package utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class LogWriter {

	// These variables are necessary for this class
	private static final String filename = "PMTlog.txt";
	// private String line;
	private static final boolean append = true;
	private static BufferedWriter writer = null;

	// This function will log when a user successfully logs into their account
	public static void successfulLogin(String username) {

		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Login on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was entered correctly by the user to access their account.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a users unsuccessful login attempt
	public static void unsucessfulLoginAttempt(String username) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append(
					"Error: Unsuccessful Login Attempt on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was entered by the user in the attempt to access an account.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a users is locked out of their account
	public static void loginLockout(String username) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Error: User Locked Out on " + date + " because username: " + username);
			writer.newLine();
			writer.append(" attempted to access the system multiple times with the incorrect user credentials.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user successfully access a profile
	public static void successfulAccessProfile(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Profile Access on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was able to access the profile id: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully attempts to access a
	// profile
	public static void unsuccessfulAccessProfile(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append(
					"Error: Unsuccessful Profile Access on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" attempting to access the profile id: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user successfully updates a profile
	public static void successfulProfileUpdate(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Profile Update on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was able to update the profile id: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully attempts to update a
	// profile
	public static void unsuccessfulProfileUpdate(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append(
					"Error: Unsuccessful Profile Update on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was unable to update the profile id: " + profileName + " correctly.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user successfully deletes a profile
	public static void successfulProfileDeletion(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Profile Deletion on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was able to delete the profile id: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully attempts to delete a
	// profile
	public static void unsuccessfulProfileDeletion(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Error: Unsuccessful Profile Deletion attempt on " + date
					+ " in which the following username: " + username);
			writer.newLine();
			writer.append(" was unable to delete the profile id: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user successfully creates a profile
	public static void successfulAccountCreation(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Profile Creation on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was able to create the profile for: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully attempts to create a
	// profile
	public static void unsuccessfulAccountCreation(String username, String profileName) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Error: Unsuccessful Profile Creation attempt on " + date + " in which the following user: "
					+ username);
			writer.newLine();
			writer.append(" was unable to create the profile for: " + profileName + ".");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user successfully logs out of their account
	public static void successfulLogout(String username) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Successful Logout on " + date + " in which the following user: " + username);
			writer.newLine();
			writer.append(" was able to logout correctly and stop access to their account.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully logs out of their account
	public static void unsuccessfulLogout(String username) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append(
					"Error: Unsuccessful Logout Attempt on " + date + " in which the following username: " + username);
			writer.newLine();
			writer.append(" was unable to logout their account correctly.");
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}

	// This function will log when a user unsuccessfully logs out of their account
	public static void recordError(String errorMessage) {
		try {
			Date date = new Date();
			writer = new BufferedWriter(new FileWriter(filename, append));
			writer.newLine();
			writer.append("Error has occured at " + date + " regarding the following:");
			writer.newLine();
			writer.append(errorMessage);
			writer.newLine();
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("File IO Exception" + io.getMessage());
		}

		try {
			if (writer != null) {
				writer.close();
			}
		}
		// print error message if there is one
		catch (IOException io) {
			System.out.println("Issue closing the File." + io.getMessage());
		}
	}
}
