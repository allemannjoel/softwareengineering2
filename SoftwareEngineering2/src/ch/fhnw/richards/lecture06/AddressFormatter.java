package ch.fhnw.richards.lecture06;

import java.util.ArrayList;

/**
 * The beginnings of an AddressFormatters, as described in the lecture.
 */
public class AddressFormatter {
	/**
	 * Split a line on a set of defined characters. Split into as many lines as
	 * required, keeping each as long as possible (<= maxLength).
	 * 
	 * If a line is too long, and none of the characters is found, split the
	 * line at maxLength anyway.
	 * 
	 * The characters used to split the string are omitted from the result.
	 */
	protected ArrayList<String> splitLine(String in, int max) {
		final char[] splitCharacters = {' ', ',', '.', ';', ':', '/' };
		ArrayList<String> linesOut = new ArrayList<>();
		int posInString = 0;
		
		while (posInString < in.length()) { // Still have unprocessed characters
			int breakPosition = posInString + max; // One character too far
	
			// Are we done? No split necessary?
			if (breakPosition >= in.length()) {
				linesOut.add(in.substring(posInString));
				posInString = breakPosition;
			} else { // We must split
				// Working from end to start: look for a split-character
				boolean splitCharFound = false;
				int pos = breakPosition;
				while (pos > posInString && !splitCharFound) {
					splitCharFound = contains(splitCharacters, in.charAt(pos));
					if (!splitCharFound) pos--;
				}
				if (splitCharFound) {
					linesOut.add(in.substring(posInString, pos));
					posInString = pos + 1; // skip split-character
				} else {
					linesOut.add(in.substring(posInString, breakPosition));
					posInString = breakPosition;
				}
			}
		}		
		return linesOut;
	}
	
	private boolean contains(char[] chars, char search) {
		for (char c : chars) {
			if (c == search) return true;
		}
		return false;
	}
}
