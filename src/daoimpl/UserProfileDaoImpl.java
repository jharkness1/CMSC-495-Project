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
	 * already in that table, new user can't be inserted
	 */
	// returns true if username or email already exist
	// or if any database error occurs
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
//					System.out.println("Number of users by that username or email equals " + numberOfRows);
				}
				// if the query returned number of rows other than 0, user already exists
				if (numberOfRows > 0) {
					exists = true;
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

		return exists;
	} // end userExists method

	@Override
	/**
	 * method to insert new user into database table
	 */
	// returns true if user was successfully inserted
	// returns false if not or if any database error occurs
	public boolean insertUser(UserProfile user) {
		// TODO Auto-generated method stub
		try {
			DBConnector dbConnector = new DBConnector();
			Connection conn = dbConnector.getConnection();
		} catch (Exception e) {

		}
		return false;
	}

	@Override
	public ArrayList<UserProfile> getSearchResultsByName(String lastName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserProfile> getSearchResultsByDept(String department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<UserProfile> listAllResults() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile accessProfile(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateProfile(UserProfile user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteUser(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
