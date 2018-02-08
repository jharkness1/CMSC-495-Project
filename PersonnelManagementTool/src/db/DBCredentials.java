package db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DBCredentials {
	// name of the file with database credentials
	public static String file = "properties.properties";

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    // constructor sets instance variables
    public DBCredentials() {
        try {
            // Get the inputStream
            InputStream inputStream = this.getClass().getClassLoader()
                    .getResourceAsStream(file);

            Properties properties = new Properties();

            // load the inputStream using the Properties
            properties.load(inputStream);
            // get the value of the property
            this.dbUser = properties.getProperty("Username");
            this.dbPassword = properties.getProperty("Password");
            this.dbUrl = properties.getProperty("DataBase");

        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    } // end constructor

    public String getDbUrl() {
        return this.dbUrl;
    }

    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    public String getDbUser() {
        return this.dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return this.dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

}