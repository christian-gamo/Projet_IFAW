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
 * Servlet called to delete vehicle from the database
 * Only accessible by admin
 * <p>
 * URL pattern is followed by request parameter id_v:
 * /DeleteVehicle?id_v=...
 * @author Darlène
 *
 */
@WebServlet("/DeleteVehicle") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/DeleteVehicle
public class DeleteVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteVehicle() {
        super();
    }

	/**
	 * doGet method called by clicking bin icon on chosen vehicle in inventory page
	 * <p>
	 * Deletes chosen vehicle from the sql database. Does it by retrieving id of vehicle through request parameters.
	 * Forwards user to inventory page again.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//Request parameter id_v
		String id_string = request.getParameter("id_v");

		CatalogData.getInstance().deleteVehicle(Integer.parseInt(id_string));
		this.getServletContext().getRequestDispatcher("/Inventory").forward(request, response);

	}
}
