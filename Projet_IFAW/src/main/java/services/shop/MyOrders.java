package services.shop;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.order.Bill;
import persistantData.users.User;

/**
 * Servlet called to display all orders of current user
 * <p>
 * Only accessible by client user (company or regular user)
 * @author Darlène
 *
 */
@WebServlet("/MyOrders")
public class MyOrders extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyOrders() {
        super();
    }

	/**
	 * doGet method called on clicking orders button on navbar.
	 * <p>
	 * Sets a session variable containing all orders of current user in the database.
	 * Forwards user to orders page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//retrieve user
		User user = (User) session.getAttribute("user");
		//retrieve id of user
		int id_u = user.getID_U();
		//retrieve all user's bills
		List<Bill> b = CatalogData.getInstance().getBillsByUserID(id_u);
		//set session attribute containing the bills
		session.setAttribute("user_bills", b);
		//forward user to orders page
		this.getServletContext().getRequestDispatcher("/myorders.jsp").forward(request, response);

	}

}
