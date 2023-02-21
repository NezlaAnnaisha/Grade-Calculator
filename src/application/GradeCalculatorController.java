package application;

import java.util.ArrayList;
import java.util.Collections;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GradeCalculatorController {
	Stage applicationStage;

    @FXML
    private ChoiceBox<Integer> requiredCodingChallengesChoiceBox;

    @FXML
    private ChoiceBox<Integer> optionalCodingChallengesChoiceBox;

    @FXML
    private ChoiceBox<Integer> requiredQuizzesChoiceBox;
    
    @FXML
    private ChoiceBox<Integer> optionalQuizzesChoiceBox;

    @FXML
    private TextField projectGradeTextfield;
    
    @FXML
    private Label requiredQuizLabel;
    
    @FXML
    private Label optionalQuizLabel;
    
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
    
    // Loop for the required quiz grades
    double requiredAverageQuizGrade = 0.0;
    void calculateRequiredAverageQuizGrade (Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	
    	// Make sure to reset to zero, in case it contains a previously computed value
    	requiredAverageQuizGrade = 0.0;
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		requiredAverageQuizGrade += Double.parseDouble(quizGradeTextfield.getText());
    	}
    	requiredAverageQuizGrade = requiredAverageQuizGrade / quizGradeTextfields.size();
    	
    	// Label to show user their inputed average
    	requiredQuizLabel.setText(String.format("(Current required quiz average is %.1f out of 10.)", requiredAverageQuizGrade));
    	
    }
    
    // Loop for the optional quiz grades
    double optionalAverageQuizGrade = 0.0;
    void calculateOptionalAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	
    	//Make sure to reset to zero, in case it contains a previously computed value
    	optionalAverageQuizGrade = 0.0;
    	
    	// List to compare optional grades if user has more than 5 inputs
    	ArrayList<Double> optionalQuizGradesList = new ArrayList<Double>();
    	
    	// Convert & add each grade to list
    	for (TextField quizGradeTextfield : quizGradeTextfields) {
    		optionalAverageQuizGrade = Double.parseDouble(quizGradeTextfield.getText());
    		optionalQuizGradesList.add(optionalAverageQuizGrade);
    	}
    	
    	// Sort grades in array from smallest to largest
    	Collections.sort(optionalQuizGradesList);
    	
    	// Remove lowest grades to ensure only 5 inputs are counted
    	if (optionalQuizGradesList.size() == 6) {
    		optionalQuizGradesList.remove(0);
    	} else if (optionalQuizGradesList.size() == 7) {
    		optionalQuizGradesList.remove(0);
    		optionalQuizGradesList.remove(0);
    	}
    	
    	for (Double optionalQuizGrade : optionalQuizGradesList) {
    		optionalAverageQuizGrade += optionalQuizGrade;
    	}
    	optionalAverageQuizGrade = optionalAverageQuizGrade / optionalQuizGradesList.size();
    	
    	// Label to show user their inputed average
    	optionalQuizLabel.setText(String.format("(Current optional quiz everage is %.1f out of 10.)", optionalAverageQuizGrade));
    	
    	
    }
    
    @FXML
    void getRequiredQuizGrades(ActionEvent enterRequiredQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfRequiredQuizzes = requiredQuizzesChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.setPadding(new Insets(10,10,10,10));
    	
    	Label requiredQuizTitle = new Label("Enter your required quiz grades.");
    	requiredQuizTitle.setPadding(new Insets(10,10,10,10));
    	quizGradeContainer.getChildren().add(requiredQuizTitle);
    	
    	// Create a list that we'll put all the text-field with quiz grades
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	while (rowsCreated < numberOfRequiredQuizzes) {		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz grade: ");
        	TextField quizGradeTextfield = new TextField();
        	quizGradeTextfields.add(quizGradeTextfield);
        	rowContainer.setPadding(new Insets(5,5,5,5));
        	
        	rowContainer.getChildren().addAll(quizGradeLabel, quizGradeTextfield);
        	rowsCreated++;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
        	
    	}
    	
    	// Labels and paddings for grades
    	Label userInput = new Label("(Grade input must be out of 10!)");
    	userInput.setPadding(new Insets(10,10,10,10));
    	quizGradeContainer.getChildren().add(userInput);
    	
    	HBox buttonContainer = new HBox();
    	Button doneButton = new Button("Done");
    	buttonContainer.setPadding(new Insets(10,10,10,10));
    	buttonContainer.getChildren().add(doneButton);
    	
    	doneButton.setOnAction(doneEvent -> calculateRequiredAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(buttonContainer);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
    }
    
    @FXML
    void getOptionalQuizGrades(ActionEvent enterOptionalQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfOptionalQuizzes = optionalQuizzesChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.setPadding(new Insets(10,10,10,10));
    	
    	Label optionalQuizTitle = new Label("Enter your optional quiz grades.");
    	optionalQuizTitle.setPadding(new Insets(10,10,10,10));
    	quizGradeContainer.getChildren().add(optionalQuizTitle);
    	
    	// Create a list that we'll put all the text-field with quiz grades
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	
    	while (rowsCreated < numberOfOptionalQuizzes) {		
    		HBox rowContainer = new HBox();
        	Label quizGradeLabel = new Label("Quiz grade: ");
        	TextField quizGradeTextfield = new TextField();
        	quizGradeTextfields.add(quizGradeTextfield);
        	rowContainer.setPadding(new Insets(5, 5, 5, 5));
        	
        	rowContainer.getChildren().addAll(quizGradeLabel, quizGradeTextfield);
        	rowsCreated++;
        	
        	quizGradeContainer.getChildren().add(rowContainer);
        	
    	}
    	
    	Label userInput = new Label("(Grade input must be out of 10!)");
    	userInput.setPadding(new Insets(10, 10, 10, 10));
    	quizGradeContainer.getChildren().add(userInput);
    	
    	HBox buttonContainer = new HBox();
    	Button doneButton = new Button("Done"); 
    	buttonContainer.setPadding(new Insets(10, 10, 10, 10));
    	buttonContainer.getChildren().add(doneButton);
    
    	doneButton.setOnAction(doneEvent -> calculateOptionalAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(buttonContainer);
    	
    	Scene quizGradesScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradesScene);
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
    	// Assuming quizzes are marked out of 20 total; 15 required, 5 optional
    	courseGrade += (requiredAverageQuizGrade * 100/20) * 0.25;
    	System.out.println("Required quiz grade: " + requiredAverageQuizGrade + ", Course grade so far: " + courseGrade);
    	
    	courseGrade += (optionalAverageQuizGrade * 100/20) * 0.25;
    	System.out.println("Optional quiz grade: " + optionalAverageQuizGrade + ", Course grade so far: " + courseGrade);
    	
    	// Assuming coding challenges are worth 25% towards course grade (optional & required total)
    	// Assuming challenges are marked out of 20 total; 15 required, 5 optional
    	int requiredCodingChallenges = requiredCodingChallengesChoiceBox.getValue();
    	courseGrade = courseGrade + (requiredCodingChallenges * 100/20) * 0.25;
    	System.out.println("Required coding challenges passed: " + requiredCodingChallenges + ", Course grade so far: " + courseGrade);
    	
    	int optionalCodingChallenges = optionalCodingChallengesChoiceBox.getValue();
    	courseGrade = courseGrade + (optionalCodingChallenges * 100/20) * 0.25;
    	System.out.println("Optional coding challenges passed: " + optionalCodingChallenges + ", Course grade so far: " + courseGrade);
    	
    	courseGradeLabel.setText(String.format("Your course grade is %.2f", courseGrade));
    }

}

