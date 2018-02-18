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
import utilities.Validator;

/**
 * Servlet implementation class SearchUsersServlet
 */
@WebServlet("/SearchUsersServlet")
public class SearchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<UserProfile> searchResults;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchUsersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get  posted fields from the search form
		String searchBy = request.getParameter("searchBy");
		String searchValue = request.getParameter("searchValue");
		// check if form fields are filled out
		if (searchBy.length() > 0 && searchValue.length() > 0) {
			// check what was the search type
			if (searchBy.equals("lastName")) {
				// validate lastName
				if (Validator.validateOnlyLetters(searchValue)) {
					// create data access object implementation
					UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
					searchResults = userProfileDaoImpl.getSearchResultsByName(searchValue);
					// set the request attribute to forward the results to jsp
					request.setAttribute("results", searchResults);
					RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
					dispatcher.forward(request, response);
				}
				else {
					// display an error message above the form
					request.setAttribute("ErrorMessage",
							"Wrong input!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
					dispatcher.forward(request, response);
				}
			}
			// check what was the search type (department)
			else if (searchBy.equals("department")) {
				// validate department
				if (Validator.validateOnlyLettersAndNumbers(searchValue)) {
					// create data access object implementation
					UserProfileDaoImpl userProfileDaoImpl = new UserProfileDaoImpl();
					searchResults = userProfileDaoImpl.getSearchResultsByDept(searchValue);
					// set the request attribute to forward the results to jsp
					request.setAttribute("results", searchResults);
					RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
					dispatcher.forward(request, response);
				} else { // search type other that lastName or department (no such option should be allowed!)
					// display an error message above the form
					request.setAttribute("ErrorMessage",
							"Wrong input!");
					RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
					dispatcher.forward(request, response);
				}
			}
			else {
				// display an error message above the form
				request.setAttribute("ErrorMessage",
						"Someone might be trying to hack the application!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			// display an error message above the form
			request.setAttribute("ErrorMessage",
					"Please fill out the form.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
