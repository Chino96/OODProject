package student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;

import comm.DataBase;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class QuestionPages {

	// use file to generate questions and answers

	// use Question object to store questions and answers
	ArrayList<Question> questions;
	ArrayList<String> answers;
	ArrayList<String> questionNum;
	TextArea shortAnswer;
	// list of buttons
	ArrayList<RadioButton> answerBtnList;
	ToggleGroup group;
	int MCQ = 0;

	// arraylist of scenes each scene is a new page
	ArrayList<Scene> scenes;

	// next page button
	Button btnNext;
	Button btnSubmit;

	// previous button

	String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #957D3F ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	String buttonHoverStyle = "-fx-base: #957D3F; -fx-background-radius: 25px; -fx-border-color: #FFFFFF ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	String backgroundColor = "-fx-background-color: #041E60";

	private double paneWidth = 700;
	private double paneHeight = 400;
	int count = 0;

	// Adds shadow effect to buttons when hovered over
	private Color colorOn = new Color(0.5843, 0.4902, 0.2471, 1);
	private Color colorOff = new Color(0.5, 0.5, 0.5, 0);
	private DropShadow shadowOn = new DropShadow(20, colorOn);
	private DropShadow shadowOff = new DropShadow(20, colorOff);

	// no arg constructor
	public QuestionPages() {
		// initialize variables in constructor
		scenes = new ArrayList<>();
		answers = new ArrayList<>();
		answerBtnList = new ArrayList<>();
		questions = new ArrayList<>();
		questionNum = new ArrayList<>();

		shadowOn.setSpread(0.7);

		btnNext = new Button("Next");
		btnNext.setPrefWidth(80);
		btnNext.setPrefHeight(40);
		btnNext.setLayoutX(paneWidth - 110);
		btnNext.setLayoutY(paneHeight - 55);
		btnNext.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnNext.setStyle(buttonStyle);
		btnNext.setOnMouseEntered(e -> btnNext.setStyle(buttonHoverStyle));
		btnNext.setOnMouseExited(e -> btnNext.setStyle(buttonStyle));

		btnSubmit = new Button("Submit Quiz");
		btnSubmit.setPrefWidth(150);
		btnSubmit.setPrefHeight(40);
		btnSubmit.setLayoutX(paneWidth - 190);
		btnSubmit.setLayoutY(paneHeight - 55);
		btnSubmit.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnSubmit.setStyle(buttonStyle);
		btnSubmit.setOnMouseEntered(e -> btnSubmit.setStyle(buttonHoverStyle));
		btnSubmit.setOnMouseExited(e -> btnSubmit.setStyle(buttonStyle));

		// read document when instantiated
	}

	// method to create page
	public void generatePage(int index) {

		// create new pane and set the dimensions
		Pane pane = new Pane();
		pane.setPrefHeight(paneHeight);
		pane.setPrefWidth(paneWidth);
		pane.setStyle(backgroundColor);

		// add the next button to the page
		if (index == questions.size()-1) {
			pane.getChildren().add(btnSubmit);
		} else {
			pane.getChildren().add(btnNext);
		}

		// create label for question
		Label question = new Label(questions.get(index).question);

		question.setTextFill(Color.WHITE);
		question.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		question.setPrefWidth(paneWidth - 30);
		question.setLayoutX(15);
		question.setLayoutY(15);
		question.setWrapText(true);

		// add question to pane
		pane.getChildren().add(question);

		// create array of radio buttons
		RadioButton[] rdioAnswer = new RadioButton[questions.get(index).answers.size()];

		double yPos = question.getLayoutY() + 90;

		Text answer = new Text("A) ");

		group = new ToggleGroup();
		// loop through all answers and create radio buttons
		for (int i = 0; i <= rdioAnswer.length - 1; i++) {

			switch (i) {
			case 1:
				answer.setText("B) ");
				break;
			case 2:
				answer.setText("C) ");
				break;
			case 3:
				answer.setText("D) ");
				break;
			case 4:
				answer.setText("E) ");
				break;
			case 5:
				answer.setText("F) ");
				break;
			}
			answer.setFont(Font.font("Arial", 20));
			// if the answer is a # create a text area
			if (questions.get(index).answers.get(i).equals("#")) {
				shortAnswer = new TextArea();
				shortAnswer.setWrapText(true);
				shortAnswer.setLayoutX(30);
				shortAnswer.setLayoutY(yPos);
				shortAnswer.setPrefWidth(paneWidth - 60);
				shortAnswer.setPrefHeight(paneHeight - 180);
				shortAnswer.setStyle(
						buttonStyle + "-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-width: 5px;");
				pane.getChildren().add(shortAnswer);
			} else {
				// otherwise create a radio button for the answer
				rdioAnswer[i] = new RadioButton();
				rdioAnswer[i].setText(answer.getText() + questions.get(index).answers.get(i));
				rdioAnswer[i].setLayoutX(question.getLayoutX() + 20);
				rdioAnswer[i].setLayoutY(yPos);
				rdioAnswer[i].setTextFill(Color.WHITE);

				rdioAnswer[i].setFont(Font.font("Arial", 14));

				rdioAnswer[i].setToggleGroup(group);
				pane.getChildren().add(rdioAnswer[i]);
				yPos += 30;
			}
			
		}
		// add new scene using the pane
		scenes.add(new Scene(pane));

	}

	public ArrayList<Scene> getScenes() {
		return scenes;
	}

	public Button getBtnNext() {
		return btnNext;
	}

	public Button getBtnSubmit() {
		return btnSubmit;
	}

	public void readData() {
		DataBase db = new DataBase("student", "gsouthern");
		ResultSet rs = db.Read("SELECT * from " + Proxy.getQuizName(Proxy.quizCode)+"questions");
		try {
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {
				for (int i = 2; i <= rsm.getColumnCount(); i++) {
					if (i == 2) {
						questions.add(new Question(rs.getString(i)));
					} else if (i == 3) {
						questions.get(questions.size() - 1).cAnswer = rs.getString(i);

					} else if (i == 4) {
						int k = 1;

						if (rs.getString(i) != null) {

							for (int j = 0; j < rs.getString(i).length(); j++) {
								if (rs.getString(i).charAt(j) == ',') {
									questions.get(questions.size() - 1).answers
											.add(rs.getString(i).substring(k, j));
									k = j + 1;
								} else if ((j + 1) == rs.getString(i).length()) {
									questions.get(questions.size() - 1).answers
											.add(rs.getString(i).substring(k, j));
									
								}
							}
						}
						else {
							questions.get(questions.size() - 1).answers
							.add("#");
							questions.get(questions.size() - 1).cAnswer = "#";
							MCQ++;
						}
						
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
