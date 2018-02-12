package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.UserProfile;
import utilities.Validator;

/**
 * Servlet implementation class UpdateProfileServlet
 */
@WebServlet("/UpdateProfileServlet")
public class UpdateProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserProfile userOldInfo = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Description: helper method that will set the appropriate error message to
	 * display above the updateProfile form this method will also set back the
	 * request attribute to old user's info
	 * 
	 * @param request
	 * @param response
	 * @param errorMessage
	 * @param object
	 * @throws ServletException
	 * @throws IOException
	 */
	public void showError(HttpServletRequest request, HttpServletResponse response, String errorMessage, Object object)
			throws ServletException, IOException {
		request.setAttribute("ErrorMessage", errorMessage);
		// set back the request attribute to old user's info
		request.setAttribute("oldInfo", object);
		RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
		dispatcher.forward(request, response);
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
		// get all posted fields from the createAccount form
		// and retrieve the old user information from request attribute to compare with
		// the posted values
		userOldInfo = (UserProfile) request.getAttribute("oldInfo");
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
				// if all fields contain safe, acceptable characters, check if password_confirm
				// matches password
				if (password.equals(password_confirm)) {
					// check if user wants to change own username (avoid duplicates!)
					if (username.equals(userOldInfo.getUsername())) {
						// if user doesn't want to change username
						System.out.println("You are not updating username");
						// check if user wants to change email (avoid duplicates!)
						if (email.equals(userOldInfo.getEmail())) {
							// if user doesn't want to change email
							System.out.println("You are not updating email");
						} // end if user doesn't want to change email
						else {
							// if user wants to change email
							System.out.println("You want to update email");
							
						} // end if user wanted to change email						
					} // end if user doesn't want to change username
					else {
						// if user wants to change username
						System.out.println("You want to update username");
						// check if new, given username already exists in the database to avoid duplicates
						
					} // end if user wanted to change username
				} // end if password matched the password_confirm
				else {
					// display an error message above the form
					showError(request, response, "Confirmed password did not match your password.", userOldInfo);
				} // end if passwords didn't match

			} // end if all fields were validated
			else {
				// if user input was not valid
				// display an error message above the form
				showError(request, response, "Wrong input. Check if all form fields are correct.", userOldInfo);
			}

		} // end if all required fields have been filled out
		else {
			// display an error message above the form
			showError(request, response, "Check if all required fields are filled out.", userOldInfo);
		} // end if not all required fields have been filled

	} // end doPost

}
