import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class QuestionPages {

	//use file to generate questions and answers
	File txtQuestions = new File("src\\questions.txt");

	//use Question object to store questions and answers
	ArrayList<Question> questions;
	ArrayList<String> answers;

	//list of buttons
	ArrayList<RadioButton> answerBtnList;

	//arraylist of scenes each scene is a new page
	ArrayList<Scene> scenes;

	//next page button
	Button btnNext;

	//previous button

	String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #14336C; -fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-insets: 0";
	String backgroundColor = "-fx-background-color: #98B4C2";

	private double paneWidth = 400;
	private double paneHeight = 300;
	int count = 0;
	int lineLength = 0;

	//no arg constructor
	public QuestionPages() {
		//initialize variables in constructor
		scenes = new ArrayList<Scene>();
		answers = new ArrayList<>();
		answerBtnList = new ArrayList<>();
		questions = new ArrayList<>();
		btnNext = new Button("Next");
		btnNext.setPrefWidth(80);
		btnNext.setPrefHeight(40);
		btnNext.setLayoutX(paneWidth - 95);
		btnNext.setLayoutY(paneHeight - 55);
		btnNext.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnNext.setStyle(buttonStyle);

		//read document when instantiated
		readDocument();


	}

	//read the file provided
	public void readDocument() {

		//string to hold value of line read
		String line;


		//catch file not found exceptions
		try {
			//create file readers
			FileReader fileReader = new FileReader(txtQuestions);
			BufferedReader txtReader = new BufferedReader(fileReader);


			//loop through every line of the file
			while ((line = txtReader.readLine()) != null) {

				//line must contain a ? to be read as a question
				if (line.contains("?")) {
					//Increment count for question number
					count++;
					//if the line is too long make it multiline
					if (line.length() > 90) {
						line = line.substring(0, line.lastIndexOf(" ", 30)) + "\n"
								+ line.substring(line.lastIndexOf("", 27));
					}
					//add new question to question arraylist
					questions.add(new Question("Question " + count + ":\n" + line));
				}
				//if the line has no question mark and isn't a blank line
				else if (!line.contains("?") && !line.isEmpty()) {
					//add the answer to the last question in question arraylist
					questions.get(questions.size() - 1).answers.add(line);
				}

			}
			txtReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//method to create page
	public void generatePage(int index) {

		//create new pane and set the dimensions
		Pane pane = new Pane();
		pane.setPrefHeight(paneHeight);
		pane.setPrefWidth(paneWidth);
		pane.setStyle(backgroundColor);

		//add the next button to the page
		pane.getChildren().add(btnNext);

		//create label for question
		Label question = new Label(questions.get(index).question);
		question.setLayoutX(15);
		question.setLayoutY(15);

		//add question to pane
		pane.getChildren().add(question);

		//create array of radio buttons
		RadioButton[] rdioAnswer = new RadioButton[questions.get(index).answers.size()];

		double yPos = question.getLayoutY() + 60;

		//loop through all answers and create radio buttons
		for (int i = 0; i <= rdioAnswer.length - 1; i++) {

			// if the answer is a # create a text area
			if (questions.get(index).answers.get(i).equals("#")) {
				TextArea shortAnswer = new TextArea();
				shortAnswer.setLayoutX(35);
				shortAnswer.wrapTextProperty();
				shortAnswer.setLayoutY(yPos);
				shortAnswer.setPrefWidth(paneWidth - 70);
				shortAnswer.setPrefHeight(paneHeight - 160);
				shortAnswer.setStyle(buttonStyle +"; -fx-background-radius: 0px; -fx-border-radius: 0px");
				pane.getChildren().add(shortAnswer);
			} else {
				//otherwise create a radio button for the answer
				rdioAnswer[i] = new RadioButton();
				rdioAnswer[i].setText(questions.get(index).answers.get(i));
				rdioAnswer[i].setLayoutX(question.getLayoutX() + 20);
				rdioAnswer[i].setLayoutY(yPos);
				pane.getChildren().add(rdioAnswer[i]);
				yPos += 25;
			}
		}
		//add new scene using the pane
		scenes.add(new Scene(pane));

	}

	public ArrayList<Scene> getScenes() {
		return scenes;
	}

	public Button getBtnNext() {
		return btnNext;
	}

}


