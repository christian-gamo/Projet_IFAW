package services.shop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;

/**
 * Servlet called to display the vehicle inventory (all vehicles, available or not)
 * <p>
 * Only accessible by admin
 * @author Darlène
 *
 */
@WebServlet("/Inventory")
public class Inventory extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inventory() {
        super();
    }

	/**
	 * doGet method called on clicking the inventory manager button on the navbar in the administrator's space section.
	 * <p>
	 * Sets a session attribute containing all vehicles, available or not in the database.
	 * Forwards user to vehicle inventory page, where you can add, edit or delete a vehicle.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		if(session.getAttribute("inventoryVehicles") != null) {
		     session.removeAttribute("inventoryVehicles");
		 }

		session.setAttribute("inventoryVehicles",CatalogData.getInstance().
				getVehicles());

		this.getServletContext().getRequestDispatcher("/vehicleinventory.jsp").forward(request, response);

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
}
