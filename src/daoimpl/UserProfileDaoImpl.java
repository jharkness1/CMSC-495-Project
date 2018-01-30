package daoimpl;

import java.sql.Connection;
import java.util.ArrayList;


import dao.UserProfileDao;
import db.DBConnector;
import models.UserProfile;

public class UserProfileDaoImpl implements UserProfileDao{
	
	// constructor
	public UserProfileDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean userExists(String username, String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertUser(UserProfile user) {
		// TODO Auto-generated method stub
		try {
		DBConnector dbConnector = new DBConnector();
		Connection conn = dbConnector.getConnection();
		}
		catch (Exception e) {
			
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
