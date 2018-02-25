package dao;

import java.util.ArrayList;

import models.UserProfile;

public interface UserProfileDao {

	boolean userExists(String username, String email);

	boolean usernameExists(String username);

	boolean emailExists(String email);

	boolean insertUser(UserProfile user);

	ArrayList<UserProfile> getSearchResultsByName(String lastName);

	ArrayList<UserProfile> getSearchResultsByDept(String department);

	ArrayList<UserProfile> listAllResults();

	UserProfile accessProfile(int id);

	UserProfile getUserInfoForUpdate(int id);

	String updateProfile(UserProfile user, String oldUsername);

	boolean deleteUser(int id);

}
