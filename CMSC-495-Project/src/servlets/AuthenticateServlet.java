package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import daoimpl.LoginInfoDaoImpl;
import daoimpl.UserProfileDaoImpl;
import models.LoginInfo;
import models.UserProfile;
import utilities.Validator;

/**
 * Servlet implementation class AuthenticateServlet
 */
@WebServlet("/AuthenticateServlet")
public class AuthenticateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// set the maximum allowed number of failed login attempts
	private static final int MAX_NO_FAILED_LOGIN = 3;
	// set the lockout time to 10 minutes (600 sec)
	private static final int LOCKOUT_PERIOD = 600;
	// I will set the authenticated value to false by default
	private boolean authenticated = false;
	private HttpSession session = null;
	private LoginInfo loginInfo = null;

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
		// check if all required fields have been filled out
		if (username.length() > 0 || password.length() > 0) {
			// if all required fields have been filled out, validate all fields
			if (Validator.validateOnlyLettersAndNumbers(username) && Validator.validatePassword(password)) {
				// if user input valid, check if user exists
				LoginInfoDaoImpl loginInfoDaoImpl = new LoginInfoDaoImpl();
				loginInfo = loginInfoDaoImpl.getLoginInfo(username);
				// if loginInfo object is still null, it means that no such username was found
				// in the database
				if (loginInfo != null) {
					// if username was found, check if account is not locked
					if (loginInfoDaoImpl.isLocked(loginInfo, MAX_NO_FAILED_LOGIN, LOCKOUT_PERIOD)) {
						request.setAttribute("ErrorMessage", "Your Account is Locked!");
						RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
						dispatcher.forward(request, response);
					} else { // if the account is not locked
						// update loginInfo, if the account got unlocked
						// (isLocked can reset the value of failed_login_attempts in database)
						loginInfo = loginInfoDaoImpl.getLoginInfo(username);
						// try to verify user's password from the login form
						authenticated = loginInfoDaoImpl.authenticate(loginInfo, password);
						if (authenticated) {
							// if the account was not locked, and username and password match!
							// start authenticated session
							// Create a session object if it is not yet created.
							session = request.getSession(true);
							// set session attribute named role to the value of user's role in the
							// database:
							session.setAttribute("role", loginInfo.getRole());
							// retrieve user's profile from the database, for the home page:
							UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
							UserProfile ownProfile = userProfileDaoImpl.accessProfile(loginInfo.getId());
							// I will set this UserProfile object as a session attribute, therefore we
							// will be able to access it throughout the session, until we logout
							session.setAttribute("ownProfile", ownProfile);
							// Send to the Home JSP page
							RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
							dispatcher.forward(request, response);
						} // end successful authentication
						else { // if password did not match
							request.setAttribute("ErrorMessage", "Invalid Username or Password. Please try again.");
							RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
							dispatcher.forward(request, response);
						}

					} // end if the account was not locked

				} else { // no such username was found in the database
					request.setAttribute("ErrorMessage", "Invalid Username or Password. Please try again.");
					RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
					dispatcher.forward(request, response);
				}

			} // end if positively validated
			else { // show error message
				request.setAttribute("ErrorMessage", "Invalid Username or Password Input. Please try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
			}
		} // end if all required fields have been filled out
		else {
			request.setAttribute("ErrorMessage", "Both fields are required. Please try again.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
	} // end doPost

}
