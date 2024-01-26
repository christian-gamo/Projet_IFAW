package services.shop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.vehicles.Vehicle;

/**
 * Servlet called to consult the catalog containing the list of available vehicles.
 * You can add vehicles to the cart or filter the list.
 * <p>
 * Accessible by both admin and client, but content of page forwarded changes accordingly (see catalog.jsp for details)
 *
 * @author Christian
 *
 */
@WebServlet("/Catalog")
public class Catalog extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Catalog() {
        super();
    }

	/**
	 * doGet method called on accessing catalog page request.
	 * <p>
	 * Sets session variable containing list of available vehicles if it doesn't exists. Necessary to display the vehicles.
	 * Checks if vehicle has been added message exists, and if it does, deletes it.
	 * Forwards user to catalog page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("vehicles") == null) {
			session.setAttribute("vehicles",CatalogData.getInstance().getAvailableVehicles());
		 }

		if(session.getAttribute("catalog_success") != null) {
			session.removeAttribute("catalog_success");
		 }

		if(session.getAttribute("catalog_error") != null) {
			session.removeAttribute("catalog_error");
		 }

		this.getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);

		//removes session variable just in case
		session.removeAttribute("vehicles");

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * doPost method called on form submit on catalog.jsp, when selecting filters for relevant vehicles
	 * <p>
	 * Retrieves form data to set a new filtered list of available vehicles in the vehicles session variable.
	 * Forwards user to same page(catalog.jsp) after filtering.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String car = request.getParameter("Car");
		String motorcycle = request.getParameter("Motorcycle");
		String electric = request.getParameter("Electric");
		String gasoline = request.getParameter("Gasoline");
		String manual = request.getParameter("Manual");
		String automatic = request.getParameter("Automatic");
		String nb_seats = request.getParameter("nb_seats");
		String on_sale = request.getParameter("on_sale");
		String normal_price = request.getParameter("normal_price");
		String price = request.getParameter("price");

		//Arrays of object which represents the filters
		//Will be used when creating the sql query
		//Object 1 : true if its a string of characters (to add necessary '' in query)
		//Object 2 : name of field
		//Object 3 : first string value (null if checkbox is not checked or no value selected)
		//Object 4 : second string value (null if checkbox is not checked or no value selected)
		Object[] type = new Object[]{true,"=","Type",car,motorcycle};
		Object[] engine = new Object[]{true,"=","Engine",electric,gasoline};
		Object[] gearbox = new Object[]{true,"=","Gearbox",manual,automatic};
		Object[] nbseats = new Object[]{false,"=","Nb_seats",nb_seats,null};
		Object[] sale = new Object[]{false,"=","On_sale",on_sale,normal_price};
		Object[] price_amount = new Object[]{false,"<=","Price",price,null};

		//list of filters
		List<Object[]> filters = new ArrayList<>();
		filters.add(type);
		filters.add(engine);
		filters.add(gearbox);
		filters.add(nbseats);
		filters.add(sale);
		filters.add(price_amount);

		List<Vehicle> filtered = CatalogData.getInstance().filterVehicles(filters);

		session.setAttribute("vehicles",filtered);

		this.getServletContext().getRequestDispatcher("/catalog.jsp").forward(request, response);

	}
}
