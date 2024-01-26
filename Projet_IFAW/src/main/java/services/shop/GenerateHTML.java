package services.shop;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.order.Bill;
import persistantData.users.User;
import persistantData.vehicles.Vehicle;

/**
 * Servlet called to display vehicle document (order form, registration form, transfer certificate)
 * <p>
 * Only accessible by client user (company or regular user)
 * @author Darlène
 *
 */
@WebServlet("/GenerateHTML")
public class GenerateHTML extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GenerateHTML() {
        super();
    }

	/**
	 * doGet method called on clicking on document icon on the chosen bill/order on the my orders page.
	 * <p>
	 * Generates web page for chosen document.
	 * Does it by retrieving id of bill (id_b) and index of chosen document (doc) through request parameters.
	 * Sets session attributes containing data necessary to generate chosen document (vehicles, quantity, registration number...)
	 * Registration number is generated according to data from bill and user.
	 * Uses a list of map entries in order to bypass duplicates issue of standard Hashmap, as all vehicles need
	 * a unique registration number, same model or not.
	 * Forwards user to document page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//retrieve bill with id_b request parameter which is the id of the bill in the database
		Bill bill = CatalogData.getInstance().getBillByID(Integer.parseInt(request.getParameter("id_b")));
		//set session attribute containing chosen bill
		session.setAttribute("bill", bill);

		//retrieve user
		User user = (User) session.getAttribute("user");

		//hashmap containing a list of pairs (vehicle object, quantity)
		//contains the vehicles of the bill
		Map<Vehicle,Integer> vehicles = new HashMap<>();
		for(Map.Entry<Integer,Integer> vehicle : bill.getVehicles().entrySet()) {
			vehicles.put(CatalogData.getInstance().getVehicleByID(vehicle.getKey()),(int)vehicle.getValue());
		}

		//List of simple map entries to have a list of pairs with duplicates allowed (vehicle object, registration number)
		//contains the vehicles of the bill
		List<AbstractMap.SimpleEntry<Vehicle,String>> vehiclesUniqueID = new ArrayList<>();

		//For all map entries of Bill object (Id,Quantity)
		for(Map.Entry<Integer,Integer> vehicle : bill.getVehicles().entrySet()) {
			//i first takes value of quantity of current vehicle model
			//then decrements
			for(int i = vehicle.getValue();i>0;i--) {

				//Example format of vehicle registration number:
				// AA1-234-BBC
				// registration number is generated from user and bill data
				StringBuilder code = new StringBuilder();
				String lastNameCode;
				String firstNameCode;
				String vehicleCode;

				//First two letters of last name
				if (user.getLastName().length() > 2)
				    lastNameCode = user.getLastName().substring(0, 2);
				else
					lastNameCode = user.getLastName();
				code.append(lastNameCode);

				//id of bill in database
				code.append(bill.getId_B());

				code.append("-"); //Here you have 'AA1-'

				code.append(user.getID_U()); //id of user
				code.append(CatalogData.getInstance().getVehicleByID(vehicle.getKey()).getID_V()); //id of vehicle
				code.append(i); //index of for loop
				code.append("-"); //here you have 'AA1-234-'

				//First two letters of vehicle name
				String vehicleName = CatalogData.getInstance().getVehicleByID(vehicle.getKey()).getModel();
				if (vehicleName.length() > 2)
				    vehicleCode = vehicleName.substring(0, 2);
				else
					vehicleCode = vehicleName;
				code.append(vehicleCode);

				//first letter of user first name
				if (user.getFirstName().length() > 1)
				    firstNameCode = user.getFirstName().substring(0, 1);
				else
					firstNameCode = user.getFirstName();
				code.append(firstNameCode);

				// In the end you have 'AA1-234-BBC'
				vehiclesUniqueID.add(new AbstractMap.SimpleEntry<>(
						CatalogData.getInstance().getVehicleByID(vehicle.getKey()),code.toString().toUpperCase()));
			}
		}

		//shipping fee
		double tax_amount = Bill.shipping_fee(bill.getCountry());

		//setting session attributes
		session.setAttribute("tax_amount_receipt", tax_amount);
		session.setAttribute("vehiclesReceipt", vehicles);
		session.setAttribute("vehiclesReceiptID", vehiclesUniqueID);

		//forwards user to chosen document's web page with request parameter doc in url
		int doc = Integer.parseInt(request.getParameter("doc"));
		switch(doc) {
		case 0 :
			this.getServletContext().getRequestDispatcher("/orderform.jsp").forward(request, response);
			break;
		case 1 :
			this.getServletContext().getRequestDispatcher("/vehicleregistrationform.jsp").forward(request, response);
			break;
		case 2 :
			this.getServletContext().getRequestDispatcher("/vehicletransfercertificate.jsp").forward(request, response);
			break;
		}
	}
}
