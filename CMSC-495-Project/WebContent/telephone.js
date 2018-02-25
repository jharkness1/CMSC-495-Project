/**
 * function to add dashes to phone number
 * requires 10 digit input
 * returns string formatted: 101-101-0000
 * @param f
 * @returns
 * based on forum :https://stackoverflow.com/questions/13093187/javascript-dashes-in-phone-number
 */
function addDashes(f) {
	// adds dashes between numbers representing phone number
	var input = f.value;
	// if user cleared field leave empty string
	if (input.length == 0) {
		f.value = "";
	} else { // add dashes
		var withDashes = input.slice(0, 3) + "-" + input.slice(3, 6) + "-"
				+ input.slice(6, 12);
		// check if format after adding dashes fits
		var regex = /\d{3}[-]\d{3}[-]\d{4}$/;
		if (regex.test(withDashes)) {
			f.value = withDashes;
		} else { // wrong format
			f.value = "Format: 1011010000";
		}
	}
}