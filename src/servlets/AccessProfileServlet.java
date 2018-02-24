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

/**
 * Servlet implementation class AccessProfileServlet
 */
@WebServlet("/AccessProfileServlet")
public class AccessProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private HttpSession session;
	private String loggedInUsername;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccessProfileServlet() {
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
		// retrieve id from submitted form
		int id = Integer.parseInt(request.getParameter("id"));

		UserProfileDaoImpl profileImp = new UserProfileDaoImpl();
		// retrieve user profile info
		UserProfile user = profileImp.accessProfile(id);
		// log
		// retrieve username from the session attribute
		session = request.getSession(true);
		loggedInUsername = (String) session.getAttribute("username");
		if (user.getLastname().length() > 0) {
			// if method successfully retrieved the user info from database
			// last name has to be longer than 0 characters (required field)
			LogWriter.successfulAccessProfile(loggedInUsername, String.valueOf(id));
		} else {
			LogWriter.unsuccessfulAccessProfile(loggedInUsername, String.valueOf(id));
		}
		// set user profile as request attribute
		request.setAttribute("profile", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");

		dispatcher.forward(request, response);
	}

}
