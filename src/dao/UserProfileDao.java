package dao;

import java.util.ArrayList;


import models.UserProfile;

public interface UserProfileDao {
	
	boolean userExists(String username, String email); 
	boolean insertUser(UserProfile user);
	ArrayList<UserProfile> getSearchResultsByName(String lastName); 
	ArrayList<UserProfile> getSearchResultsByDept(String department); 
	ArrayList<UserProfile> listAllResults(); 
	UserProfile accessProfile(int id); 
	String updateProfile(UserProfile user); 
	boolean deleteUser(int id); 

}
