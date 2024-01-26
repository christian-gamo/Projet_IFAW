package persistantData.vehicles;

import java.sql.Date;
/**
 * Class made to instantiate a Vehicle object.
 * Follows the Factory design pattern.
 * If a new class extending the Vehicle class is created, this class must be modified too.
 *
 * @author Christian | Darlène | Yassine
 *
 */
public class VehicleFactory {

	/**
	 * Method used to create the object which extends the Vehicle class.
	 * Current types of vehicles implemented: Car and Motorcycle.
	 * @param data : Array of Object containing all data relevant to create the object.
	 * @return Vehicle object which class extends Vehicle (Car or Motorcycle).
	 */
	public Vehicle createVehicle(Object[] data) {
		switch((String) data[1]) {
			case "Car":
				return new Car(
						  (int) data[0], //ID of vehicle (ID_V)
					      (String) data[1], // Type of the vehicle
						  (String) data[2], // Category
						  (String) data[3], // Brand
					      (String) data[4], // Model
					      (String) data[5], // Engine
					      (String) data[6], // Gearbox
						  (int) data[7], // Number of seats
						  (String) data[8], // Description
						  (double) data[9], // Unit Price
						  (String) data[10], // Picture
						  (boolean) data[11], // Availability
						  (Date) data[12], // Date of entry in stock
						  (boolean)data[13], // Sale status (on sale or not)
						  (int)data[14]); // Quantity in stock
		case "Motorcycle":
				return new Motorcycle(

						  (int) data[0], //ID of vehicle (ID_V)
					      (String) data[1], // Type of the vehicle
						  (String) data[2], // Category
						  (String) data[3], // Brand
					      (String) data[4], // Model
					      (String) data[5], // Engine
					      (String) data[6], // Gearbox
					      (int) data[7], // Number of seats
						  (String) data[8], // Description
						  (double) data[9], // Unit Price
						  (String) data[10], // Picture
						  (boolean) data[11], // Availability
						  (Date) data[12], // Date of entry in stock
						  (boolean)data[13], // Sale status (on sale or not)
						  (int)data[14]); // Quantity in stock
		default:
				return null;
		}
	}
}
