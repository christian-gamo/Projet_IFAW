package services.shop;

import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.order.PaymentMethod;
import persistantData.users.User;
import persistantData.vehicles.Vehicle;
/**
 * Servlet called to add an order in the sql database (table bill)
 * <p>
 * Only accessible by client user (company or regular user)
 * @author Christian
 *
 */
@WebServlet("/AddBill") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/AddBill
public class AddBill extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBill() {
        super();
    }


	/**
	 * doPost method called on form submit of mypayment.jsp.
	 * <p>
	 * Adds bill entry after order confirmation by user in the database. Decreases vehicles stock according to the order.
	 * Forwards user to "thank you for ordering" page.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		Map<Vehicle,Integer> cart = (Map<Vehicle,Integer>)session.getAttribute("cart");

		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		String payment_method = (String) session.getAttribute("payment_method");
		String country = (String) session.getAttribute("shipping_country");

		//We assume that if payment method is credit card, order is immediately paid
		boolean payment_state = false;
		PaymentMethod pm;
		if(payment_method.equals("Credit Card")) {
			pm = PaymentMethod.CREDIT_CARD;
			payment_state = true;
		}
		else
			pm = PaymentMethod.CREDIT_REQUEST;

		double bill_amount = (double) session.getAttribute("total_amount");
		User user = (User) session.getAttribute("user");
		int id_u = user.getID_U();


		Map<Integer,Integer> vehicles = new HashMap<>();

		//Update stock
		for (Vehicle key : cart.keySet()) {
			vehicles.put(key.getID_V(), cart.get(key));
			CatalogData.getInstance().updateVehicleStock(key, key.getStock()-cart.get(key) >= 0 ? key.getStock()-cart.get(key) : 0);
		}

		//We put data of future database entry in an array of objects to properly process it
		Object[] data = new Object[]{0,date,country,payment_state,pm,bill_amount,id_u,vehicles};
		int id_b = CatalogData.getInstance().addBill(data);

		session.removeAttribute("cart");
		session.removeAttribute("cart_size");

		session.setAttribute("lastOrder", CatalogData.getInstance().getBillByID(id_b));

		this.getServletContext().getRequestDispatcher("/thankyou.jsp").forward(request, response);
	}

}
