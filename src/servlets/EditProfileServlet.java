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

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
	// variable that will determine whether the user is authorized to edit profile
	// deny by default
	boolean allowEdit = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditProfileServlet() {
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
		// get the posted values from the user's profile view
		int id = Integer.valueOf((String) request.getParameter("id"));
		// if the user is not admin he/she should not be allowed to update
		// any other profile that own profile
		// Therefore, retrieve role and own id from session variables:
		session = request.getSession(true);
		String role = (String) session.getAttribute("role");
		UserProfile loggedInUser = (UserProfile) session.getAttribute("ownProfile");
		int idOfLoggedInUser = loggedInUser.getId();
		if (!role.equals("admin")) {
			// if user is not admin check if the posted id equals own id
			if (id == idOfLoggedInUser) {
				// if posted id equals own id, it means that non-admin user wants to edit own
				// profile. In such case we should authorize user to do that
				allowEdit = true;
			} else {
				// this means that regular user is trying to edit someone else profile
				// In this case we should not authorize him/her to do that
				allowEdit = false;
			}
		} // end if user was not admin
		else { // if user is admin
				// authorize admin user to edit any profile
			allowEdit = true;
		}
		// now verify if user is authorized to edit profile
		if (allowEdit) {
			// retrieve all user's information from the database
			UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
			UserProfile user = userProfileDaoImpl.getUserInfoForUpdate(id);
			// save the info as a request parameter that will be sent to edit.jsp
			request.setAttribute("userInfo", user);
			// redirect to edit.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("edit.jsp");
			dispatcher.forward(request, response);
		} else { // if user was not authorized to edit profile
			// redirect to home.jsp
			request.setAttribute("ErrorMessage", "Access denied.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("home.jsp");
			dispatcher.forward(request, response);
		}
	}

}
