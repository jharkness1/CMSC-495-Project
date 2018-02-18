package daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.UserProfileDao;
import db.DBConnector;
import models.UserProfile;

public class UserProfileDaoImpl implements UserProfileDao {

	// constructor
	public UserProfileDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	/**
	 * method to verify if user already exists in the database username and email
	 * are unique fields in the profiles table, therefore, if username or email are
	 * already in that table, new user can't be inserted. Returns true if username
	 * or email already exist or if any database error occurs
	 */
	public boolean userExists(String username, String email) {
		boolean exists = true;
		int numberOfRows = 0;

		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT COUNT(*) FROM profiles WHERE username=? OR email=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, username);
				prepSt.setString(2, email);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					numberOfRows = rs.getInt(1);
					// System.out.println("Number of users by that username or email equals " +
					// numberOfRows);
				}
				// if the query returned number of rows other than 0, user already exists
				if (numberOfRows > 0) {
					exists = true;
				} else {
					exists = false;
				}

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up
			// System.out.println(exists);
		return exists;
	} // end userExists method

	@Override
	public boolean usernameExists(String username) {
		boolean usernameExists = true;
		int numberOfRows = 0;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT COUNT(*) FROM profiles WHERE username=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, username);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					numberOfRows = rs.getInt(1);
					// System.out.println("Number of users by that username equals " +
					// numberOfRows);
				}
				// if the query returned number of rows other than 0, user already exists
				if (numberOfRows > 0) {
					usernameExists = true;
				} else {
					usernameExists = false;
				}

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up
		// System.out.println(usernameExists);
		return usernameExists;
	} // end usernameExists method

	@Override
	public boolean emailExists(String email) {
		boolean emailExists = true;
		int numberOfRows = 0;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT COUNT(*) FROM profiles WHERE email=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, email);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					numberOfRows = rs.getInt(1);
					// System.out.println("Number of users by that username equals " +
					// numberOfRows);
				}
				// if the query returned number of rows other than 0, user already exists
				if (numberOfRows > 0) {
					emailExists = true;
				} else {
					emailExists = false;
				}

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up
		// System.out.println(emailExists);
		return emailExists;
	} // end emailExists method

	@Override
	/**
	 * method to insert new user into database table Returns true if user was
	 * successfully inserted Returns false if not or if any database error occurs
	 */
	public boolean insertUser(UserProfile user) {
		boolean success = false;

		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "INSERT INTO profiles (firstname, lastname, "
					+ "email, company, department, title, work_address, "
					+ "work_city, work_state, work_zip, phone, username, password) "
					+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement prepSt = null;

			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, user.getFirstname());
				prepSt.setString(2, user.getLastname());
				prepSt.setString(3, user.getEmail());
				prepSt.setString(4, user.getCompany());
				prepSt.setString(5, user.getDepartment());
				prepSt.setString(6, user.getTitle());
				prepSt.setString(7, user.getWork_address());
				prepSt.setString(8, user.getWork_city());
				prepSt.setString(9, user.getWork_state());
				prepSt.setString(10, user.getWork_zip());
				prepSt.setString(11, user.getPhone());
				prepSt.setString(12, user.getUsername());
				prepSt.setString(13, user.getPassword());

				prepSt.executeUpdate();

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up

				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up

		// verify if the new row was added to the table
		if (userExists(user.getUsername(), user.getEmail())) {
			success = true;
		}
		return success;
	} // end insertUser method

	@Override
	/**
	 * method to search for users in database table by Last Name Returns Array List
	 * of UserProfile objects where each UserProfile object represents single user
	 * in database table
	 */
	public ArrayList<UserProfile> getSearchResultsByName(String lastName) {
		ArrayList<UserProfile> searchResults = new ArrayList<>();
		int id = 0;
		String lastnameResult;
		String firstname;
		String email;
		String department;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT id, lastname, firstname, email, department FROM profiles WHERE lastname=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				// bind parameter with SQL query
				prepSt.setString(1, lastName);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					id = rs.getInt(1);
					lastnameResult = rs.getString(2);
					firstname = rs.getString(3);
					email = rs.getString(4);
					// this field can be null!
					department = rs.getString(5);
					if (department == null) {
						// replace null with empty string
						department = "";
					}
					// create new UserProfile object
					UserProfile resultUser = new UserProfile();
					// set appropriate fields to values from database
					resultUser.setId(id);
					resultUser.setLastname(lastnameResult);
					resultUser.setFirstname(firstname);
					resultUser.setEmail(email);
					resultUser.setDepartment(department);
					// add resultUser to the list of search results
					searchResults.add(resultUser);
				} // end while

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Other exception");
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("close conn exception");
					System.out.println(ex);
				}
			}
		} // end clean up

		// System.out.println("Results length: " + searchResults.size());
		// // iterate over results
		// for (UserProfile u : searchResults) {
		// System.out.print(u.getId());
		// System.out.print(u.getLastname());
		// System.out.print(u.getFirstname());
		// System.out.print(u.getEmail());
		// System.out.println(u.getDepartment());
		// }
		return searchResults;
	} // end searchResultsByName method

	@Override
	/**
	 * method to search for users in database table by the department Returns Array
	 * List of UserProfile objects where each UserProfile object represents single
	 * user in database table
	 */
	public ArrayList<UserProfile> getSearchResultsByDept(String department) {
		ArrayList<UserProfile> searchResults = new ArrayList<>();
		int id = 0;
		String lastname;
		String firstname;
		String email;
		String departmentResult;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT id, lastname, firstname, email, department FROM profiles WHERE department=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				// bind parameter with SQL query
				prepSt.setString(1, department);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					id = rs.getInt(1);
					lastname = rs.getString(2);
					firstname = rs.getString(3);
					email = rs.getString(4);
					departmentResult = rs.getString(5);
					// create new UserProfile object
					UserProfile resultUser = new UserProfile();
					// set appropriate fields to values from database
					resultUser.setId(id);
					resultUser.setLastname(lastname);
					resultUser.setFirstname(firstname);
					resultUser.setEmail(email);
					resultUser.setDepartment(departmentResult);
					// add resultUser to the list of search results
					searchResults.add(resultUser);
				} // end while

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Other exception");
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("close conn exception");
					System.out.println(ex);
				}
			}
		} // end clean up

		return searchResults;
	} // end searchResultsByDept method

	@Override
	/**
	 * method to retrieve all users from database table Returns Array List of
	 * UserProfile objects where each UserProfile object represents single user in
	 * database table
	 */
	public ArrayList<UserProfile> listAllResults() {
		ArrayList<UserProfile> allResults = new ArrayList<>();
		int id = 0;
		String lastname;
		String firstname;
		String email;
		String department;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT id, lastname, firstname, email, department FROM profiles";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);

				// execute query
				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					id = rs.getInt(1);
					lastname = rs.getString(2);
					firstname = rs.getString(3);
					email = rs.getString(4);
					// this field can be null!
					department = rs.getString(5);
					if (department == null) {
						// replace null with empty string
						department = "";
					}
					// create new UserProfile object
					UserProfile resultUser = new UserProfile();
					// set appropriate fields to values from database
					resultUser.setId(id);
					resultUser.setLastname(lastname);
					resultUser.setFirstname(firstname);
					resultUser.setEmail(email);
					resultUser.setDepartment(department);
					// add resultUser to the list of search results
					allResults.add(resultUser);
				} // end while

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Other exception");
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("close conn exception");
					System.out.println(ex);
				}
			}
		} // end clean up

		return allResults;
	} // end listAllResults

	@Override
	/**
	 * method to retrieve user's profile from database table by user's id
	 */
	public UserProfile accessProfile(int i) {
		UserProfile ownProfile = new UserProfile();
		String lastname = "";
		String firstname = "";
		String email = "";
		String company = "";
		String department = "";
		String title = "";
		String work_a = "";
		String work_c = "";
		String work_s = "";
		String work_z = "";
		String phone = "";
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT firstname,lastname,email,company,"
					+ "department,title,work_address,work_city,work_state,work_zip,phone " + "FROM profiles WHERE id=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				// bind parameter with SQL query
				prepSt.setInt(1, i);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					// sequence numbers of columns are like in the query
					firstname = rs.getString(1);
					lastname = rs.getString(2);
					email = rs.getString(3);

					// these fields can be null!
					company = rs.getString(4);
					if (company == null) {
						// replace null with empty string
						company = "";
					}
					department = rs.getString(5);
					if (department == null) {
						department = "";
					}
					title = rs.getString(6);
					if (title == null) {
						title = "";
					}
					work_a = rs.getString(7);
					if (work_a == null) {
						work_a = "";
					}
					work_c = rs.getString(8);
					if (work_c == null) {
						work_c = "";
					}
					work_s = rs.getString(9);
					if (work_s == null) {
						work_s = "";
					}
					work_z = rs.getString(10);
					if (work_z == null) {
						work_z = "";
					}
					phone = rs.getString(11);
					if (phone == null) {
						phone = "";
					}

					// set appropriate fields to values from database
					ownProfile.setId(i); // it is the same id as method's argument
					ownProfile.setLastname(lastname);
					ownProfile.setFirstname(firstname);
					ownProfile.setEmail(email);
					ownProfile.setCompany(company);
					ownProfile.setDepartment(department);
					ownProfile.setTitle(title);
					ownProfile.setWork_address(work_a);
					ownProfile.setWork_city(work_c);
					ownProfile.setWork_state(work_s);
					ownProfile.setWork_zip(work_z);
					ownProfile.setPhone(phone);
				} // end while

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Other exception");
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("close conn exception");
					System.out.println(ex);
				}
			}
		} // end clean up

		return ownProfile;
	} // end accessProfile method

	@Override
	/**
	 * method to retrieve all user's information that can be updated from the
	 * database (including username and password)
	 */
	public UserProfile getUserInfoForUpdate(int id) {
		UserProfile userInfoForUpdate = new UserProfile();

		String firstname = "";
		String lastname = "";
		String email = "";
		String company = "";
		String department = "";
		String title = "";
		String work_a = "";
		String work_c = "";
		String work_s = "";
		String work_z = "";
		String phone = "";
		String username = "";
		String password = "";
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "SELECT firstname,lastname,email,company,"
					+ "department,title,work_address,work_city,work_state,work_zip,phone,username,password "
					+ "FROM profiles WHERE id=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			try {
				prepSt = conn.prepareStatement(query);
				// bind parameter with SQL query
				prepSt.setInt(1, id);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					// sequence numbers of columns are like in the query
					firstname = rs.getString(1);
					lastname = rs.getString(2);
					email = rs.getString(3);

					// these fields can be null!
					company = rs.getString(4);
					if (company == null) {
						// replace null with empty string
						company = "";
					}
					department = rs.getString(5);
					if (department == null) {
						department = "";
					}
					title = rs.getString(6);
					if (title == null) {
						title = "";
					}
					work_a = rs.getString(7);
					if (work_a == null) {
						work_a = "";
					}
					work_c = rs.getString(8);
					if (work_c == null) {
						work_c = "";
					}
					work_s = rs.getString(9);
					if (work_s == null) {
						work_s = "";
					}
					work_z = rs.getString(10);
					if (work_z == null) {
						work_z = "";
					}
					phone = rs.getString(11);
					if (phone == null) {
						phone = "";
					}
					username = rs.getString(12);
					password = rs.getString(13);

					// set appropriate fields to values from database
					userInfoForUpdate.setId(id); // it is the same id as method's argument
					userInfoForUpdate.setLastname(lastname);
					userInfoForUpdate.setFirstname(firstname);
					userInfoForUpdate.setEmail(email);
					userInfoForUpdate.setCompany(company);
					userInfoForUpdate.setDepartment(department);
					userInfoForUpdate.setTitle(title);
					userInfoForUpdate.setWork_address(work_a);
					userInfoForUpdate.setWork_city(work_c);
					userInfoForUpdate.setWork_state(work_s);
					userInfoForUpdate.setWork_zip(work_z);
					userInfoForUpdate.setPhone(phone);
					userInfoForUpdate.setUsername(username);
					userInfoForUpdate.setPassword(password);
				} // end while

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("SQL exception");
			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Other exception");
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("close conn exception");
					System.out.println(ex);
				}
			}
		} // end clean up
		return userInfoForUpdate;
	} // end getUserInfoForUpdate method

	@Override
	/**
	 * method to update user profile information in the database returns string
	 * message whether there was no error during update
	 */
	public String updateProfile(UserProfile user, String oldUsername) {
		// set message to error by default,
		// update it to success later
		String message = "Profile could not be updated.";

		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "UPDATE profiles SET firstname=?, lastname=?, email=?, "
					+ "company=?, department=?, title=?, work_address=?, work_city=?, "
					+ "work_state=?, work_zip=?, phone=?, username=?, password=? WHERE username=?";

			PreparedStatement prepSt = null;

			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, user.getFirstname());
				prepSt.setString(2, user.getLastname());
				prepSt.setString(3, user.getEmail());
				prepSt.setString(4, user.getCompany());
				prepSt.setString(5, user.getDepartment());
				prepSt.setString(6, user.getTitle());
				prepSt.setString(7, user.getWork_address());
				prepSt.setString(8, user.getWork_city());
				prepSt.setString(9, user.getWork_state());
				prepSt.setString(10, user.getWork_zip());
				prepSt.setString(11, user.getPhone());
				prepSt.setString(12, user.getUsername());
				prepSt.setString(13, user.getPassword());
				prepSt.setString(14, oldUsername);

				prepSt.executeUpdate();
				message = "Success! Profile updated.";

			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up

				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up
		return message;
	} // end updateProfile method

	@Override
	public boolean deleteUser(int id) {
		boolean success = false;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "DELETE FROM profiles WHERE id=?";

			PreparedStatement prepSt = null;

			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setInt(1, id);

				prepSt.executeUpdate();
				success = true;
			} catch (SQLException e) {
				System.out.println(e);
			} finally { // clean up

				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println(ex);
				}
			}
		} // end clean up
		return success;
	} // end deleteUser method

}
