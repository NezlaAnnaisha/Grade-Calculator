package application;

public class Grade {
	double value;
	int maxValue = 100;
	double weight;
	
	/** 
	 * Assigns the values needed to by user for calculation used in weighted percentage grade. This
	 * constructor is used to initialize the course grade instance variables. There is nothing being returned.
	 * 
	 * @param gradeValue a Double that holds the value entered by the user intended to be a grade
	 * @param maxPossibleValue an Integer that states the maximum possible value the user can enter as a grade
	 * @param weightTowardsCourseGrade a Double that declares the weight of a value towards the course grade
	 */
	
	Grade(double gradeValue, int maxPossibleValue, double weightTowardsCourseGrade){
		value = gradeValue;
		maxValue = maxPossibleValue;
		weight = weightTowardsCourseGrade;
	}
	
	double getWeightedPercentageGrade() {
		return value * 100 * weight / maxValue;
	}
	
	
	
    /**
     * Convert the value entered to a double value. This method will verify that the value
     * entered is indeed a number and is a valid percentage grade (between 0 and 100). If the
     * value entered is not a valid percentage grade, this method will return 0.0 as the
     * grade instead.
     * 
     * @param valueAsString a String that holds a value entered by the user intended to be a project grade
     * @return the project value entered by the user if it is a valid percentage grade and 0 otherwise
     */
	
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
    			}
    			
    			// Check if more than 1 decimal point exists in user input
    			if (decimalCount > 1) {
    				validProjectGrade = false;
    				errorMessage = "Make sure to only enter 1 decimal point!";
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
