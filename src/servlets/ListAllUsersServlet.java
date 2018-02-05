package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import daoimpl.UserProfileDaoImpl;
import models.UserProfile;

/**
 * Servlet implementation class ListAllUsersServlet
 */
@WebServlet("/ListAllUsersServlet")
public class ListAllUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<UserProfile> allResults;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListAllUsersServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		// create data access object implementation
		UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
		allResults = userProfileDaoImpl.listAllResults();

		// set the request attribute to forward the results to jsp
		request.setAttribute("results", allResults);
		RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
