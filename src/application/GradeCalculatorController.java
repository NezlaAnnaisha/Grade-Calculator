package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class GradeCalculatorController {

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoiceBox;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private Slider quizSlider;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label courseGradeLabel;
    
    @FXML
    Label projectErrorLabel;
    
    /**
     * Convert the value entered to a double value. This method will verify that the value
     * entered is indeed a number and is a valid percentage grade (between 0 and 100). If the
     * value entered is not a valid percentage grade, this method will return 0.0 as the project
     * grade instead.
     * 
     * @param valueEntered a String that holds a value entered by the user intended to be a project grade
     * @return the project value entered by the user if it is a valid percentage grade and 0 otherwise
     */
    
    double getProjectGrade(String valueEntered) {
    	// Check that string entered by user is valid decimal number
    	boolean validProjectGrade = true;
    	int decimalCount = 0;
    	for (char c : valueEntered.toCharArray()) {
    		// Check if the character is a digit
    		if (!Character.isDigit(c)) {
    			validProjectGrade = false;
    			projectErrorLabel.setText("Do not use characters in the project grade! Make sure to enter a number.");
    		
    			// Check if the character is a negative symbol
    			if (!Character.isDigit(c) && c == '-') {
    				validProjectGrade = true;
    			}
    			
    			// Check if the character is a decimal point
    			if (!Character.isDigit(c) && c == '.') {
    				validProjectGrade = true;
    				projectErrorLabel.setText("");
    				decimalCount++;
    			}
    			
    			// Check if more that 1 decimal point exists in user input
    			if (decimalCount > 1) {
    				validProjectGrade = false;
    				projectErrorLabel.setText("Make sure to only enter 1 decimal point!");
    			}
    		
    		}
    	}
    	
    	// Convert the string entered by the user to a double if the input is a valid number
    	// Otherwise the project grade defaults to zero
    	double projectGrade = 0;
    	if (validProjectGrade) {
    		projectGrade = Double.parseDouble(valueEntered);
    	}
    	
    	// Check if the number entered by the user is a valid percentage grade
    	// If valid, include it in the grade computation
    	if (projectGrade < 0 || projectGrade > 100) {
    		projectErrorLabel.setText("Project grade should be between 0% and 100%.");
    		projectGrade = 0;
    	}
    	
    	return projectGrade;
    }

    @FXML
    void calculateGrade(ActionEvent event) {
    	
    	/**
    	 * 
    	 * Takes user input based on grades entered. This method will calculate the average value 
    	 * based on the graded components entered. Each grade are calculated with their respective weights.
    	 * This method will return the total average course grade.
    	 * 
    	 * @param projectGrade a Double that holds a value entered by the user intended to be a project grade
    	 * @param quizGrade a Double that holds value chosen on slider by the user intended to be a quiz grade
    	 * @param requiredCodingChallenges an Integer that holds value chosen by the user intended to be a number of required coding challenges completed
    	 * @param optionalCodingChallanges an Integer that holds value chosen by the user intended to be a number of optional coding challenges completed
    	 * @return the average course grade calculated based on marks entered by user
    	 */
    	
    	// Clear all error messages
    	projectErrorLabel.setText("");
    	
    	double courseGrade = 0.0;
    	
    	// Assuming project grade worth 50% towards course grade
    	String projectValueEntered = projectGradeTextfield.getText();
    	
    	double projectGrade = getProjectGrade(projectValueEntered);
    	
    	courseGrade = courseGrade + projectGrade * 50/100;
    	
    	System.out.println("Project grade: " + projectGrade + ", Course grade so far: " + courseGrade);
    	
    	// Assuming quizzes are worth 25% towards course grade (optional & required total)
    	double quizGrade = quizSlider.getValue();
    	courseGrade = courseGrade + (quizGrade * 100/10) * 0.25;
    	System.out.println("Quiz grade: " + quizGrade + ", Course grade so far: " + courseGrade);
    	
    	int requiredCodingChallenges = requiredCodingChallengesChoiceBox.getValue();
    	courseGrade = courseGrade + (requiredCodingChallenges * 100/20) * 0.25;
    	System.out.println("Required coding challenges passed: " + requiredCodingChallenges + ", Course grade so far: " + courseGrade);
    	
    	int optionalCodingChallenges = optionalCodingChallengesChoiceBox.getValue();
    	courseGrade = courseGrade + (optionalCodingChallenges * 100/20) * 0.25;
    	System.out.println("Optional coding challenges passed: " + optionalCodingChallenges + ", Course grade so far: " + courseGrade);
    	
    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }

}

