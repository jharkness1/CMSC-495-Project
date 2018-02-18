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

/**
 * Servlet implementation class AccessProfileServlet
 */
@WebServlet("/AccessProfileServlet")
public class AccessProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// set user profile as request attribute
		request.setAttribute("profile", user);

		RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");

		dispatcher.forward(request, response);
	}

}
