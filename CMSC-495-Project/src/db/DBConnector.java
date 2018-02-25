package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.util.http.fileupload.MultipartStream.IllegalBoundaryException;


public class DBConnector {
	// initiate a connection to null
	private Connection conn = null;
	// create an object with database credentials
	// for each instance of DBConnector
	private DBCredentials myCredentials = new DBCredentials();

	// constructor
	// try to initiate database connection
	public DBConnector() throws IllegalBoundaryException, InstantiationException, IllegalAccessException {

		try {

			Class.forName("com.mysql.jdbc.Driver").newInstance();
			// use database credentials retrieved from properties file
			// stored in DBCredentials object
			conn = DriverManager.getConnection(myCredentials.getDbUrl(), myCredentials.getDbUser(),
					myCredentials.getDbPassword());

		} catch (ClassNotFoundException e) {
			System.out.println("Where is your Driver?");
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	// method that returns the connection to the database
	public Connection getConnection() {
		return this.conn;
	}

}