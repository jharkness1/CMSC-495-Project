package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoimpl.UserProfileDaoImpl;
import models.LoginInfo;
import models.UserProfile;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// I will set this to true to test login button
	private boolean authenticated = true;
	private HttpSession session;

	/**
	 * Default constructor.
	 */
	public AuthenticateServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get the posted values from the login form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// here we would try to retrieve from the database user's info based on given
		// username
		// and it would return a LoginInfo object:
		LoginInfo loginInfo = new LoginInfo();
		// Add some code to authenticate user, or count failed login attempts
		// if user is positively authenticated, set session role to the user's role from
		// the database
		// I will set it as admin just to test
		// we entered admin user manually as the first user, hence I will set the id to
		// 1
		// in order to access this user's profile through accessProfile(i)
		if (authenticated) {
			loginInfo.setId(1);
			loginInfo.setRole("admin");
			// start session:
			// Create a session object if it is not yet created.
			session = request.getSession(true);
			// set session attribute named role to the value of user's role in the database:
			session.setAttribute("role", loginInfo.getRole());
			// retrieve user's profile for the home page:
			UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
			UserProfile ownProfile = userProfileDaoImpl.accessProfile(loginInfo.getId());
			// I will set this UserProfile object as a session attribute, therefore we will
			// be able to
			// access it throughout the session, until we logout
			session.setAttribute("ownProfile", ownProfile);
			response.setContentType("text/html;charset=UTF-8");
			// Send to the Home JSP page
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
		else {
			request.setAttribute("ErrorMessage",
					"Username or Password not registered. Try again.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
