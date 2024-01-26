package services.shop;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Servlet called to access list of bills and manage them.
 * <p>
 * Only accessible by admin.
 *
 * @author Christian
 *
 */
@WebServlet("/BillManager") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/BillManager
public class BillManager extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillManager() {
        super();
    }

	/**
	 /**
	 * doGet method called on clicking bill manager button on navbar.
	 * <p>
	 * Sets a session variable containing all bills in the database.
	 * Forwards user to bill manager page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Bill> b = CatalogData.getInstance().getAllBills();
		Map<Bill,User> bills = new HashMap<>();

		for(Bill bill : b) {
			bills.put(bill, CatalogData.getInstance().getUserByID(bill.getId_U()));
		}

		session.setAttribute("bills", bills);

		this.getServletContext().getRequestDispatcher("/billmanager.jsp").forward(request, response);
	}
}
