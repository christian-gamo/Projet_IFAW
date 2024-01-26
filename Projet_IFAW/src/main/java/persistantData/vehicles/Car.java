package persistantData.vehicles;

import java.sql.Date;
/**
 * Class that models a car object in the app.
 * Since a car is by extent a vehicle, the car class inherits from the vehicle class.
 * As such, the car class is not that different from the vehicle class from a code point of view.
 * But conceptually, they are different objects, so we have created an inheritance relation between them.
 * Note that an instance of the object car represents a car model instead of a single car in the catalog
 *
 * @author Yassine
 *
 */
public class Car extends Vehicle{
    /**
     * Public constructor of the Car class which allows to create a new Car object
	 *
     * @param ID corresponds to the ID of the vehicle (Car)
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
     * @param s corresponds to the quantity of vehicles in stock (example : 200)
     */
	public Car(int ID,String t, String c, String br, String m, String e, String g,
			   int n, String d, double p, String pi, boolean a, Date da,boolean o,int s) {
		this.setID_V(ID);
		this.setType(t);
		this.setCategory(c);
		this.setBrand(br);
		this.setModel(m);
		this.setEngine(e);
		this.setGearbox(g);
		this.setNb_seats(n);
		this.setDescription(d);
		this.setPrice(p);
		this.setPicture(pi);
		this.setAvailable(a);
		this.setDate_stock(da);
		this.setOn_sale(o);
		this.setStock(s);

	}
}
