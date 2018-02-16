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
 * Servlet implementation class ConfirmDeleteServlet
 */
@WebServlet("/ConfirmDeleteServlet")
public class ConfirmDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private HttpSession session = null;
	private int id;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.id = Integer.parseInt(request.getParameter("id"));
		// session = request.getSession(true);
		UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
		UserProfile deleteProfile = userProfileDaoImpl.accessProfile(this.id);
		// session.setAttribute("ownProfile", deleteProfile);
		request.setAttribute("profileToDelete", deleteProfile);
		RequestDispatcher dispatcher = request.getRequestDispatcher("delete.jsp");
		dispatcher.forward(request, response);
	}

}
