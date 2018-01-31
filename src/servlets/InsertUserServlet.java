package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoimpl.UserProfileDaoImpl;
import models.UserProfile;
import utilities.Validator;

/**
 * Servlet implementation class InsertUserServlet
 */
@WebServlet("/InsertUserServlet")
public class InsertUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	// /**
	// * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	// * response)
	// */
	// protected void doGet(HttpServletRequest request, HttpServletResponse
	// response)
	// throws ServletException, IOException {
	// // TODO Auto-generated method stub
	// response.getWriter().append("Served at: ").append(request.getContextPath());
	// }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get all posted fields from the createAccount form
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

		// check if all required fields have been filled out
		if (firstName.length() > 0 || lastName.length() > 0 || email.length() > 0 || username.length() > 0
				|| password.length() > 0 || password_confirm.length() > 0) {
			// if all required fields have been filled out, validate all other fields
			if (Validator.validateOnlyLetters(firstName) && Validator.validateOnlyLetters(lastName)
					&& Validator.validateEmail(email) && Validator.validateOnlyLettersAndNumbers(username)
					&& Validator.validatePassword(password) && Validator.validatePassword(password_confirm)
					&& Validator.validateOnlyLettersAndNumbers(company)
					&& Validator.validateOnlyLettersAndNumbers(department)
					&& Validator.validateOnlyLettersAndNumbers(title) && Validator.validateAddress(streetAddr)
					&& Validator.validateOnlyLetters(city) && Validator.validateState(state)
					&& Validator.validateZip(zip) && Validator.validatePhone(phone)) {

				// if all fields contain safe, acceptable characters, check if password_confirm
				// matches password
				if (password.equals(password_confirm)) {

					// create UserProfile object
					UserProfile user = new UserProfile(firstName, lastName, email, username, password, company,
							department, title, streetAddr, city, state, zip, phone);
					UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
					// avoid creating duplicate accounts
					// check if given username or email already exist in the database table
					if (!userProfileDaoImpl.userExists(username, email)) {

						// attempt to create new account
						if (userProfileDaoImpl.insertUser(user)) {
							// if user was successfully added to the database table
							// display message above the form
							request.setAttribute("ErrorMessage",
									"Success! You can login using the registered credentials.");

						} else {
							// if account could not be created
							// display an error message above the form
							request.setAttribute("ErrorMessage",
									"Sorry. The account could not be created at the moment.");

						}
					} else {
						// display an error message above the form
						request.setAttribute("ErrorMessage", "User account for that username or email already exist!");
					}
				} else {
					// display an error message above the form
					request.setAttribute("ErrorMessage", "Confirmed password did not match your password.");
				}

			} // end if all posted fields have been positively validated
			else {
				// if user input was not valid
				// display an error message above the form
				request.setAttribute("ErrorMessage", "Wrong input. Check if all form fields are correct.");

			}
		} else {
			request.setAttribute("ErrorMessage", "Check if all required fields are filled out.");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("insert.jsp");
		dispatcher.forward(request, response);

	} // end doPost

}
