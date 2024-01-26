package services.shop;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.vehicles.Vehicle;
/**
 * Servlet called to add a vehicle to the cart in the catalog
 * <p>
 * Only accessible by client user (company or regular user)
 * @author Christian
 */
@WebServlet("/AddToCart") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/AddToCart
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddToCart() {
        super();
    }

	/**
	 * doPost method called on form submit on catalog.jsp, on button add to cart click.
	 * <p>
	 * Adds vehicle to cart with chosen quantity. Chosen quantity must be equal or less than current stock.
	 * Cart is stored in a session variable. It also sets a session variable for error or success message.
	 * Forwards user to same page(catalog.jsp) after adding vehicle to cart and displays the message.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		if(session.getAttribute("catalog_success") != null) {
			session.removeAttribute("catalog_success");
		 }

		if(session.getAttribute("catalog_error") != null) {
			session.removeAttribute("catalog_error");
		 }

		Map<Vehicle,Integer> cart;

		// We use existing cart if it exists
		if(session.getAttribute("cart") == null)
			cart = new ConcurrentHashMap <>(); //ConcurrentHashMap instead of normal HashMap to prevent concurrent access/modification exceptions
		else
			cart = (Map<Vehicle,Integer>)session.getAttribute("cart");

		int id = Integer.parseInt(request.getParameter("id_vehicle"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Vehicle v = CatalogData.getInstance().getVehicleByID(id);

		//if vehicle already exists in cart, we change the quantity
		if(cart.containsKey(v)) {
			if(cart.get(v)+quantity<= v.getStock()) {
				cart.replace(v, cart.get(v)+quantity);
				session.setAttribute("catalog_success", "Successfully added to the cart !");
			}
			else
				session.setAttribute("catalog_error", "Quantity exceeds current stock !");
		}

		//else we add it in the cart
		else {
			if(quantity<= v.getStock()) {
				cart.put(v, quantity);
				session.setAttribute("catalog_success", "Successfully added to the cart !");
			}
			else
				session.setAttribute("catalog_error", "Quantity exceeds current stock !");
		}

		session.setAttribute("cart",cart);

		//cart size session variable for cart size indicator icon
		int cart_size = 0;
		for (int qty : cart.values()) {
		    cart_size+=qty;
		}
		session.setAttribute("cart_size",cart_size);

		this.getServletContext().getRequestDispatcher("/Catalog").forward(request, response);
	}

}
