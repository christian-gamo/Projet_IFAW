package services.authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * Servlet called to log out from the app to stop the current connected user session
 *
 * @author Yassine
 *
 */
@WebServlet("/Logout")// Annotation with URL pattern to call the servlet e.g. http://localhost:8070/Projet_IFAW/Login
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
    }

	/**
	 * doGet() method called when user tries to disconnect
	 * <p>
	 * Simply removes session attribute containing user object to disconnect him from the session.
	 * Also cancels current session.
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// cancels the current session
	    HttpSession session = request.getSession(false);
        //removes the session attributes and gives the user the possibility to log in again if he wants to
        if (session != null) {
            session.removeAttribute("user");
            session.setAttribute("hasLoggedOut", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/Login");
            dispatcher.forward(request, response);
        }
	}


}
