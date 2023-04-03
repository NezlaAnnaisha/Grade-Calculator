package application;

public class Grade {
	double value;
	int maxValue = 100;
	double weight;
	
	Grade(double gradeValue, int maxPossibleValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxPossibleValue;
		weight = weightTowardsCourseGrade;
	}
	
	double getWeightedPercentageGrade() {
		return value * 100 * weight / maxValue;
	}
	
	String setValue(String valueAsString) {	
		String errorMessage = "";
		// Check that the string entered by the user is a valid decimal number
    	boolean validProjectGrade = true;
    	int decimalCount = 0;
    	for (char c : valueAsString.toCharArray()) {
    		// Check if the character is a digit
    		if (!Character.isDigit(c)) {		   			
    			validProjectGrade = false;
    			errorMessage = "Do not use characters! Please enter a number."; 
    			
    			// Check if the character is a negative symbol
    			if (!Character.isDigit(c) && c == '-') {
    				validProjectGrade = true;
    			}
    			
    			// Check if the character is a decimal point   			
    			if (!Character.isDigit(c) && c == '.') {
    					validProjectGrade = true;
    					errorMessage = "";
    					decimalCount++;
    			
    			// Check if more than 1 decimal point exists in user input
    			if (decimalCount > 1) {
    				validProjectGrade = false;
    				errorMessage = "Make sure to only enter 1 decimal point!";
    			}

    			}
    			
    		}    			
    		    		
    	}  
    	
    	
    	// Convert the string entered by the user to a double if the input is a valid number
    	// Otherwise the project grade will default to zero
    	
    	if (validProjectGrade) {
    		value = Double.parseDouble(valueAsString);
    	}
    	
    	// Check if the number entered by the user is a valid percentage grade
    	// If valid, include it in the grade computation
    	if (value < 0 || value > maxValue) {
    		errorMessage = "Project grade should be between 0% and 100%.";
    		value = 0;
    	}
    	
    	return errorMessage;
    
	}
	
}
