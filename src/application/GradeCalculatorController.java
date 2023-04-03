package application;

import java.util.ArrayList;
import java.util.Collections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class GradeCalculatorController {
	Stage applicationStage;
	
	@FXML
	private Label courseGradeLabel;	

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
    private Label projectErrorLabel;   
    
    // Loop for required quiz grades
    double requiredAverageQuizGrade = 0.0;
    
    void calculateRequiredAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	
    	
    	// Make sure to reset to zero, in case it contains a previously computed value
    	requiredAverageQuizGrade = 0.0;
    	
    	for(TextField quizGradeTextfield : quizGradeTextfields) {
    		requiredAverageQuizGrade += Double.parseDouble(quizGradeTextfield.getText());
    	}
    	requiredAverageQuizGrade = requiredAverageQuizGrade / 15;
    	// Label to show user inputed average
        requiredQuizLabel.setText(String.format("(Current required quiz average is %.2f out of 10)",requiredAverageQuizGrade));
    }
    
    
    // Loop for optional quiz grades
    double optionalAverageQuizGrade = 0.0;
    
    void calculateOptionalAverageQuizGrade(Scene mainScene, ArrayList<TextField> quizGradeTextfields) {
    	applicationStage.setScene(mainScene);
    	
    	// Make sure to reset to zero, in case it contains a previously computed value
    	optionalAverageQuizGrade = 0.0;
    	
    	// Array list to compare grades if user has more than 5 inputs
    	ArrayList<Double> gradesList = new ArrayList<Double>();  	
    	
    	// Convert & add each grade to list
    	for(TextField quizGradeTextfield : quizGradeTextfields) {    		
    		double doubleQuizGrade = Double.parseDouble(quizGradeTextfield.getText());     		
    		gradesList.add(doubleQuizGrade);
    	}	
        
    	// Sort grades in array smallest to largest
        Collections.sort(gradesList);
        	
        // Remove lowest grades if user inputs more than 5 grades
        if (gradesList.size() == 6) {
        	gradesList.remove(0);
        } else if (gradesList.size() == 7) {
        	gradesList.remove(0);
        	gradesList.remove(0);
        }       		
        	
        
        for (Double gradeList : gradesList) {       		
        	optionalAverageQuizGrade += gradeList;        		
        	}  
    	
    	optionalAverageQuizGrade = optionalAverageQuizGrade / 5;
    	// Label to show user inputed average
    	optionalQuizLabel.setText(String.format("(Current required quiz average is %.2f out of 10)",optionalAverageQuizGrade));
    }
    
    @FXML
    void getRequiredQuizGrades (ActionEvent enterRequiredQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfRequiredQuizzes = requiredQuizzesChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.setPadding(new Insets(10, 10, 10, 10));
    	
    	Label requiredQuizTitle = new Label("Enter your required quiz grades.");
    	requiredQuizTitle.setPadding(new Insets(10, 10, 10, 10));
    	quizGradeContainer.getChildren().add(requiredQuizTitle);
    	
    	// Create a list that we'll put all the textFields with quiz grades
    	ArrayList<TextField> quizGradeTextfields = new ArrayList<TextField>();
    	
    	while (rowsCreated < numberOfRequiredQuizzes) {
    		
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
    	
    	doneButton.setOnAction(doneEvent -> calculateRequiredAverageQuizGrade(mainScene, quizGradeTextfields));
    	quizGradeContainer.getChildren().add(buttonContainer);
    	
    	Scene quizGradeScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradeScene);
    }
    
    @FXML
    void getOptionalQuizGrades (ActionEvent enterOptionalQuizGradeEvent) {
    	Scene mainScene = applicationStage.getScene();
    	
    	int numberOfOptionalQuizzes = optionalQuizzesChoiceBox.getValue();
    	int rowsCreated = 0;
    	VBox quizGradeContainer = new VBox();
    	quizGradeContainer.setPadding(new Insets(10, 10, 10, 10));
    	
    	Label optionalQuizTitle = new Label("Enter your optional quiz grades.");
    	optionalQuizTitle.setPadding(new Insets(10, 10, 10, 10));
    	quizGradeContainer.getChildren().add(optionalQuizTitle);
    	
    	// Create a list that we'll put all the textFields with quiz grades
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
    	
    	Scene quizGradeScene = new Scene(quizGradeContainer);
    	applicationStage.setScene(quizGradeScene);
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
    	
    	
    	
    	Grade projectGrade = new Grade(0, 100, 0.5);
    	projectErrorLabel.setText(projectGrade.setValue(projectGradeTextfield.getText()));
    	
    	Grade requiredQuizGrade = new Grade(requiredAverageQuizGrade, 20, .25);	
    	Grade optionalQuizGrade = new Grade(optionalAverageQuizGrade, 20, .25);
    	
    	Grade requiredCodingChallengeGrade = new Grade(requiredCodingChallengesChoiceBox.getValue(), 20, .25);
    	Grade optionalCodingChallengeGrade = new Grade(optionalCodingChallengesChoiceBox.getValue(), 20, .25);
    	
    	double courseGrade = projectGrade.getWeightedPercentageGrade()+requiredQuizGrade.getWeightedPercentageGrade()+
    			optionalQuizGrade.getWeightedPercentageGrade()+requiredCodingChallengeGrade.getWeightedPercentageGrade()+
    			optionalCodingChallengeGrade.getWeightedPercentageGrade();
    	
    	//Display result of calculation to the user in the window
    	//Display result to two digits after decimal point
    	courseGradeLabel.setText(String.format("Your course grade is %.2f",courseGrade));
    	

    }

}