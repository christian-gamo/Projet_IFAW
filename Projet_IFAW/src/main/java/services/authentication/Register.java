package services.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistantData.dao.CatalogData;
import persistantData.users.User;
/**
 *
 *
 * @author Yassine
 *
 */
@WebServlet("/Register")// Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/Login
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
    }

	/**
	 * doGet() method called when user wants to register.
	 * <p>
	 * Forwards user to register page.
	 * Also retrieves a request parameter indicating if user wants to register as a company or a regular user.
	 *
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String isCompany = request.getParameter("isCompany");
		if (isCompany.equals("1")) {
			session.setAttribute("isCompany", true);
		} else {
			session.setAttribute("isCompany", false);
		}
		if(session.getAttribute("hasRegistered") != null) {
		     session.removeAttribute("hasRegistered");
		 }
		if(session.getAttribute("err") != null) {
		     session.removeAttribute("err");
		 }
		this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
	}

	/**
	 * doPost method called on form submit on register.jsp, when user has filled the register form
	 * <p>
	 * Retrieves form data to create a new user in the sql database.
	 * Forwards user to same page(catalog.jsp) after registering.
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//form data
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String lastName = request.getParameter("lastName");
		String firstName = request.getParameter("firstName");

		String companyName = "";
		//retrieve session attribute indicating if user wants to register as a company
		boolean isCompany = (boolean) session.getAttribute("isCompany");
		if(isCompany)
			companyName = request.getParameter("companyName");

		//only alphabetical characters in last name or first name
		if(User.isStringOnlyAlphabet(lastName) && User.isStringOnlyAlphabet(firstName)) {
			if(isCompany) {
				CatalogData.getInstance().createCompany(email, password, lastName, firstName,companyName);

			} else {
				CatalogData.getInstance().createUser(email, password, lastName, firstName);
			}

			session.setAttribute("hasRegistered", true);
			response.sendRedirect("register.jsp");

			if(session.getAttribute("err") != null) {
			     session.removeAttribute("err");
			 }
		}
		//else error is sent
		else {
			if(session.getAttribute("hasRegistered") != null) {
			     session.removeAttribute("hasRegistered");
			 }
			session.setAttribute("err","Your first and last name must contain alphabetical characters only");
			response.sendRedirect("register.jsp");
		}
	}
}

