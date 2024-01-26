package persistantData.dao;


import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import persistantData.order.Bill;
import persistantData.order.OrderState;
import persistantData.users.Company;
import persistantData.users.User;
import persistantData.vehicles.Vehicle;

/**
 * With this class you can use the features of the three DAO (for User, Vehicle and Bill)
 * This class follows the singleton and facade design pattern.
 * Facade design pattern : CatalogData is used to access DAO classes and thus database operations.
 * @author Christian
 *
 */

public class CatalogData {
	/**DAO object for the user database*/
	private UserDAO dbUser;
	/**DAO object for the vehicle database*/
	private VehicleDAO dbVehicle;
	/**DAO object for the bill database*/
	private BillDAO dbBill;
	/**Instance of CatalogData (follows Singleton design pattern)*/
	private static CatalogData instance;

	/**
	 * Constructor of CatalogData which allows to create a new CatalogData object
	 */
	private CatalogData () {
		dbUser = new UserDAO();
		dbVehicle = new VehicleDAO();
		dbBill = new BillDAO();
	}

	/**
	 * getInstance() method
	 * <p>
	 * This method allows to create a new CatalogData object if it doesn't exist (if it's not instanced yet)
	 * @return a new CatalogData object if it doesn't exist, a CatalogData object which already exists otherwise.
	 */
	public static CatalogData getInstance() {
		if (instance == null) {
			instance = new CatalogData();
		}
		return instance;
	}
	/**
	 * deleteVehicle() method
	 * <p>
	 * This method allows to delete from the Vehicle table a vehicle (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param ID_V, the vehicle ID associated to the vehicle
	 */
	public void deleteVehicle(int ID_V) {
		dbVehicle.delete(ID_V);
	}

	/**
	 * getUser() method
	 * <p>
	 * This method allows to retrieve from the User table an user (if it does exist) who has the same login and password as those given as parameters.
	 *
	 * @param login, the Email address associated to the user
	 * @param password, the password associated to the user
	 * @return User object, the user associated to the email login, to the password pwd.
	 */
	public User getUser(String login, String password) {
		return dbUser.getByLogin(login, password);
	}

	/**
	 * getUserByID() method
	 * <p>
	 * This method allows to retrieve from the User table an user (if it does exist) who has the same id as the id given as a parameter.
	 *
	 * @param id the user ID associated to the user
	 * @return User object, the user associated to the ID id.
	 */
	public User getUserByID(int id) {
		return dbUser.get(id);
	}

	/**
	 * createUser() method
	 * <p>
	 * This method allows to create and to insert in User table a User who has attributes given as parameters.
	 *
	 * @param email corresponds to the new user's email
	 * @param password corresponds to the new user's password
	 * @param lastName corresponds to the new user's last name
	 * @param firstName corresponds to the new user's first name
	 *
	 * @return the id of the user.
	 *
	 */
	public int createUser(String email, String password, String lastName, String firstName) {
		User u = new User(email, password, lastName, firstName);
		return dbUser.insert(u);
	}

	/**
	 * createCompany() method
	 * <p>
	 * This method allows to create and to insert in User table a Company who has attributes given as parameters.
	 *
	 * @param email corresponds to the new comapny's email
	 * @param password corresponds to the password of the new company's person in charge
	 * @param lastName corresponds to the last name of the new company's person in charge
	 * @param firstName corresponds to the first name of the new company's person in charge
	 * @param name corresponds to the new company's name
	 * @return returns the id of the company.
	 *
	 */

	public int createCompany(String email, String password, String lastName, String firstName, String name) {
		Company c = new Company(email, password, lastName, firstName, name);
		return dbUser.insert(c);
	}

	/**
	 * isAdmin() method
	 * <p>
	 * This method allows to know whether an user is an administrator or not
	 *
	 * @param u which is a User object and corresponds to the user which we want to know if they are an administrator or not
	 *
	 * @return returns true if the given user is an administrator, false otherwise
	 */
	public boolean isAdmin(User u) {
		return u.isAdmin();
	}

	/**
	 * isCompany() method
	 * <p>
	 * This method allows to know whether an user is a company or not
	 *
	 * @param u corresponds to the user which we want to know if he/she's registered as a company or not
	 *
	 * @return returns true if the given user is registered as a company, false otherwise
	 */
	public boolean isCompany(User u) {
		return u.isCompany();
	}

	/**
	 * getVehicles() method
	 * <p>
	 * This method allows to retrieve every vehicle in the vehicle table.
	 * @return a list of vehicles which contains every vehicle in the Vehicle table.
	 */
	public List<Vehicle> getVehicles() {
		return dbVehicle.getAll();
	}

	/**
	 * getAvailableVehicles() method
	 * <p>
	 * This method allows to retrieve every vehicle in stock in the vehicle table.
	 * @return a list of User which contains every vehicle in stock in the Vehicle table.
	 */
	public List<Vehicle> getAvailableVehicles() {
		return dbVehicle.getAllAvailable();
	}

	/**
	 * getVehicleByID() method
	 * <p>
	 * This method allows to retrieve from the Vehicle table an vehicle (if it does exist) which has the same id as the id given as a parameter.
	 *
	 * @param id, the user ID associated to the vehicle
	 * @return Vehicle v, the vehicle associated to the ID id.
	 */
	public Vehicle getVehicleByID(int id) {
		return dbVehicle.get(id);
	}

	/**
	 * getVehicleBymodel() method
	 * <p>
	 * This method allows to retrieve from the Vehicle table an vehicle (if it does exist) which is the same model as the model given as a parameter
	 *
	 * @param model, the model associated to the vehicle
	 * @return Vehicle v, the vehicle associated to the model model.
	 */
	public Vehicle getVehicleByModel(String model) {
		return dbVehicle.getByModel(model);
	}

	/**
	 * updateVehicleStock() method
	 * <p>
	 * This method allows to update how many vehicle v is in stock.
	 * Given vehicle v and integer stock, sets vehicle v's stock to the integer stock.
	 *
	 * @param v the vehicle which we want to update its stock.
	 * @param stock, a new value for vehicle v's stock.
	 */
	public void updateVehicleStock(Vehicle v, int stock) {
		dbVehicle.updateStock(v, stock);
	}

	/**
	 * addVehicle() method
	 * <p>
	 * This method allow to insert in the vehicle table a vehicle with attributes corresponding to those in the object[] data given as a parameter.
	 *
	 * @param data, an array of Objects which contains attributes needed to create a vehicle
	 * @return an integer which corresponds to the id of the vehicle.
	 *
	 */
	public int addVehicle(Object[] data) {
		Vehicle v = dbVehicle.createVehicleObject(data);
		return dbVehicle.insert(v);
	}

	/**
	 * filterVehicles() method
	 * <p>
	 * This method allows to retrieve from the Vehicle table a list of vehicles according to a filter given as a list of Object[]
	 *
	 * @param filters which corresponds to
	 * @return a list of vehicles according to a filter given as a parameter.
	 */
	public List<Vehicle> filterVehicles(List<Object[]> filters){
		return dbVehicle.filter(filters);
	}

	/**
	 * updateVehicle() method
	 * <p>
	 * This method allows to modify every attribute of a vehicle v in the Vehicle table
	 * @param v corresponds to the vehicle which we want to update
	 * @param type corresponds to the new vehicle type
	 * @param brand corresponds to the new brand
	 * @param model corresponds to the new model
	 * @param gearbox corresponds to the new gearbox
	 * @param category corresponds to the new category
	 * @param engine corresponds to the new engine
	 * @param nb_seats corresponds to the new nb_seats
	 * @param description corresponds to the new description
	 * @param price corresponds to the new price
	 * @param picture corresponds to the new picture
	 * @param available corresponds to the new availability
	 * @param stock corresponds to the new stock
	 */
	public void updateVehicle(Vehicle v, String type, String brand, String model,
			String gearbox, String category, String engine, int nb_seats, String description,
			double price, String picture,boolean available, int stock) {
		dbVehicle.updateVehicle(v, type, brand, model, gearbox, category, engine, nb_seats, description, price, picture, available, stock);
	}

	/**
	 * addBill() method
	 * <p>
	 * This method allow to insert in the Bill table a Bill with attributes corresponding to those in the object[] data given as a parameter.
	 *
	 * @param data, a list of Objects which contains attributes needed to create a bill
	 * @return an integer which corresponds to the id of the bill.
	 *
	 */
	public int addBill(Object[] data) {
		Bill b = dbBill.createBillObject(data);
		return dbBill.insert(b);
	}

	/**
	 * getAllBills() method
	 * <p>
	 * This method allows to retrieve every Bill in the Bill table.
	 * @return a list of Bill which contains every Bill in the Bill table.
	 */
	public List<Bill> getAllBills() {
		return dbBill.getAll();
	}

	/**
	 * getAllBillsByUserID() method
	 * <p>
	 *
	 * This method allows to retrieve every Bill in the Bill table, with its ID_U equal to the integer id given as a parameter
	 * @param id which is the id of the User which we want to retrieve every bills associated with them.
	 * @return a list of Bill which contains every bill associated with the user who has ID_U equal to id in the database.
	 */
	public List<Bill> getBillsByUserID(int id){
		return dbBill.getByUserID(id);
	}

	/**
	 * getBillByID() method
	 * <p>
	 * This method allows to retrieve from the Bill table a Bill (if it does exist) which has the same id as the id given as a parameter.
	 *
	 * @param id the user ID associated to the bill
	 * @return Bill object, the bill associated to the ID id.
	 */
	public Bill getBillByID(int id){
		return dbBill.get(id);
	}

	/**
	 * validateOrder() method
	 * <p>
	 *
	 * This method allows to validate an order (a bill) which has as ID the integer id given as a parameter.
	 * @param id ID of the bill we want to validate
	 */
	public void validateOrder(int id){
		OrderState validated = OrderState.VALIDATED;
		Bill b = CatalogData.getInstance().getBillByID(id);

		java.sql.Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		//addition of 3 days after validation of the order
		Date deliveryDate = BillDAO.addDays(d,3);

		dbBill.updatePaymentState(b, true);
		dbBill.updateOrderState(b, validated);
		dbBill.setDeliveryDate(b, deliveryDate);
	}
}