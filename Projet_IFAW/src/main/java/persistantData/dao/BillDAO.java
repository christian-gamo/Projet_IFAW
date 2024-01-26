package persistantData.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import persistantData.order.Bill;
import persistantData.order.OrderState;
import persistantData.order.PaymentMethod;
/**
 * Class that links the bill table of the database to the bill class of the java application
 * As DAO stands for Data Access Object, this class function is to access Bill table in the database
 * @author Yassine
 *
 */
public class BillDAO extends DAO<Bill> {

	/**
	 * Constructor of the BillDAO class which allows to create a new BillDAO object
	 */
	public BillDAO() {
		super(DB_Connection.getInstance());
	}

	/**
	 * insert() method
	 * <p>
	 * This method allows to insert a bill who has the same attributes as the bill given as a parameter.
	 *
	 * @param bill which corresponds to the bill which we want to put in the Bill table
	 *
	 * @return id of the newly created bill.
	 * Otherwise, returns 0 if a new bill has not been created.
	 *
	 */
	@Override
	public int insert(Bill bill) {
		String query = "INSERT INTO bill (Bill_Date,Country,Payment_State,Payment_Method,Order_State,Bill_Amount,ID_U) VALUES (?,?,?,?,?,?,?)";
		String query2 = "INSERT INTO bill_vehicles(ID_B,ID_V,Quantity) VALUES (?,?,?)";
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query,
					Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setDate(1, bill.getBill_date());
			preparedStatement.setString(2, bill.getCountry());
			preparedStatement.setBoolean(3, bill.getPayment_state());
			preparedStatement.setString(4, bill.getPayment_method().getString());
			preparedStatement.setObject(5, bill.getOrder_state().getString());
			preparedStatement.setDouble(6, bill.getBill_amount());
			preparedStatement.setInt(7, bill.getId_U());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			int id_b;
			if (resultSet.next()) {
				id_b = resultSet.getInt(1);

				for (Integer id_v : bill.getVehicles().keySet()) {
					PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
					preparedStatement2.setInt(1, id_b);
					preparedStatement2.setInt(2, id_v);
					preparedStatement2.setInt(3, bill.getVehicles().get(id_v));
					preparedStatement2.executeUpdate();
				}

				if(bill.getPayment_method().equals(PaymentMethod.CREDIT_CARD)) {
					bill = get(id_b);
					updateOrderState(bill, OrderState.VALIDATED);
					java.sql.Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
					//Addition of two days compared to the current date which correspond to the delivery delay
					Date deliveryDate = addDays(d,2);
					setDeliveryDate(bill,deliveryDate);
				}
				return id_b;
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * getAll() method
	 * <p>
	 * This method allows to retrieve every bill in the Bill table.
	 * @return a list of bills which contains every user in the Bill table.
	 */
	@Override
	public List<Bill> getAll() {
		String query = "SELECT * FROM bill";
		Bill b = null;
		List<Bill> l = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			ResultSet set = preparedStatement.executeQuery();

			while (set.next()) {
				int id_B = set.getInt("ID_B");
				Date bill_date = set.getDate("Bill_Date");
				Date delivery_date = set.getDate("Delivery_Date");
				String country = set.getString("Country");
				boolean payment_state = set.getBoolean("Payment_State");
				String payment_method = set.getString("Payment_Method");
				String order_state = set.getString("Order_State");

				OrderState os;
				switch (order_state) {
				case "Ongoing":
					os = OrderState.ONGOING;
					break;
				case "Validated":
					os = OrderState.VALIDATED;
					break;
				case "Shipped":
					os = OrderState.SHIPPED;
					break;
				default:
					os = OrderState.ONGOING;
					break;
				}

				PaymentMethod pm;
				switch (payment_method) {
				case "Credit Card":
					pm = PaymentMethod.CREDIT_CARD;
					break;
				case "Credit Request":
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				default:
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				}

				double bill_amount = set.getDouble("Bill_Amount");
				int id_u = set.getInt("ID_U");

				String query2 = "SELECT * FROM bill_vehicles WHERE ID_B = ?";
				PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
				preparedStatement2.setInt(1, id_B);
				ResultSet set2 = preparedStatement2.executeQuery();

				Map<Integer, Integer> vehicles = new HashMap<>();

				while (set2.next()) {
					int id_v = set2.getInt("ID_V");
					int quantity = set2.getInt("Quantity");
					vehicles.put(id_v, quantity);
				}

				b = new Bill(id_B, bill_date, delivery_date, country, payment_state, pm, os, bill_amount, id_u,
						vehicles);

				l.add(b);
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return l;
	}


	/**
	 * get() method
	 * <p>
	 * This method allows to retrieve from the Bill table an user (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param id the Bill ID associated to the user
	 * @return Bill b the bill object associated to the Integer id.
	 */
	@Override
	public Bill get(int id) {
		String query = "SELECT * FROM bill WHERE ID_B = ?";
		Bill b = null;
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id);
			ResultSet set = preparedStatement.executeQuery();

			if (set.next()) {
				int id_B = set.getInt("ID_B");
				Date bill_date = set.getDate("Bill_Date");
				Date delivery_date = set.getDate("Delivery_Date");
				String country = set.getString("Country");
				String payment_method = set.getString("Payment_Method");
				boolean payment_state = set.getBoolean("Payment_State");
				String order_state = set.getString("Order_State");

				OrderState os;
				switch (order_state) {
				case "Ongoing":
					os = OrderState.ONGOING;
					break;
				case "Validated":
					os = OrderState.VALIDATED;
					break;
				case "Shipped":
					os = OrderState.SHIPPED;
					break;
				default:
					os = OrderState.ONGOING;
					break;
				}

				PaymentMethod pm;
				switch (payment_method) {
				case "Credit Card":
					pm = PaymentMethod.CREDIT_CARD;
					break;
				case "Credit Request":
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				default:
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				}

				double bill_amount = set.getFloat("Bill_Amount");
				int id_u = set.getInt("ID_U");

				String query2 = "SELECT * FROM bill_vehicles WHERE ID_B = ?";
				PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
				preparedStatement2.setInt(1, id_B);
				ResultSet set2 = preparedStatement2.executeQuery();

				Map<Integer, Integer> vehicles = new HashMap<>();

				while (set2.next()) {
					int id_v = set2.getInt("ID_V");
					int quantity = set2.getInt("Quantity");
					vehicles.put(id_v, quantity);
				}

				b = new Bill(id_B, bill_date, delivery_date, country, payment_state, pm, os, bill_amount, id_u,
						vehicles);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return b;
	}


	/**
	 * getByUserID() method
	 * <p>
	 * This method allows to retrieve from the Bill table a bill (if it does exist) who has the same ID as the one given as parameter.
	 *
	 * @param id_u which is the user id
	 * @return A list that corresponds to all bills that belong to the given user (who has the ID in parameter)
	 */
	public List<Bill> getByUserID(int id_u) {
		String query = "SELECT * FROM bill WHERE ID_U = ?";
		Bill b = null;
		List<Bill> l = new ArrayList<>();
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id_u);
			ResultSet set = preparedStatement.executeQuery();

			while (set.next()) {
				int id_B = set.getInt("ID_B");
				Date bill_date = set.getDate("Bill_Date");
				Date delivery_date = set.getDate("Delivery_Date");
				String country = set.getString("Country");
				String payment_method = set.getString("Payment_Method");
				boolean payment_state = set.getBoolean("Payment_State");
				String order_state = set.getString("Order_State");

				OrderState os;
				switch (order_state) {
				case "Ongoing":
					os = OrderState.ONGOING;
					break;
				case "Validated":
					os = OrderState.VALIDATED;
					break;
				case "Shipped":
					os = OrderState.SHIPPED;
					break;
				default:
					os = OrderState.ONGOING;
					break;
				}

				PaymentMethod pm;
				switch (payment_method) {
				case "Credit Card":
					pm = PaymentMethod.CREDIT_CARD;
					break;
				case "Credit Request":
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				default:
					pm = PaymentMethod.CREDIT_REQUEST;
					break;
				}

				double bill_amount = set.getFloat("Bill_Amount");

				String query2 = "SELECT * FROM bill_vehicles WHERE ID_B = ?";
				PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
				preparedStatement2.setInt(1, id_B);
				ResultSet set2 = preparedStatement2.executeQuery();

				Map<Integer, Integer> vehicles = new HashMap<>();

				while (set2.next()) {
					int id_v = set2.getInt("ID_V");
					int quantity = set2.getInt("Quantity");
					vehicles.put(id_v, quantity);
				}

				b = new Bill(id_B, bill_date, delivery_date, country, payment_state, pm, os, bill_amount, id_u,
						vehicles);
				l.add(b);
			}
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}


	/**
	 * delete() method
	 * <p>
	 * This method allows to delete from the Bill table an bill (if it does exist) who has the same id as the one given as a parameter.
	 *
	 * @param id_B which is the Bill ID associated to the bill
	 * @return boolean true if deletion has been successful, false otherwise
	 */
	@Override
	public boolean delete(int id_B) {
		String query = "DELETE FROM bill_vehicules WHERE ID_B = ?";
		String query2 = "DELETE FROM bill WHERE ID_B = ?";
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setInt(1, id_B);
			preparedStatement.executeUpdate();

			PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
			preparedStatement2.setInt(1, id_B);
			preparedStatement2.executeUpdate();
			return true;
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return false;
	}

     /**
      * createBillObject() method
      * <p>
      * This method allows to create a new bill object, in order to be able to retrieve
      * more easily (thanks to a table) the bill data
      *
      * @param data an object array containing the fields of the bill object
      * @return a new data object (respecting the data format imposed by the constructor)
      */
	@SuppressWarnings("unchecked")
	public Bill createBillObject(Object[] data) {
		Bill b = new Bill((int) data[0], (Date) data[1], (String) data[2], (boolean) data[3], (PaymentMethod) data[4],
				(double) data[5], (int) data[6], (Map<Integer, Integer>) data[7]);
		return b;

	}

    /**
     * updateOrderState() Method
     * <p>
     * This method allows to update the state of the order among the 3 possible states
     * depending on the delivery time and the payment of the order
     * @param b corresponds to the bill for which we want to update the order state
     * @param orderState the order status that we want to set to the bill in parameter
     */
	public void updateOrderState(Bill b, OrderState orderState) {
		String query = "UPDATE bill SET Order_State = ? WHERE ID_B = ?";
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setString(1, orderState.getString());
			preparedStatement.setInt(2, b.getId_B());
			preparedStatement.executeUpdate();
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	/**
	 * setDeliveryDate() method
	 * <p>
	 * This method allows you to set a delivery date for an invoice associated with a given order after payment,
	 * this delivery date is set at two days after the order is placed by default
	 *
	 * @param b corresponds to the bill for which we want to set a delivery date
	 * @param dd the delivery date of the bill
	 */
	public void setDeliveryDate(Bill b, Date dd) {
		String query = "UPDATE bill SET Delivery_Date = ? WHERE ID_B = ?";
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setDate(1, dd);
			preparedStatement.setInt(2, b.getId_B());
			preparedStatement.executeUpdate();
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

	/**
	 * updatePaymentState() method
	 * <p>
	 * This method allows to update the payment state of the order if it has been paid, by default the state of the order is false
	 *
	 * @param b bill which we want to update the payment state
	 * @param payment_state the new payment state of the order
	 */
	public void updatePaymentState(Bill b, boolean payment_state) {
		String query = "UPDATE bill SET payment_state = ? WHERE ID_B = ?";
		try {
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
			preparedStatement.setBoolean(1, payment_state);
			preparedStatement.setInt(2, b.getId_B());
			preparedStatement.executeUpdate();
		}

		catch (SQLException throwables) {
			throwables.printStackTrace();
		}
	}

    /**
     * addDays() Method
     * <p>
     * This method allows you to add a certain number of days to a given date (for example to add a delivery delay)
     *
     * @param d a reference date for which we want to add days (usually the current date)
     * @param nbDays the number of days to add to the date entered in parameters
     * @return a new date updated by adding the given number of days to the initial date given in parameters
     */
    public static Date addDays(Date d, int nbDays) {
        return new Date(d.getTime() + 1000 * 60 * 60 * 24 * nbDays);
    }

}
