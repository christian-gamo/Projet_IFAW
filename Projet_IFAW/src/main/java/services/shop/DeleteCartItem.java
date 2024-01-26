package services.shop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.vehicles.Vehicle;

/**
 * Servlet called to delete a cart item
 * Only accessible by client user (company or regular user)
 * @author Christian
 *
 */
@WebServlet("/DeleteCartItem") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/DeleteCartItem
public class DeleteCartItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCartItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * doGet method called by clicking the bin icon on the chosen vehicle in cart page.
	 * <p>
	 * Deletes chosen vehicle from the cart. Does it by retrieving cart session attribute and removing vehicle from it.
	 * Updates cart size after operation.
	 * Forwards user to cart page again.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String id_string = request.getParameter("id_v");
		Map<Vehicle,Integer> cart = (Map<Vehicle,Integer>) session.getAttribute("cart");
		Vehicle v = CatalogData.getInstance().getVehicleByID(Integer.parseInt(id_string));

		for (Vehicle key : cart.keySet()) {
		    if(v.equals(key))
		    	cart.remove(key);
		}

		int cart_size = 0;
		for (int qty : cart.values()) {
		    cart_size+=qty;
		}

		session.setAttribute("cart_size",cart_size);
		session.setAttribute("cart",cart);

		this.getServletContext().getRequestDispatcher("/mycart.jsp").forward(request, response);
	}
}
