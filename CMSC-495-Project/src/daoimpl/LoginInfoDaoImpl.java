package daoimpl;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import dao.LoginInfoDao;
import db.DBConnector;
import models.LoginInfo;

public class LoginInfoDaoImpl implements LoginInfoDao {

	@Override
	/**
	 * method to retrieve from the database information required to verify user's
	 * credentials, and to determine whether the account is locked or should remain
	 * locked This method will first check if user by the given username exists in
	 * the database. If no such user exists, the method will return null. If user by
	 * the given username exists in the database, the method will return LoginInfo
	 * object that will have the fields set to the values retrieved from the
	 * database.
	 */
	public LoginInfo getLoginInfo(String username) {
		LoginInfo loginInfo = null;
		int numberOfRows = 0;
		int id = 0;
		String usernameResult = "";
		String password = "";
		int noFailedLoginAttempts = 0;
		Timestamp lastFailedLogin = null;
		String role = "";

		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			// check if user by that username exists in the database
			String query = "SELECT COUNT(*) FROM profiles WHERE username=?";
			// retrieve login information from the database:
			String query2 = "SELECT profiles.id, profiles.username, profiles.password, "
					+ "profiles.failed_login_attempts, profiles.last_failed_login, roles.role FROM profiles "
					+ "INNER JOIN roles ON profiles.id=roles.id WHERE profiles.username=?";

			PreparedStatement prepSt = null;
			ResultSet rs = null;
			PreparedStatement prepSt2 = null;
			ResultSet rs2 = null;
			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, username);

				rs = prepSt.executeQuery();
				// iterate over results, retrieve by column index
				while (rs.next()) {
					numberOfRows = rs.getInt(1);
					// System.out.println("Number of users by that username or email equals " +
					// numberOfRows);
				}
				// if username found, retrieve loginInfo
				if (numberOfRows > 0) {
					// create LoginInfo object with generic object constructor
					// this sets the object fields as declared in LoginInfo class
					// (empty strings or null)
					loginInfo = new LoginInfo();
					// retrieve login info from database
					prepSt2 = conn.prepareStatement(query2);
					prepSt2.setString(1, username);
					rs2 = prepSt2.executeQuery();
					// iterate over results, retrieve by column index
					while (rs2.next()) {
						id = rs2.getInt(1);
						usernameResult = rs2.getString(2);
						password = rs2.getString(3);
						noFailedLoginAttempts = rs2.getInt(4);
						lastFailedLogin = rs2.getTimestamp(5);
						role = rs2.getString(6);
					}
					// set loginInfo object's fields to values retrieved from database
					loginInfo.setId(id);
					loginInfo.setUsername(usernameResult);
					loginInfo.setPassword(password);
					loginInfo.setNoFailedLoginAttempts(noFailedLoginAttempts);
					loginInfo.setLastFailedLogin(lastFailedLogin);
					loginInfo.setRole(role);
				} // end if username was found in the database
			} catch (SQLException e) {
				System.out.println("Error");
//				System.out.println(e);
			} finally { // clean up
				if (rs != null) {
					rs.close();
				}
				if (prepSt != null) {
					prepSt.close();
				}
				if (rs2 != null) {
					rs2.close();
				}
				if (prepSt2 != null) {
					prepSt2.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("Error");
//			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Error");			
//			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("Error");
//					System.out.println(ex);
				}
			}
		} // end clean up
		return loginInfo;
	}

	@Override
	/**
	 * This method takes as an argument loginInfo object returned by getLoginInfo
	 * method
	 */
	public boolean isLocked(LoginInfo loginInfo, int maxFailed, int LockoutPeriod) {
		// set as locked by default
		boolean lockedOut = true;
		// get information from loginInfo object
		int attemptsSoFar = loginInfo.getNoFailedLoginAttempts();
		Timestamp lastFailedLogin = loginInfo.getLastFailedLogin();
		// if number of failed login attempts is less than allowed max,
		// the account is not locked out
		if (attemptsSoFar < maxFailed) {
			lockedOut = false;
		} // end if account is not locked
		else {
			// if the account is locked, check if the allowed lockout time period has passed
			// create current timestamp
			// convert Date object from utils to Timestamp from java.sql
			Timestamp nowTimestamp = new Timestamp(new Date().getTime());
			// subtraction result in seconds
			long elapsed = (nowTimestamp.getTime() - lastFailedLogin.getTime()) / 1000;
			if (elapsed > LockoutPeriod) {
				// reset number of failed login attempts in the database
				boolean success = resetFailedLogin(loginInfo);
				// unlock automatically
				if (success) {
					lockedOut = false;
				} else {
					// if reset was not successful, keep the account locked
					lockedOut = true;
				}
			} else {
				// keep the account locked
				lockedOut = true;
			}
		} // end if the account was locked

		return lockedOut;
	}

	@Override
	public boolean authenticate(LoginInfo loginInfo, String password) {
		// deny by default!
		boolean authentic = false;
		String passwordFromDatabase = loginInfo.getPassword();
		// check if password from database and password from the login form match
		if (password.equals(passwordFromDatabase)) {
			// reset number of failed login attempts in database
			boolean success = resetFailedLogin(loginInfo);
			if (success) { // if number of failed login attempts was reset
				// set authentic to true if match was found
				authentic = true;
			}
		} else {
			// update the number of failed login attempts
			// and time of failed login attempt in the database
			boolean success = updateFailedLogin(loginInfo);
			if (success) { // if number of failed login attempts was updated
				authentic = false;
			}
		} // end if password from the login form didn't match password from the database

		return authentic;
	}

	/**
	 * 
	 * @param loginInfo
	 * @return method to reset number of failed login attempts to 0 in the database
	 *         for the given username
	 */
	public boolean resetFailedLogin(LoginInfo loginInfo) {
		boolean success = false;
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "UPDATE profiles SET failed_login_attempts=0 WHERE username=?";

			PreparedStatement prepSt = null;

			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setString(1, loginInfo.getUsername());

				prepSt.executeUpdate();
				success = true;
			} catch (SQLException e) {
				System.out.println("Error");
//				System.out.println(e);
			} finally { // clean up

				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("Error");
//			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Error");
//			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("Error");
//					System.out.println(ex);
				}
			}
		} // end clean up
		return success;
	} // end resetFailedLogin method

	/**
	 * 
	 * @param loginInfo
	 * @return method to update number of failed login attempts and time of last
	 *         failed login attempt in the database
	 */
	public boolean updateFailedLogin(LoginInfo loginInfo) {
		boolean success = false;
		// retrieve the number of failed login attempts so far
		int attemptsSoFar = loginInfo.getNoFailedLoginAttempts();
		// and increment
		attemptsSoFar++;
		// create current timestamp
		// convert Date object from utils to Timestamp from java.sql
		Timestamp nowTimestamp = new Timestamp(new Date().getTime());
		Connection conn = null;
		try {
			// try to create connection to the database
			conn = new DBConnector().getConnection();

			// use prepared statements to avoid SQL Injection
			String query = "UPDATE profiles SET failed_login_attempts=?, last_failed_login=? WHERE username=?";

			PreparedStatement prepSt = null;

			try {
				prepSt = conn.prepareStatement(query);
				prepSt.setInt(1, attemptsSoFar);
				prepSt.setTimestamp(2, nowTimestamp);
				prepSt.setString(3, loginInfo.getUsername());

				prepSt.executeUpdate();
				success = true;
			} catch (SQLException e) {
				System.out.println("Error");
//				System.out.println(e);
			} finally { // clean up

				if (prepSt != null) {
					prepSt.close();
				}
			}
		} catch (SQLException e) {
			System.out.println("Error");
//			System.out.println(e);
		} catch (Exception e) {
			System.out.println("Error");
//			e.printStackTrace();
		} finally { // clean up
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException ex) {
					System.out.println("Error");
//					System.out.println(ex);
				}
			}
		} // end clean up
		return success;
	} // end updateFailedLogin method

}
