package persistantData.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import persistantData.vehicles.Vehicle;
import persistantData.vehicles.VehicleFactory;

/**
 * Class that links the vehicle table of the database to the vehicle class of the java application
 * As DAO stands for Data Access Object, this class function is to access vehicle table in the database
 * @author Christian
 *
 */
public class VehicleDAO extends DAO<Vehicle> {

	/** Instance of VehicleFactory, necessary to create vehicle objects*/
	private static VehicleFactory factory;

	/**
	 * Constructor of the VehicleDAO class which allows to create a new VehicleDAO object
	 * As DAO stands for Data Access Object, this class's function is to access Vehicle table in the database
	 */
    public VehicleDAO() {
        super(DB_Connection.getInstance());
        factory = new VehicleFactory();

    }


    /**
     *
     * insert() method
	 * <p>
	 * This method allows to insert a Vehicle who has the same attributes as the vehicle given as a parameter.
	 *
	 * @param v which corresponds to the user which we want to put in the Vehicle table
	 *
	 * @return the id of the newly created vehicle.
	 * Otherwise, returns 0 if vehicle has not been created.
	 *
     */
	@Override
	public int insert(Vehicle v)
	{
		String query = "INSERT INTO vehicle "
		+ "(Type,Category,Brand,Model,Engine,Gearbox,Nb_seats,"
		+ "Description,Price,Picture,Available,Date_Stock,On_sale,Stock)"
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		PreparedStatement preparedStatement;
		try {
			preparedStatement = super.getConnection().prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, v.getType()); //"0" Not Available <=>  / "1" <=>  Available
			preparedStatement.setString(2, v.getCategory());
			preparedStatement.setString(3, v.getBrand());
			preparedStatement.setString(4, v.getModel());
			preparedStatement.setString(5, v.getEngine());
			preparedStatement.setString(6, v.getGearbox());
			preparedStatement.setInt(7, v.getNb_seats());
			preparedStatement.setString(8, v.getDescription());
			preparedStatement.setDouble(9, v.getPrice());
			preparedStatement.setString(10, v.getPicture());
			preparedStatement.setBoolean(11, v.isAvailable());
			preparedStatement.setDate(12, v.getDate_stock());
			preparedStatement.setBoolean(13, v.isOn_sale());
			preparedStatement.setInt(14, v.getStock());
	        preparedStatement.executeUpdate();

	        ResultSet resultSet = preparedStatement.getGeneratedKeys();

			int id = 0;
			if(resultSet.next())
				id=resultSet.getInt(1);
			return id;
		}

		catch (SQLException throwables) {
            throwables.printStackTrace();
        }
		return 0;
	}

	/**
	 * getAll() method
	 * <p>
	 * This method allows to retrieve every vehicle in the Vehicle table.
	 * If a vehicle's date of stock is bigger or equals to 2 years, it is set on sale.
	 * @return a list of Vehicle which contains every vehicle in the Vehicle table.
	 */
	@Override
	public List<Vehicle> getAll() {
		String query = "SELECT * FROM vehicle";
		Vehicle v = null;
		List<Vehicle> l = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
            	int id_V = set.getInt("ID_V");
            	String Type = set.getString("Type");
            	String Category = set.getString("Category");
            	String Brand = set.getString("Brand");
            	String model = set.getString("Model");
            	String Engine = set.getString("Engine");
            	String Gearbox = set.getString("Gearbox");
            	int Nb_seats = set.getInt("Nb_seats");
            	String Description = set.getString("Description");
            	double Price = set.getDouble("Price");
            	String Picture = set.getString("Picture");
            	Boolean Available = set.getBoolean("Available");
            	Date Date_Stock = set.getDate("Date_Stock");
            	Boolean on_sale = set.getBoolean("On_sale");
            	int Stock = set.getInt("Stock");

            	long difference = System.currentTimeMillis() - Date_Stock.getTime();
            	long one_day_ms = 60 * 60 * 24 * 1000; //1 day in milliseconds
            	int diffDays = (int)(difference / one_day_ms);

            	if (diffDays >= 365 * 2)
            	{	on_sale = true;
            		String query2 = "UPDATE vehicle SET on_sale = 1 WHERE ID_V = ?";
            		PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
            		preparedStatement2.setInt(1, id_V);
            		preparedStatement2.executeUpdate();
            		Price = Price * 0.7; //30% OFF!
            	}

            	Object data[] = new Object[]{id_V,Type,Category,Brand,model,Engine,Gearbox,Nb_seats,Description,Price,Picture,Available,Date_Stock,on_sale,Stock};
            	v = factory.createVehicle(data);
            	l.add(v);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return l;
	}

	/**
	 * getAllAvailable() method
	 * <p>
	 * This method allows to retrieve every available vehicle in the Vehicle table.
	 * If a vehicle's date of stock is bigger or equals to 2 years, it is set on sale.
	 * @return a list of Vehicle which contains every available vehicle in the Vehicle table.
	 */
	public List<Vehicle> getAllAvailable() {
		String query = "SELECT * FROM vehicle WHERE Available = ?";
		Vehicle v = null;
		List<Vehicle> l = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            preparedStatement.setString(1, "1"); //"0" Not Available <=>  / "1" <=>  Available
            String s = preparedStatement.toString();
            ResultSet set = preparedStatement.executeQuery();

            while (set.next()) {
            	int id_V = set.getInt("ID_V");
            	String Type = set.getString("Type");
            	String Category = set.getString("Category");
            	String Brand = set.getString("Brand");
            	String model = set.getString("Model");
            	String Engine = set.getString("Engine");
            	String Gearbox = set.getString("Gearbox");
            	int Nb_seats = set.getInt("Nb_seats");
            	String Description = set.getString("Description");
            	double Price = set.getDouble("Price");
            	String Picture = set.getString("Picture");
            	Boolean Available = set.getBoolean("Available");
            	Date Date_Stock = set.getDate("Date_Stock");
            	Boolean on_sale = set.getBoolean("On_sale");
            	int Stock = set.getInt("Stock");

            	long difference = System.currentTimeMillis() - Date_Stock.getTime();
            	long one_day_ms = 60 * 60 * 24 * 1000; //1 day in milliseconds
            	int diffDays = (int)(difference / one_day_ms);

            	if (diffDays >= 365 * 2)
            	{	on_sale = true;
            		String query2 = "UPDATE vehicle SET on_sale = 1 WHERE ID_V = ?";
            		PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
            		preparedStatement2.setInt(1, id_V);
            		preparedStatement2.executeUpdate();
            		Price = Price * 0.7; //30% OFF!
            	}

            	Object data[] = new Object[]{id_V,Type,Category,Brand,model,Engine,Gearbox,Nb_seats,Description,Price,Picture,Available,Date_Stock,on_sale,Stock};
            	v = factory.createVehicle(data);
            	l.add(v);
            }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return l;
	}

	/**
	 * get() method
	 * <p>
	 * This method allows to retrieve from the Vehicle table a vehicle (if it does exist)
	 * which has the same id as the id given as a parameter.
	 * If a vehicle's date of stock is bigger or equals to 2 years, it is set on sale.
	 *
	 * @param id, the vehicle ID associated to the vehicle
	 * @return Vehicle v, the vehicle object associated to the ID id.
	 */
	@Override
	public Vehicle get(int id) {
		String query = "SELECT * FROM vehicle WHERE ID_V = ?";
		Vehicle v = null;

        try {
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
            	int id_V = set.getInt("ID_V");
            	String Type = set.getString("Type");
            	String Category = set.getString("Category");
            	String Brand = set.getString("Brand");
            	String model = set.getString("Model");
            	String Engine = set.getString("Engine");
            	String Gearbox = set.getString("Gearbox");
            	int Nb_seats = set.getInt("Nb_seats");
            	String Description = set.getString("Description");
            	double Price = set.getDouble("Price");
            	String Picture = set.getString("Picture");
            	Boolean Available = set.getBoolean("Available");
            	Date Date_Stock = set.getDate("Date_Stock");
            	Boolean on_sale = set.getBoolean("On_sale");
            	int Stock = set.getInt("Stock");

            	long difference = System.currentTimeMillis() - Date_Stock.getTime();
            	long one_day_ms = 60 * 60 * 24 * 1000; //1 day in milliseconds
            	int diffDays = (int)(difference / one_day_ms);

            	if (diffDays >= 365 * 2)
            	{	on_sale = true;
            		String query2 = "UPDATE vehicle SET on_sale = 1 WHERE ID_V = ?";
            		PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
            		preparedStatement2.setInt(1, id_V);
            		preparedStatement2.executeUpdate();
            		Price = Price * 0.7; //30% OFF!
            	}

            	Object data[] = new Object[]{id_V,Type,Category,Brand,model,Engine,Gearbox,Nb_seats,Description,Price,Picture,Available,Date_Stock,on_sale,Stock};
            	v = factory.createVehicle(data);
            }
        }

        catch (SQLException throwables) {

            throwables.printStackTrace();
        }
        return v;
	}

	/**
	 * delete() method
	 * <p>
	 * This method allows to delete from the Vehicle table a vehicle (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param id the vehicle ID associated to the vehicle
	 * @return boolean true if deletion has been successful, false otherwise
	 */
	@Override
	public boolean delete(int id) {

		String query = "DELETE FROM vehicle WHERE ID_V = ?";
		try
		{
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		return false;
	}

	/**
	 * getByModel() method
	 * <p>
	 * This method allows to retrieve from the Vehicle table a vehicle (if it does exist) which has the same model.
	 * If a vehicle's date of stock is bigger or equals to 2 years, it is set on sale.
	 *
	 * @param model the model of the vehicle
	 * @return v the vehicle associated to the vehicle model
	 */
	public Vehicle getByModel(String model) {
		String query = "SELECT * FROM vehicle WHERE Model = ?";
		Vehicle v = null;
        try {
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            preparedStatement.setString(1, model);

            ResultSet set = preparedStatement.executeQuery();

            if (set.next()) {
            	int id_V = set.getInt("ID_V");
            	String Type = set.getString("Type");
            	String Category = set.getString("Category");
            	String Brand = set.getString("Brand");
            	String Model = set.getString("Model");
            	String Engine = set.getString("Engine");
            	String Gearbox = set.getString("Gearbox");
            	int Nb_seats = set.getInt("Nb_seats");
            	String Description = set.getString("Description");
            	double Price = set.getDouble("Price");
            	String Picture = set.getString("Picture");
            	Boolean Available = set.getBoolean("Available");
            	Date Date_Stock = set.getDate("Date_Stock");
            	Boolean on_sale = set.getBoolean("On_sale");
            	int Stock = set.getInt("Stock");

            	long difference = System.currentTimeMillis() - Date_Stock.getTime();
            	long one_day_ms = 60 * 60 * 24 * 1000; //1 day in milliseconds
            	int diffDays = (int)(difference / one_day_ms);

            	if (diffDays >= 365 * 2)
            	{	on_sale = true;
            		String query2 = "UPDATE vehicle SET on_sale = 1 WHERE ID_V = ?";
            		PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
            		preparedStatement2.setInt(1, id_V);
            		preparedStatement2.executeUpdate();
            		Price = Price * 0.7; //30% OFF!
            	}

            	Object data[] = new Object[]{id_V,Type,Category,Brand,Model,Engine,Gearbox,Nb_seats,Description,Price,Picture,Available,Date_Stock,on_sale,Stock};
            	v = factory.createVehicle(data);
            }
        }

        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return v;
    }

	/**
	 * updateStock() method
	 * <p>
	 * This method allows to update stock of an existing vehicle.
	 *
	 * @param v which is the vehicle we will update the stock
	 * @param stock the new stock we want to stock
	 */
	public void updateStock(Vehicle v, int stock) {

		String query = "UPDATE vehicle SET stock = ? WHERE ID_V = ?";
		try
		{
            PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, stock);
            preparedStatement.setInt(2, v.getID_V());
            preparedStatement.executeUpdate();

            if(stock==0){
            	String query2 = "UPDATE vehicle SET Available = ? WHERE ID_V = ?";
            	PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query);
                preparedStatement.setBoolean(1, false);
                preparedStatement.setInt(2, v.getID_V());
                preparedStatement.executeUpdate();
            }
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	}

	/**
	 * updateVehicle() method
	 * <p>
	 * This method allows to update the fields of an existing vehicle
	 *
	 * @param v which is the vehicle we want to update the fields
	 * @param type the new type of vehicle
	 * @param brand the new brand of the vehicle
	 * @param model the new model of the vehicle
	 * @param gearbox the new gearbox of the vehicle
	 * @param category the new category of the vehicle
	 * @param engine the new engine of the vehicle
	 * @param nb_seats the new number of the seats
	 * @param description the new description of the vehicle
	 * @param price the new price of the vehicle
	 * @param picture the new link to the picture of the vehicle
	 * @param available true if its available, false otherwise
	 * @param stock the new number of vehicles of this model in stock
	 */
	public void updateVehicle(Vehicle v, String type, String brand, String model,
			String gearbox, String category, String engine, int nb_seats, String description,
			double price, String picture,boolean available, int stock) {

		String query = "UPDATE vehicle SET Type = ?, Category = ?, Brand = ?, Model = ?, Engine = ?,"
		+ "Gearbox = ?, Nb_seats = ?, Description = ?, Price = ?, Picture = ?, Available = ?,"
		+ "Stock = ? WHERE ID_V = ?";

		try
		{
			PreparedStatement preparedStatement = super.getConnection().prepareStatement(query);

            preparedStatement.setString(1,type);
            preparedStatement.setString(2,category);
            preparedStatement.setString(3,brand);
            preparedStatement.setString(4,model);
            preparedStatement.setString(5,engine);
            preparedStatement.setString(6,gearbox);
            preparedStatement.setInt(7,nb_seats);
            preparedStatement.setString(8,description);
            preparedStatement.setDouble(9,price);
            preparedStatement.setString(10,picture);
            preparedStatement.setBoolean(11, stock>0);
            preparedStatement.setInt(12,stock);
            preparedStatement.setInt(13, v.getID_V());
            preparedStatement.executeUpdate();

		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	}

	/**
	 * createVehicleObject() method
	 * <p>
	 * This method allows the creation of a vehicle object
	 * Needed sometimes in the CatalogData class to instantiate a vehicle object without id
	 * @param data which contains the fields we want to create the vehicle with
	 * @return Vehicle v, the vehicle associated to the vehicle model
	 */
	public Vehicle createVehicleObject(Object[] data) {
		Vehicle v = factory.createVehicle(data);
		return v;
	}

	/**
	 * filter() method
	 * <p>
	 * This method allows to filter the vehicle table
	 * @param filters which contains the filters.
	 * Array of object contains data variables relevant to the corresponding filter.
	 * @see VehicleDAO#appendFilter(StringBuilder sb, boolean isString, String operator, String field, String value1, String value2)
	 * @return a list of vehicles the list of vehicles which correspond to the filters
	 */
	public List<Vehicle> filter(List<Object[]> filters){
		List<Vehicle> l = new ArrayList<>();

		StringBuilder sb = new StringBuilder();
		sb.append("SELECT * FROM vehicle WHERE Available = true");


		for(Object[] filter: filters) {
			appendFilter(sb,(boolean)filter[0],(String)filter[1],(String) filter[2], (String)filter[3] , (String)filter[4]);

		}


        String query = sb.toString();

        PreparedStatement preparedStatement;
		try {
			 preparedStatement = super.getConnection().prepareStatement(query);
			 ResultSet set = preparedStatement.executeQuery();
			 while (set.next()) {
				 	Vehicle v;
	            	int id_V = set.getInt("ID_V");
	            	String Type = set.getString("Type");
	            	String Category = set.getString("Category");
	            	String Brand = set.getString("Brand");
	            	String model = set.getString("Model");
	            	String Engine = set.getString("Engine");
	            	String Gearbox = set.getString("Gearbox");
	            	int Nb_seats = set.getInt("Nb_seats");
	            	String Description = set.getString("Description");
	            	double Price = set.getDouble("Price");
	            	String Picture = set.getString("Picture");
	            	Boolean Available = set.getBoolean("Available");
	            	Date Date_Stock = set.getDate("Date_Stock");
	            	Boolean sale = set.getBoolean("On_sale");
	            	int Stock = set.getInt("Stock");

	            	long difference = System.currentTimeMillis() - Date_Stock.getTime();
	            	long one_day_ms = 60 * 60 * 24 * 1000; //1 day in milliseconds
	            	int diffDays = (int)(difference / one_day_ms);

	            	if (diffDays >= 365 * 2)
	            	{	sale = true;
	            		String query2 = "UPDATE vehicle SET on_sale = 1 WHERE ID_V = ?";
	            		PreparedStatement preparedStatement2 = super.getConnection().prepareStatement(query2);
	            		preparedStatement2.setInt(1, id_V);
	            		preparedStatement2.executeUpdate();
	            		Price = Price * 0.7; //30% OFF!
	            	}

	            	Object data[] = new Object[]{id_V,Type,Category,Brand,model,Engine,Gearbox,Nb_seats,Description,Price,Picture,Available,Date_Stock,sale,Stock};
	            	v = factory.createVehicle(data);
	            	l.add(v);
	            }

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return l;
	}

	/**
	 * appendFilter() method
	 * <p>
	 * This method allows to create the query necessary for the filtering
	 * @param sb the stringbuilder which contains the query that is being constructed
	 * @param isString boolean to add '' when field is a string
	 * @param operator which can be equals, less than, greater than, or any other combination
	 * @param field the name of the field we want to filter
	 * @param value1 the first value we want to add to the filter, can be null
	 * @param value2 the second value we want to add to the filter, can be null
	 * @return boolean b which returns true if query has been updated, false otherwise
	 */
	private boolean appendFilter(StringBuilder sb, boolean isString, String operator, String field, String value1, String value2) {

		//On vérifie si la valeur est null. Si c'est le cas, on trim (retire les espaces) et on vérifie si
		//la valeur est vide. Si elle est vide, on considère la valeur comme null.
		value1 = value1 == null ? null : (value1.trim().isEmpty()? null: value1.trim());
		value2 = value2 == null ? null : (value2.trim().isEmpty()? null: value2.trim());

		//Si les deux valeurs sont vides, on saute la fonction
		if(value1 != null || value2 != null) {

			//On rajoute un filtre (le premier étant Available = true)
			sb.append(" AND ");

			String character = "";

			//si la ou les valeurs sont des chaines de caractères, on doit rajouter les apostrophes (') dans la requete
			if(isString) {
				character = "'";
			}

	    	sb.append("(");

	    	if(value1 != null && value2!= null)
	    		sb.append(field + " " + operator + " " + character + value1 + character +
	    		" OR " + field + " " + operator + " " + character + value2 + character);
	    	else if(value1!=null)
	    		sb.append(field + " " + operator + " " + character + value1 + character );
	    	else if(value2!=null)
	    		sb.append(field + " " + operator + " " + character + value2 + character );

	        sb.append(")");

			return true;
		}
		return false;
	}

}
