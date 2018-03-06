package ch.fhnw.richards.lecture02.solutions.webValidator;

import java.util.regex.Pattern;

public class WebValidator_Model {
	private static final Pattern IPPATTERN = Pattern.compile(
	        "^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	
	private static boolean validate(final String ip) {
	    return IPPATTERN.matcher(ip).matches();
	}
	
	protected ValueType getValueType(String newValue) {
		ValueType valueType = ValueType.None;
		String[] parts = newValue.split("\\.");

		// check for a numeric address first: 4 parts, each an integer 0 to 255
		if (validate(newValue)){
			return ValueType.IPV4;
		}
		
		/*
		if (parts.length == 4) {
			valid = true;
			for (String part : parts) {
				try {
					int value = Integer.parseInt(part);
					if (value < 0 || value > 255) valid = false;
				} catch (NumberFormatException e) {
					// wasn't an integer
					valid = false;
				}
			}
		}*/

		// If not valid, try for a symbolic address: at least two parts, each
		// part at least two characters. We don't bother checking what kinds of
		// characters they are.
		
		if (valueType == ValueType.None) {
			if (parts.length >= 2) {
				valueType = ValueType.WebAddress;
				for (String part : parts) {
					if (part.length() < 2) valueType = ValueType.None;
				}
			}
		}
		
		return valueType;
	}
	
	protected boolean isValidPortNumber(String newValue) {
		boolean valid = true;

		try {
			int value = Integer.parseInt(newValue);
			if (value < 1 || value > 65535) valid = false;
		} catch (NumberFormatException e) {
			// wasn't an integer
			valid = false;
		}

		return valid;
	}
}
