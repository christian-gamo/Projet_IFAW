package services.shop;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.vehicles.Vehicle;

/**
 *
 * Servlet called to add a vehicle to the sql database (table vehicle).
 * <p>
 * Only accessible by admin.
 * @author Darlene
 *
 */
@WebServlet("/AddVehicle") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/AddVehicle
public class AddVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehicle() {
        super();
    }

	/**
	 * doGet method called on clicking add vehicle button in vehicle inventory manager.
	 * <p>
	 * Checks if vehicle has been added message exists, and if it does, deletes it.
	 * Forwards user to add vehicle page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("hasAdded") != null) {
		     session.removeAttribute("hasAdded");
		 }
		this.getServletContext().getRequestDispatcher("/addvehicle.jsp").forward(request, response);
	}

	/**
	 * doPost method called on form submit in vehicle add page. All fields are required.
	 * <p>
	 * Adds vehicle entry to database with data from filled form.
	 * It also sets a session variable for error or success message.
	 * Forwards user to same page(catalog.jsp) after adding vehicle to cart and displays the message.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
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

		Vehicle existing = CatalogData.getInstance().
				getVehicleByModel(Model);

		if(existing!=null) {
			session.setAttribute("hasAdded",false);
		}
		else {
			//Current date for date_stock (date when vehicle was added to the inventory)
			java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

			Object[] data = new Object[]{0,Type,Category,Brand,Model,Engine,Gearbox,Nb_seats,Description,Price,Picture,true,date,false,Stock};
			CatalogData.getInstance().addVehicle(data);
			session.setAttribute("hasAdded",true);
		}
		response.sendRedirect("addvehicle.jsp");


	}

}
