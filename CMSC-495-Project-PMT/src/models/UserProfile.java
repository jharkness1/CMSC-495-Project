package models;

public class UserProfile {

	// fields
	// initialize id to 0 (there will never be such id in the database)
	private int id = 0;
	// initialize all String fields to empty strings
	private String firstname = "";
	private String lastname = "";
	private String email = "";
	private String company = "";
	private String department = "";
	private String title = "";
	private String work_address = "";
	private String work_city = "";
	private String work_state = "";
	private String work_zip = "";
	private String phone = "";
	private String username = "";
	private String password = "";
	
	// constructor
	public UserProfile() {
		super();
		// TODO Auto-generated constructor stub
	}
	// constructor with all fields except id
	public UserProfile(String firstname, String lastname, String email, String company, String department,
			String title, String work_address, String work_city, String work_state, String work_zip, String phone,
			String username, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.company = company;
		this.department = department;
		this.title = title;
		this.work_address = work_address;
		this.work_city = work_city;
		this.work_state = work_state;
		this.work_zip = work_zip;
		this.phone = phone;
		this.username = username;
		this.password = password;
	}

	// getter and setter methods
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWork_address() {
		return work_address;
	}

	public void setWork_address(String work_address) {
		this.work_address = work_address;
	}

	public String getWork_city() {
		return work_city;
	}

	public void setWork_city(String work_city) {
		this.work_city = work_city;
	}

	public String getWork_state() {
		return work_state;
	}

	public void setWork_state(String work_state) {
		this.work_state = work_state;
	}

	public String getWork_zip() {
		return work_zip;
	}

	public void setWork_zip(String work_zip) {
		this.work_zip = work_zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
