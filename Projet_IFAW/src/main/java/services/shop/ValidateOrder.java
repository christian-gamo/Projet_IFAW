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
 * Servlet called to validate an order in the sql database (table bill)
 * <p>
 * Only accessible by admin
 * @author Christian
 */
@WebServlet("/ValidateOrder")
public class ValidateOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ValidateOrder() {
        super();
    }

	/**
	 * doGet method called on bill manager page when clicking on the validate button for a chosen bill.
	 * <p>
	 * Validates chosen order in the sql database. Does it by retrieving id of bill through request parameters.
	 * Forwards user to bill manager page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		int id_b = Integer.parseInt(request.getParameter("id_b"));
		CatalogData.getInstance().validateOrder(id_b);

		this.getServletContext().getRequestDispatcher("/BillManager").forward(request, response);


	}
}
