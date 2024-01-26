package services.shop;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.order.Bill;
import persistantData.vehicles.Vehicle;

/**
 * Servlet called to start the order process (from cart page to payment and shipping page)
 * <p>
 * Only accessible by client user (company or regular user)
 * @author Christian
 *
 */
@WebServlet("/PaymentShipping")
public class PaymentShipping extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PaymentShipping() {
        super();
    }


	/**
	 * doPost method called on form submit on the cart page, after clicking the checkout button
	 * <p>
	 * Retrieves form data from cart page (payment method, shipping country and bill amount)
	 * Sets session attributes containing form data
	 * Forwards user to payment shipping page.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();

		Map<Vehicle,Integer> cart = (Map<Vehicle,Integer>)session.getAttribute("cart");

		for (Vehicle key : cart.keySet()) {
			int quantity = Integer.parseInt(request.getParameter("quantity"+key.getID_V()));
			if(quantity<= key.getStock())
				cart.replace(key, quantity);
		}

		int cart_size = 0;
		for (int qty : cart.values()) {
		    cart_size+=qty;
		}

		session.setAttribute("cart_size",cart_size);

		String payment_method = request.getParameter("payment_method");
		String shipping_country = request.getParameter("shipping_country");

		double sum_price = 0;

		for (Vehicle v : cart.keySet()) {
		    sum_price += v.getPrice() * cart.get(v);
		}

		double tax_amount = Bill.shipping_fee(shipping_country);

		double total_amount = sum_price + tax_amount;

		session.setAttribute("payment_method", payment_method);
		session.setAttribute("shipping_country", shipping_country);
		session.setAttribute("total_amount", total_amount);
		session.setAttribute("tax_amount", tax_amount);

		this.getServletContext().getRequestDispatcher("/mypayment.jsp").forward(request, response);

	}

}
