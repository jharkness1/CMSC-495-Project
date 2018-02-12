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
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserProfile user = null;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// get all posted fields from the updateProfile form
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
				user = new UserProfile(firstName, lastName, email, company, department, title, streetAddr, city, state,
						zip, phone, username, password);
				UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
				// if all fields contain safe, acceptable characters, check if password_confirm
				// matches password
				if (password.equals(password_confirm)) {
					// check if user wants to change own email or username (avoid duplicates!)
					if (email.equals(oldEmail) && username.equals(oldUsername)) {
						System.out.println("You are not changing email and username");
						// try to update profile
						String message = userProfileDaoImpl.updateProfile(user, oldUsername);
						request.setAttribute("ErrorMessage", message);
						RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
						dispatcher.forward(request, response);
					} // end if user didn't want to change email or username
					else {
						// if user wanted to change email or username
						System.out.println("You want to change email and username");
					}
				} // end if password matched the password_confirm
				else {
					// display an error message
					request.setAttribute("ErrorMessage", "Confirmed password did not match your password.");
					// set the request attribute to user's info
					request.setAttribute("userInfo", user);
					RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
					dispatcher.forward(request, response);
				} // end if passwords didn't match

			} // end if all fields were validated
			else {
				// if user input was not valid
				// display an error message
				request.setAttribute("ErrorMessage", "Wrong input.Try again.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
				dispatcher.forward(request, response);
			}

		} // end if all required fields have been filled out
		else {
			// display an error message
			request.setAttribute("ErrorMessage", "Required fields cannot be empty.Try again.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		} // end if not all required fields have been filled

	} // end doPost

}
