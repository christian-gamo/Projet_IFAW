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
 * Servlet called to log into the app as a user, an admin or a company (database table user)
 *
 * @author Yassine
 *
 */
@WebServlet("/Login") // Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/Login
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * doGet() method
	 * <p>
	 * Sends current user who wants to connect to his user account to login page.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		//redirection to the login page if the HasLoggedOut session attribute is null
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

		//remove the session attribute hasLoggedOut if it's different from null to reset it at each session start
		if(session.getAttribute("hasLoggedOut") != null) {
		     session.removeAttribute("hasLoggedOut");
		 }
	}

	/**
	 * doPost() method called on form submit of login.jsp
	 * <p>
	 * Allows to create a new user session (allows to connect the user) by verifying
	 * his credentials (email + password) in the database and set a session attribute containing
	 * a user object.
	 * Sets booleans as session attributes to be able to tell if user is admin, company, or regular user.
	 *
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		//reset the cart session attribute
		if(session.getAttribute("cart") != null) {
		     session.removeAttribute("cart");
		}
		//reset the cart size session attribute
		if(session.getAttribute("cart_size") != null) {
		     session.removeAttribute("cart_size");
		}

		String login = request.getParameter("email");
		String password = request.getParameter("password");
		// A try catch block that allows to check that the user who
		//tries to connect has entered credentials which exists in the database
		try {
			User u = CatalogData.getInstance().getUser(login, password);

			if(u!=null) {
				session.setAttribute("user", u);
				// Check if the connected user is an admin and set a session attribute isAdmin
				// in order to display all the admin features of the app
				boolean b = CatalogData.getInstance().isAdmin(u);
				session.setAttribute("isAdmin", b);
				// Check if the connected user is a company and set a session attribute isCompany
			    // in order to display all the company features of the app
				boolean c = CatalogData.getInstance().isCompany(u);
				session.setAttribute("isCompany", c);
				// Remove the session attribute error, because if the user session is different from null
				// then there is no connection issues
				if(session.getAttribute("error") != null) {
				     session.removeAttribute("error");
				 }
				//Redirection to the login page with a success message
				response.sendRedirect("/Projet_IFAW/Login");
			}
			else {
				//Redirection to the login page with an error message, the error is due to wrong credentials
				response.sendRedirect("/Projet_IFAW/Login");
				session.setAttribute("error","Invalid credentials, please try again");
			}
		} catch (Exception e) {
			// Redirection to the login page with an error message, it takes in charge all the other types of exception
			response.sendRedirect("/Projet_IFAW/Login");
		}
	}

}
