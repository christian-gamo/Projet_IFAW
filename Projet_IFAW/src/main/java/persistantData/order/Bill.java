package persistantData.order;

import java.sql.Date;
import java.util.Map;

/**
 * Class that models a bill object, a new instance of this class is created each time an order is validated in the app
 * @author Christian
 *
 */

public class Bill {
	/** ID of the bill*/
	private int id_B;
	/** Creation date of the bill*/
	private Date bill_date;
	/** Estimated delivery date of the order*/
	private Date delivery_date;
	/** Country where order is delivered*/
	private String country;
	/** Payment state of the bill*/
	private boolean payment_state;
	/** Payment method of the bill (Credit Request or Credit Card*/
	private PaymentMethod payment_method;
	/** State of the order (Ongoing, Validated or Shipped*/
	private OrderState order_state;
	/** Price amount of the bill*/
	private double bill_amount;
	/** ID of the order's client (user)*/
	private int id_U;
	/** Map containing data to retrieve the vehicles (ID of vehicle, Quantity) */
	private Map<Integer,Integer> vehicles;

	/**
	 * First public constructor of the bill class which allows to create a new bill object,
	 * this first constructor doesn't allow to set the state of the order.
	 *
	 * @param i corresponds to the Bill ID
	 * @param b_d corresponds to the date of edition of the bill
	 * @param d_b corresponds to the delivery date of the order
	 * @param c corresponds to the country where the order was made
	 * @param p corresponds to the payment state of the order (Paid/Not paid)
	 * @param pm corresponds to the payment method of the order (Credit request or Credit card)
	 * @param o corresponds to the order state of the order (Ongoing, Validated or Shipped)
	 * @param f corresponds to the total amount of the bill
	 * @param iu corresponds to the ID of the user who ordered vehicles
	 * @param v corresponds to the association of the ID of a vehicle with a bill
	 */
	public Bill(int i, Date b_d, Date d_b, String c, boolean p, PaymentMethod pm, OrderState o, double f, int iu, Map<Integer,Integer> v) {
		this.setId_B(i);
		this.setBill_date(b_d);
		this.setDelivery_date(d_b);
		this.setCountry(c);
		this.setPayment_state(p);
		this.setPayment_method(pm);
		this.setOrder_state(o);
		this.setBill_amount(f);
		this.setId_U(iu);
		this.setVehicles(v);
	}

	/**
	 * Second public constructor of the class bill which allows to create a new object Bill,
	 * this second constructor allows to initialize the value of the enumerated type order state to Ongoing
	 *
	 * @param i corresponds to the Bill ID
	 * @param b_d corresponds to the date of edition of the bill
	 * @param c corresponds to the country where the order was made
	 * @param p corresponds to the payment state of the order (Paid/Not paid)
	 * @param pm corresponds to the payment method of the order (Credit request or Credit card)
	 * @param f corresponds to the total amount of the bill
	 * @param iu corresponds to the ID of the user who ordered vehicles
	 * @param v corresponds to the association of the ID of a vehicle with a bill
	 */
	public Bill(int i, Date b_d, String c, boolean p, PaymentMethod pm, double f, int iu, Map<Integer,Integer> v) {
		this.setId_B(i);
		this.setBill_date(b_d);
		this.setCountry(c);
		this.setPayment_state(p);
		this.setPayment_method(pm);
		this.setBill_amount(f);
		this.setId_U(iu);
		this.setOrder_state(OrderState.ONGOING);
		this.setVehicles(v);
	}

	/**
	 * shipping_fee() method
	 * <p>
	 * This method allows to calculate the value of the delivery tax applied
	 * to the order according to the country involved.
	 * If the order is made in Great Britain, no tax is applied. If the order is made in France,
	 * Germany, Spain or Italy, a tax of 500€ is applied.
	 * Else, for all other countries where the delivery of our vehicles is possible, the tax is 1000€.
	 *
	 * @param country which is a string which corresponds to the country where the order was made
	 * @return an integer that corresponds to the amount of tax applied to the order
	 */
	public static int shipping_fee(String country)
	{
		if (country.equals("GB"))
		{
			return 0;
		}
		String[] special_countries = {"FR","DE","ES","IT"};
		for (int i = 0 ; i < 4  ; i++)
		{
			if (country.equals(special_countries[i]))
			{
				return 500;
			}
		}
		return 1000;
	}

	/**
	 * Getter getId_U()
	 * <p>
	 * Allows to retrieve the user ID associated to the bill
	 *
	 * @return an integer that represents the user ID associated to the bill
	 */
	public int getId_U() {
		return id_U;
	}

	/**
	 * Setter setId_U()
	 * <p>
	 * Allows to set the value of the user ID associated with the bill
	 *
	 * @param id_U, the user ID associated to the bill
	 */
	public void setId_U(int id_U) {
		this.id_U = id_U;
	}

	/**
	 * Getter getId_B()
	 * <p>
	 * Allows to retrieve the Bill ID
	 *
	 * @return an integer that represents the Bill ID
	 */
	public int getId_B() {
		return id_B;
	}

	/**
	 * Setter setId_B()
	 * <p>
	 * Allows to set the value of the Bill ID
	 *
	 * @param id_B, the Bill ID
	 */
	public void setId_B(int id_B) {
		this.id_B = id_B;
	}

	/**
	 * Getter getBill_date()
	 * <p>
	 * Allows to retrieve the Bill date (date of edition of the bill)
	 *
	 * @return a date that represents the Bill date
	 */
	public Date getBill_date() {
		return bill_date;
	}

	/**
	 * Setter setBill_date()
	 * <p>
	 * Allows to set the value of the Bill date (date of edition of the bill)
	 *
	 * @param bill_date, the bill date
	 */
	public void setBill_date(Date bill_date) {
		this.bill_date = bill_date;
	}

	/**
	 * Getter getPayment_state()
	 * <p>
	 * Allows to retrieve the payment state of the order
	 *
	 * @return true if the order is paid, false otherwise
	 */
	public boolean getPayment_state() {
		return payment_state;
	}

	/**
	 * Setter setPayment_state()
	 * <p>
	 * Allows to set the value of the payment state, logically, this value is set to false by default
	 *
	 * @param payment_state, the payment state of the order
	 */
	public void setPayment_state(boolean payment_state) {
		this.payment_state = payment_state;
	}

	/**
	 * Getter getBill_amount()
	 * <p>
	 * Allows to get the value of the bill total amount
	 * as well as multiplying the unit price of vehicles
	 * by the quantity ordered, we add the tax according to the order country
	 *
	 * @return a double that represents the bill total amount
	 */
	public double getBill_amount() {
		return bill_amount + shipping_fee(getCountry());
	}

	/**
	 * Setter setBill_amount()
	 * <p>
	 * Allows to set the value of the bill total amount
	 *
	 * @param f, the bill total amount
	 */
	public void setBill_amount(double f) {
		this.bill_amount = f;
	}

	/**
	 * Getter getCountry()
	 * <p>
	 * Allows to get the value of the country
	 * where the order was taken
	 *
	 * @return a string that represents the country where the order was taken
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Setter setCountry()
	 * <p>
	 * Allows to set the value of the country
	 * where the order was taken
	 *
	 * @param country, where the order was taken
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Getter getOrder_state()
	 * Allows to get the value of the order state
	 * of a given order (ONGOING, VALIDATED, SHIPPED)
	 *
	 * @return an enumerate among ONGOING, VALIDATED or SHIPPED
	 */
	public OrderState getOrder_state() {
		return order_state;
	}

	/**
	 * Setter setOrder_state()
	 * <p>
	 * Allows to set the value of the order state
	 * of a given order (ONGOING, VALIDATED, SHIPPED)
	 *
	 * @param order_state, the order state
	 */
	public void setOrder_state(OrderState order_state) {
		this.order_state = order_state;
	}

	/**
	 * Getter getDelivery_date()
	 * <p>
	 * Allows to get the delivery date of a given order
	 *
	 * @return a date that represents the delivery date of a given order
	 */
	public Date getDelivery_date() {
		return delivery_date;
	}

	/**
	 * Setter setDelivery_date()
	 * <p>
	 * Allows to set the value of the delivery date of a given order
	 *
	 * @param delivery_date, the delivery date of a given order
	 */
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}

	/**
	 * Getter getVehicles()
	 * <p>
	 * Allows to get the list (HashMap) of all the vehicles of a certain model
	 * in a bill. This HashMap links the ID of a vehicle model and the ordered
	 * quantity of this same vehicle model.
	 *
	 * @return an HashMap with two keys : the ID of the vehicle model (Integer), the ordered quantity of
	 * the vehicle model (Integer)
	 */
	public Map<Integer,Integer> getVehicles() {
		return vehicles;
	}

	/**
	 * Setter setDelivery_date()
	 * <p>
	 * Allows to set the list (HashMap) of all the vehicles of a certain model
	 * in a bill. This hashmap links the ID of a vehicle model and the ordered
	 * quantity of this same vehicle model.
	 *
	 * @param vehicles which is a vehicle HashMap with two keys: the ID of the vehicle model (Integer), the ordered
	 * quantity of the vehicle model (Integer)
	 */
	public void setVehicles(Map<Integer,Integer> vehicles) {
		this.vehicles = vehicles;
	}

	/**
	 * Getter getPayment_method()
	 * <p>
	 * Allows to get the payment method
	 * of a given order (CREDIT CARD, CREDIT REQUEST)
	 *
	 * @return an enumerate that refers to the payment method
	 */
	public PaymentMethod getPayment_method() {
		return payment_method;
	}

	/**
	 * Setter setPayment_method()
	 * <p>
	 * Allows to set the payment method
	 * of a given order (CREDIT CARD, CREDIT REQUEST)
	 *
	 * @param payment_method, the payment method
	 */
	public void setPayment_method(PaymentMethod payment_method) {
		this.payment_method = payment_method;
	}
}