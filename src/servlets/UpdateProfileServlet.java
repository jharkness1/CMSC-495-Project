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
import models.UserProfile;
import utilities.LogWriter;
import utilities.Validator;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private UserProfile user = null;
	// variable that will determine whether the user is authorized to update profile
	// deny by default
	private boolean allowUpdate = false;
	private String loggedInUsername;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get all posted fields from the updateProfile form
		int idOfUpdatedUser = Integer.valueOf(request.getParameter("idOfUpdatedUser"));
		String oldUsername = request.getParameter("oldUsername");
		String oldEmail = request.getParameter("oldEmail");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String password_confirm = request.getParameter("password_confirm");
		String company = request.getParameter("company");
		String department = request.getParameter("department");
		String title = request.getParameter("title");
		String streetAddr = request.getParameter("streetAddr");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");
		String phone = request.getParameter("phone");

		// if the user is not admin he/she should not be allowed to update
		// any other profile that own profile
		// Therefore, retrieve role and own id from session variables:
		session = request.getSession(true);
		String role = (String) session.getAttribute("role");
		UserProfile loggedInUser = (UserProfile) session.getAttribute("ownProfile");
		int idOfLoggedInUser = loggedInUser.getId();
		// retrieve session username for logging purposes
		loggedInUsername = (String) session.getAttribute("username");
		if (!role.equals("admin")) {
			// if user is not admin check if the posted id equals own id
			if (idOfUpdatedUser == idOfLoggedInUser) {
				// if posted id equals own id, it means that non-admin user wants to update own
				// profile. In such case we should authorize user to do that
				allowUpdate = true;
			} else {
				// this means that regular user is trying to update someone else profile
				// In this case we should not authorize him/her to do that
				allowUpdate = false;
			}
		} // end if user was not admin
		else { // if user is admin
				// authorize admin user to edit any profile
			allowUpdate = true;
		}
		// now verify if user is authorized to update profile
		if (allowUpdate) {
			// check if all required fields have been filled out
			if (firstName.length() > 0 || lastName.length() > 0 || email.length() > 0 || username.length() > 0
					|| password.length() > 0 || password_confirm.length() > 0) {
				// if all required fields have been filled out, validate all fields
				if (Validator.validateOnlyLetters(firstName) && Validator.validateOnlyLetters(lastName)
						&& Validator.validateEmail(email) && Validator.validateOnlyLettersAndNumbers(username)
						&& Validator.validatePassword(password) && Validator.validatePassword(password_confirm)
						&& Validator.validateOnlyLettersAndNumbers(company)
						&& Validator.validateOnlyLettersAndNumbers(department)
						&& Validator.validateOnlyLettersAndNumbers(title) && Validator.validateAddress(streetAddr)
						&& Validator.validateOnlyLetters(city) && Validator.validateState(state)
						&& Validator.validateZip(zip) && Validator.validatePhone(phone)) {
					// create UserProfile object
					user = new UserProfile(firstName, lastName, email, company, department, title, streetAddr, city,
							state, zip, phone, username, password);
					UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
					// if all fields contain safe, acceptable characters, check if password_confirm
					// matches password
					if (password.equals(password_confirm)) {
						// check if user wants to change own email or username (avoid duplicates!)
						if (email.equals(oldEmail) && username.equals(oldUsername)) {
							// if user doesn't want to change neither username nor email
							// System.out.println("You are not changing email and username");
							// try to update profile
							String message = userProfileDaoImpl.updateProfile(user, oldUsername);
							// log
							if (message.equals("Success! Profile updated.")) {
								LogWriter.successfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
							} else {
								LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
							}
							request.setAttribute("ErrorMessage", message);
							// now check whether to update session attribute
							// if the update was performed on own profile, update session attribute, and
							// profile info on home page
							if (idOfUpdatedUser == idOfLoggedInUser) {
								// retrieve updated user's profile from the database, for the home page:
								UserProfile ownProfile = userProfileDaoImpl.accessProfile(idOfUpdatedUser);
								// update session attribute to take effect on home page
								session.setAttribute("ownProfile", ownProfile);
							}
						} // end if user didn't want to change email or username
						else {
							// if user wanted to change email or username
							// System.out.println("You want to change email or username");
							// check which one user wants to change and verify if there won't be any
							// duplicates in the database
							boolean emailApproved = false;
							boolean usernameApproved = false;
							if (!email.equals(oldEmail)) {
								// user would like to change email
								// System.out.println("You want to change email");
								// check if new, given email already exists
								if (userProfileDaoImpl.emailExists(email)) {
									// don't approve further update
									emailApproved = false;
								} else {
									// if new email does not exist in the database,
									// approve further update
									emailApproved = true;
								}
								// end approving email
							} // end if user would like to change email
							else { // user didn't want to change email
								emailApproved = true;
							} // end if user didn't want to change email
							if (!username.equals(oldUsername)) {
								// user would like to change username
								// System.out.println("You want to change username");
								// check if new, given username already exists
								if (userProfileDaoImpl.usernameExists(username)) {
									// don't approve further update
									usernameApproved = false;
								} else {
									// if new username does not exist in the database,
									// approve further update
									usernameApproved = true;
								}
								// end approving username
							} // end if user would like to change username
							else { // user didn't want to change username
								usernameApproved = true;
							} // end if user didn't want to change username
								// proceed with the update only if both email and username are approved
							if (emailApproved && usernameApproved) {
								// try to update
								// System.out.println("Good to go!");
								String message = userProfileDaoImpl.updateProfile(user, oldUsername);
								// log
								if (message.equals("Success! Profile updated.")) {
									LogWriter.successfulProfileUpdate(loggedInUsername,
											String.valueOf(idOfUpdatedUser));
								} else {
									LogWriter.unsuccessfulProfileUpdate(loggedInUsername,
											String.valueOf(idOfUpdatedUser));
								}
								request.setAttribute("ErrorMessage", message);
								// now check whether to update session attribute
								// if the update was performed on own profile, update session attribute, and
								// profile info on home page
								if (idOfUpdatedUser == idOfLoggedInUser) {
									// retrieve updated user's profile from the database, for the home page:
									UserProfile ownProfile = userProfileDaoImpl.accessProfile(idOfUpdatedUser);
									// update session attribute to take effect on home page
									session.setAttribute("ownProfile", ownProfile);
								}
							} // end if both email and username were approved
							else {
								// don't update! duplicate!
								// display an error message
								request.setAttribute("ErrorMessage",
										"Someone already registered given email or username. Try again.");
								// log
								LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
								LogWriter.recordError("Duplicate username or password.");
							}
						}
					} // end if password matched the password_confirm
					else {
						// display an error message
						request.setAttribute("ErrorMessage", "Confirmed password did not match your password.");
						// log
						LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
						LogWriter.recordError("Confirmed password did not match password.");
					} // end if passwords didn't match

				} // end if all fields were validated
				else {
					// if user input was not valid
					// display an error message
					request.setAttribute("ErrorMessage", "Wrong input.Try again.");
					// log
					LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
					LogWriter.recordError("Wrong input.");
				}

			} // end if all required fields have been filled out
			else {
				// display an error message
				request.setAttribute("ErrorMessage", "Required fields cannot be empty.Try again.");
				// log
				LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
				LogWriter.recordError("Required fields cannot be empty.");
			} // end if not all required fields have been filled
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} // end allowUpdate
		else { // if user was not authorized to perform update
				// redirect to home.jsp
			request.setAttribute("ErrorMessage", "Access denied.");
			// log
			LogWriter.unsuccessfulProfileUpdate(loggedInUsername, String.valueOf(idOfUpdatedUser));
			LogWriter.recordError("Access denied.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
	} // end doPost

}
