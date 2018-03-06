package ch.fhnw.richards.lecture02.mvc_calculator_v4;

public class CalculatorModel {
	protected String calculate(String in) {
	    boolean error = false;
	    String result;
	    
	    // '+' must be escaped in the regex, using '\'
	    // '\' must be escaped in a String, against using '\'	    
		String[] strValues = in.split("\\+");
		
		// Convert Strings to ints - catch any errors
		int[] intValues = new int[strValues.length];
		try { // parseInt may produce an exception
    		for (int i = 0; i < strValues.length; i++) {
    		    intValues[i] = Integer.parseInt(strValues[i]);
    		}
		} catch (NumberFormatException e) {
		    error = true;
		}
		
		// If error, set result
		// If no error, sum all values and set result 
		if (error) {
		    result = "Error";
		} else {
		    int sum = 0;
		    for (int intValue : intValues) sum += intValue;
		    result = Integer.toString(sum);
		}
		
		return result;
	}
}
