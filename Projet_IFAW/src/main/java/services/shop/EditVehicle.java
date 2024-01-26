package services.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.vehicles.Vehicle;
/**
 * Servlet called to edit vehicle from the database
 * Only accessible by admin
 * <p>
 * URL pattern is followed by request parameter id_vehicle:
 * /DeleteVehicle?id_vehicle=...
 * @author Darlène
 *
 */
@WebServlet("/EditVehicle")
public class EditVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditVehicle() {
		super();
	}

	/**
	 * doGet method called by clicking bin icon on chosen vehicle in inventory page
	 * <p>
	 * Sets a session attribute which contains a Vehicle object which has the data of the chosen vehicle.
	 * Does it by retrieving id of vehicle through request parameters.
	 * Forwards user to the edit vehicle page.
	 * Also removes edit success or failure message if it exists.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("hasEdited") != null) {
			session.removeAttribute("hasEdited");
		}

		//retrieve request parameter id_vehicle
		String id_string = request.getParameter("id_vehicle");
		//search for vehicle in database with ID
		Vehicle existingVehicle = CatalogData.getInstance().getVehicleByID(Integer.parseInt(id_string));
		//set the session attribute
		request.setAttribute("existingvehicle", existingVehicle);
		//forward user to edit page
		this.getServletContext().getRequestDispatcher("/editvehicle.jsp").forward(request, response);
	}

	/**
	 * doPost method called on form submit in vehicle edit page. All fields are required/used.
	 * <p>
	 * Edits vehicle entry to database with data from filled form.
	 * It also sets a session variable for error or success message (hasEdited).
	 * Forwards user to same page(catalog.jsp) after adding vehicle to cart and displays the message.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id_v = (int) session.getAttribute("id_vehicle");
		String Picture = request.getParameter("picture");
		String Type = request.getParameter("type");
		String Brand = request.getParameter("brand");
		String Model = request.getParameter("model");
		String Gearbox = request.getParameter("gearbox");
		String Category = request.getParameter("category");
		String Engine = request.getParameter("engine");
		int Nb_seats = Integer.parseInt(request.getParameter("nb_seats"));
		String Description = request.getParameter("description");
		double Price = Double.parseDouble(request.getParameter("price").replaceAll( "," , "." ));
		int Stock = Integer.parseInt(request.getParameter("stock"));

		session.removeAttribute("id_vehicule");

		Vehicle existingVehicle = CatalogData.getInstance().getVehicleByID(id_v);
		if(existingVehicle==null) {
			session.setAttribute("hasEdited",false);
		} else {
			session.setAttribute("hasEdited",true);
			CatalogData.getInstance().updateVehicle(existingVehicle, Type, Brand, Model, Gearbox, Category, Engine, Nb_seats, Description, Price, Picture, true, Stock);
		}
		this.getServletContext().getRequestDispatcher("/editvehicle.jsp").forward(request, response);

	}
}



