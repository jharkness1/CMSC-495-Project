package servlets;

import java.io.IOException;
import java.util.ArrayList;

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
 * Servlet implementation class ConfirmDeleteServlet
 */
@WebServlet("/ConfirmDeleteServlet")
public class ConfirmDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private int id;
	private HttpSession session;
	// variable that will determine whether the user is authorized to delete profile
	// deny by default
	private boolean allowDelete = false;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmDeleteServlet() {
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
		this.id = Integer.parseInt(request.getParameter("id"));
		// if the user is not admin he/she should not be allowed to delete
		// any profile
		// Therefore, retrieve role and own id from session variables:
		session = request.getSession(true);
		String role = (String) session.getAttribute("role");
		UserProfile loggedInUser = (UserProfile) session.getAttribute("ownProfile");
		int idOfLoggedInUser = loggedInUser.getId();
		if (!role.equals("admin")) {
			// deny
			allowDelete = false;
		} // end if user was not admin
		else { // if user is admin
				// if user is admin check if the posted id equals own id
			if (this.id == idOfLoggedInUser) {
				// if posted id equals own id, it means that admin user wants to delete own
				// profile. In such case we should forbid the deletion
				allowDelete = false;
			} else {
				// this means that admin user wants to delete someone else profile
				// authorize him/her to do that
				allowDelete = true;
			}
		} // end if user was admin
			// now verify if user is authorized to delete profile
		if (allowDelete) {
			UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
			UserProfile deleteProfile = userProfileDaoImpl.accessProfile(this.id);
			// session.setAttribute("ownProfile", deleteProfile);
			request.setAttribute("profileToDelete", deleteProfile);
			RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("ErrorMessage", "You are not authorized to delete this account!");
			// create data access object implementation and all profiles from database
			UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
			ArrayList<UserProfile> allResults = userProfileDaoImpl.listAllResults();
			// set the request attribute to forward the results to jsp
			request.setAttribute("results", allResults);
			RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
			dispatcher.forward(request, response);
		}
	}

}
