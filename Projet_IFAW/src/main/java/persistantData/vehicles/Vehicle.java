package persistantData.vehicles;

import java.sql.Date;
/**
 * Abstract super class that models a vehicle object in the app.
 * This class cannot be instantiated, only the child classes car and
 * motorcycle which inherit from the vehicle class can be instantiated.
 * If we want to add a new category of vehicles to the program, it will be
 * possible, for instance, to create a truck class which extends the vehicle class.
 * Note also that the vehicle factory class will have to be modified.
 *
 * @author Yassine
 *
 */
public abstract class Vehicle {
	/**
	 * * @param ID corresponds to the ID of the vehicle (Car)
     * @param t corresponds to the vehicle type (Car)
     * @param c corresponds to the vehicle category (Van, Kart...)
     * @param br corresponds to the vehicle brand (example : Animal crossing)
     * @param m corresponds to the vehicle model (example : Nook's Van)
     * @param e corresponds to the vehicle engine (example : Gasoline, Electric)
     * @param g corresponds to the vehicle gearbox (example : Manual, Automatic)
     * @param n corresponds to the vehicle number of seats (example : 1 )
     * @param d corresponds to the vehicle description (example : It offers good acceleration and handling...)
     * @param p corresponds to the vehicle unit price (example : 150000,15)
     * @param pi corresponds to the vehicle picture (example : https://nookvanpicture.png)
     * @param a corresponds to the vehicle's availability (example : 1 if available, 0 otherwise)
     * @param da corresponds to the corresponds to the date of entry in stock of the vehicle
     * (in order to implement more easily the notion of sale ; example : 08-06-2022)
     * @param o corresponds to the vehicle on sale status (example : 1 if on sale, 0 otherwise)
     * @param s corresponds to the quantity of vehicles in stock (ex
	 */

	/** ID of the vehicle */
	private int ID_V;
	/** Vehicle type (Car or Motorcycle)*/
	private String type;
	/** Category of the vehicle (Van,Scooter...)*/
	private String category;
	/** Brand of the vehicle */
	private String brand;
	/** Model name of the vehicle */
	private String model;
	/** Engine of the vehicle (Gasoline or Electric) */
	private String engine;
	/** Gearbox of the vehicle (Manual or Automatic) */
	private String gearbox;
	/** Number of seats */
	private int nb_seats;
	/** Description of the vehicle*/
	private String description;
	/** Price of the vehicle*/
	private double price;
	/** Web link to the picture of the vehicle (hosted online)*/
	private String picture;
	/** Availability of the vehicle*/
	private boolean available;
	/** Date of stock (when the vehicle was added in the database)*/
	private Date date_stock;
	/** On sale status*/
	private boolean on_sale;
	/** Quantity in stock*/
	private int stock;

	/**
	 * Getter getID_V()
	 * <p>
	 * Allows to get the ID of a given vehicle
	 *
	 * @return an integer, that represents the ID of a given vehicle
	 */
	public int getID_V() {
		return ID_V;
	}

	/**
	 * Setter setID_V()
	 * <p>
	 * Allows to set the ID of a given vehicle
	 *
	 * @param iD_V, the vehicle ID
	 */
	public void setID_V(int iD_V) {
		ID_V = iD_V;
	}

	/**
	 * Getter getBrand()
	 * <p>
	 * Allows to get the brand of a given vehicle
	 *
	 * @return a string, that represents the brand of a given vehicle
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * Setter setBrand()
	 * <p>
	 * Allows to set the brand of a given vehicle
	 *
	 * @param brand, the brand of the vehicle
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Getter getModel()
	 * <p>
	 * Allows to get the model of a given vehicle
	 *
	 * @return a string, that represents the model of a given vehicle
	 */
	public String getModel() {
		return model;
	}

	/**
	 * Setter setModel()
	 * <p>
	 * Allows to set the model of a given vehicle
	 *
	 * @param model which is the model of the vehicle
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * Getter getEngine()
	 * <p>
	 * Allows to get the engine of a given vehicle
	 *
	 * @return a string, that represents the engine of a given vehicle
	 */
	public String getEngine() {
		return engine;
	}

	/**
	 * Setter setEngine()
	 * <p>
	 * Allows to set the engine of a given vehicle
	 *
	 * @param engine which is the engine of the vehicle
	 */
	public void setEngine(String engine) {
		this.engine = engine;
	}

	/**
	 * Getter getGearbox()
	 * <p>
	 * Allows to get the gearbox of a given vehicle
	 *
	 * @return a string, that represents the gearbox of a given vehicle
	 */
	public String getGearbox() {
		return gearbox;
	}

	/**
	 * Setter setGearbox()
	 * <p>
	 * Allows to set the gearbox of a given vehicle
	 *
	 * @param gearbox which is the gearbox of the vehicle
	 */
	public void setGearbox(String gearbox) {
		this.gearbox = gearbox;
	}

	/**
	 * Getter getNb_seats()
	 * <p>
	 * Allows to get the number of seats of a given vehicle
	 *
	 * @return an integer, that represents the number of seats of a given vehicle
	 */
	public int getNb_seats() {
		return nb_seats;
	}

	/**
	 * Setter setNb_seats()
	 * <p>
	 * Allows to set the number of seats of a given vehicle
	 *
	 * @param nb_seats which is the number of seats of the vehicle
	 */
	public void setNb_seats(int nb_seats) {
		this.nb_seats = nb_seats;
	}

	/**
	 * Getter getDescription()
	 * <p>
	 * Allows to get the description of a given vehicle
	 *
	 * @return a string, that represents the description of a given vehicle
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setter setDescription()
	 * <p>
	 * Allows to set the description of a given vehicle
	 *
	 * @param description which is the new description of the vehicle
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Getter getPrice()
	 * <p>
	 * Allows to get the price of a given vehicle
	 *
	 * @return a double, that represents the price of a given vehicle
	 *
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Setter setPrice()
	 * <p>
	 * Allows to set the price of a given vehicle
	 *
	 * @param price, the price of the vehicle
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Getter getPicture()
	 * <p>
	 * Allows to get the picture of a given vehicle
	 *
	 * @return a string, that represents the picture of a given vehicle
	 */
	public String getPicture() {
		return picture;
	}

	/**
	 * Setter setPicture()
	 * <p>
	 * Allows to set the picture of a given vehicle
	 *
	 * @param picture which is the picture of the vehicle
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}

	/**
	 * Getter isAvailable()
	 * <p>
	 * Allows to get the availability of a given vehicle
	 *
	 * @return a boolean, that represents the availability of a given vehicle (true (1) is available, false (0) otherwise)
	 */
	public boolean isAvailable() {
		return available;
	}

	/**
	 * Setter setAvailable()
	 * <p>
	 * Allows to set the availability of a given vehicle
	 *
	 * @param available which is the availability of the vehicle
	 */
	public void setAvailable(boolean available) {
		this.available = available;
	}

	/**
	 * Getter getDate_stock()
	 * <p>
	 * Allows to get the date of entry in stock of the given vehicle
	 *
	 * @return a date, that represents the date of entry in stock of the given vehicle
	 */
	public Date getDate_stock() {
		return date_stock;
	}
	/**
	 * Setter setDate_stock()
	 * <p>
	 * Allows to set the date of entry in stock of a given vehicle
	 *
	 * @param date_stock which is the date of entry in stock of the vehicle
	 */
	public void setDate_stock(Date date_stock) {
		this.date_stock = date_stock;
	}

	/**
	 * Getter getType()
	 * <p>
	 * Allows to get the type of a given vehicle
	 *
	 * @return a string, that represents the vehicle type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter setType()
	 * <p>
	 * Allows to set the type of a given vehicle
	 *
	 * @param type which is the vehicle type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter getCategory()
	 * <p>
	 * Allows to get the category of a given vehicle
	 *
	 * @return a string, that represents the category of a given vehicle
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Setter setCategory()
	 * <p>
	 * Allows to set the category of a given vehicle
	 *
	 * @param category which is the vehicle category
	 */
	public void setCategory(String category) {
		this.category = category;
	}

	/**
	 * Getter isOn_sale()
	 * <p>
	 * Allows to get the on sale status of a given vehicle
	 *
	 * @return a boolean, that represents the sale status of a given vehicle (true (1) is on sale, false (0) otherwise)
	 */
	public boolean isOn_sale() {
		return on_sale;
	}

	/**
	 * Setter setOn_sale()
	 * <p>
	 * Allows to set the on sale status of a given vehicle
	 *
	 * @param on_sale which is the on sale status of the vehicle
	 */
	public void setOn_sale(boolean on_sale) {
		this.on_sale = on_sale;
	}

	/**
	 * Getter getStock()
	 * <p>
	 * Allows to get the stock of a given model of vehicle
	 *
	 * @return an integer, that represents the stock of a given model of vehicle
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Setter setStock()
	 * <p>
	 * Allows to set the stock of a given model of vehicle
	 *
	 * @param stock which is the stock of the model of vehicle
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * equals() method
	 * <p>
	 * This method checks if vehicle object is equal to another by comparing their ID_V attributes.
	 * Useful for checking if the vehicle already exists in the cart, the identifier of the model of vehicle
	 * which we want to check the presence in the cart being a key to recover in a hashmap, we must override
	 * the equals function to check this existence. This allows us not to duplicate the line in the user interface
	 * if the chosen vehicle model is already present in the cart, only the quantity will be updated
	 *
	 * @param o which is the other vehicle we compare it with
	 * @return true if the vehicle model is already in the cart, false otherwise.
	 *
	 */
	@Override
    public boolean equals(Object o) {
        return this.ID_V == ((Vehicle) o).getID_V();
    }

	/**
	 * hashcode() method
	 * <p>
	 * Hashcode value associated with the vehicle.
	 * Necessary to override because of hashmap.containsKey() method, and prevent duplicates.
	 * @return ID_V id of vehicle
	 */
	@Override
	public int hashCode() {
	    return ID_V;
	}

}
