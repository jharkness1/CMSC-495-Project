package utilities;

import java.text.Normalizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

	// method to validate accepted characters for password
	// and accepted password length
	public static boolean validatePassword(String password) {
		boolean valid = false;
		// normalize first
		String normalizedPassword = Normalizer.normalize(password, Normalizer.Form.NFKC);
		// check length
		if (normalizedPassword.length() < 8 || normalizedPassword.length() > 30) {
			valid = false;
		} // end if length is too long or too short
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z0-9._!@$]");
			Matcher matcher = pattern.matcher(normalizedPassword);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate accepted characters for email
	// and accepted email length
	public static boolean validateEmail(String email) {
		boolean valid = false;
		// normalize first
		String normalizedEmail = Normalizer.normalize(email, Normalizer.Form.NFKC);
		// check length
		if (normalizedEmail.length() > 30) {
			valid = false;
		} // end if length is too long
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z0-9._@]");
			Matcher matcher = pattern.matcher(normalizedEmail);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate accepted characters
	// and accepted length for first name, last name, and work city
	public static boolean validateOnlyLetters(String userInput) {
		boolean valid = false;
		// normalize first
		String normalizedUserInput = Normalizer.normalize(userInput, Normalizer.Form.NFKC);
		// check length
		if (normalizedUserInput.length() > 30) {
			valid = false;
		} // end if length is too long
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z ]");
			Matcher matcher = pattern.matcher(normalizedUserInput);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate accepted characters
	// and accepted length for username, company name, department, and job title
	public static boolean validateOnlyLettersAndNumbers(String userInput) {
		boolean valid = false;
		// normalize first
		String normalizedUserInput = Normalizer.normalize(userInput, Normalizer.Form.NFKC);
		// check length
		if (normalizedUserInput.length() > 30) {
			valid = false;
		} // end if length is too long
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z-0-9 ]");
			Matcher matcher = pattern.matcher(normalizedUserInput);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate street address
	public static boolean validateAddress(String userInput) {
		boolean valid = false;
		// normalize first
		String normalizedUserInput = Normalizer.normalize(userInput, Normalizer.Form.NFKC);
		// check length
		if (normalizedUserInput.length() > 50) {
			valid = false;
		} // end if length is too long
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z-0-9#. ]");
			Matcher matcher = pattern.matcher(normalizedUserInput);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate state
	public static boolean validateState(String state) {
		boolean valid = false;
		// normalize first
		String normalizedState = Normalizer.normalize(state, Normalizer.Form.NFKC);
		// check length
		if (normalizedState.length() > 2) {
			valid = false;
		} 
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^A-Za-z]");
			Matcher matcher = pattern.matcher(normalizedState);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate zip code
	public static boolean validateZip(String zip) {
		boolean valid = false;
		// normalize first
		String normalizedZip = Normalizer.normalize(zip, Normalizer.Form.NFKC);
		// check length
		if (normalizedZip.length() > 5) {
			valid = false;
		}
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(normalizedZip);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

	// method to validate phone number
	public static boolean validatePhone(String phone) {
		boolean valid = false;
		// normalize first
		String normalizedPhone = Normalizer.normalize(phone, Normalizer.Form.NFKC);
		// check length
		if (normalizedPhone.length() > 15) {
			valid = false;
		} // end if length is too long
		else {
			// create a white list of accepted characters:
			Pattern pattern = Pattern.compile("[^0-9]");
			Matcher matcher = pattern.matcher(normalizedPhone);
			if (!matcher.find()) {
				valid = true;
			} else {
				valid = false;
			}
		} // end if length was accepted
		return valid;
	}

} // end Validator class
