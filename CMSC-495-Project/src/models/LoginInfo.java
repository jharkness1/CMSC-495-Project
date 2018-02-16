package models;

import java.sql.Timestamp;

public class LoginInfo {

	// fields
	// initialize id to 0 (there will never be such id in the database)
	private int id = 0;
	private String username = "";
	private String password = "";
	private int noFailedLoginAttempts = 0;
	private Timestamp lastFailedLogin = null;
	private String role = "";
	
	// constructor
	public LoginInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// getter and setter methods
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getNoFailedLoginAttempts() {
		return noFailedLoginAttempts;
	}
	public void setNoFailedLoginAttempts(int noFailedLoginAttempts) {
		this.noFailedLoginAttempts = noFailedLoginAttempts;
	}
	public Timestamp getLastFailedLogin() {
		return lastFailedLogin;
	}
	public void setLastFailedLogin(Timestamp lastFailedLogin) {
		this.lastFailedLogin = lastFailedLogin;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
