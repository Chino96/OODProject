package gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class QuestionPages {

		//use file to generate questions and answers
		File txtQuestions = new File("src/questions.txt");

		//use Question object to store questions and answers
		ArrayList<Question> questions;
		ArrayList<String> answers;
		ArrayList<String> questionNum;
		//list of buttons
		ArrayList<RadioButton> answerBtnList;
		
		//arraylist of scenes each scene is a new page
		ArrayList<Scene> scenes;
		
		//next page button
		Button btnNext;
		Button btnSubmit;

		//previous button

		String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #957D3F ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	    String buttonHoverStyle = "-fx-base: #957D3F; -fx-background-radius: 25px; -fx-border-color: #FFFFFF ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
		String backgroundColor = "-fx-background-color: #041E60";

		private double paneWidth = 700;
		private double paneHeight = 400;
		int count = 0;

		//Adds shadow effect to buttons when hovered over
		private Color colorOn = new Color(0.5843, 0.4902, 0.2471, 1);
		private Color colorOff = new Color(0.5, 0.5, 0.5, 0);
		private DropShadow shadowOn = new DropShadow(20, colorOn);
		private DropShadow shadowOff = new DropShadow(20, colorOff);
		
		//no arg constructor
		public QuestionPages() {
			//initialize variables in constructor
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
			
			//read document when instantiated 
			readDocument();


		}

		//read the file provided
		public void readDocument() {
			
			//string to hold value of line read
			String line;
			String prevLine;
			String currLine = "";

			//catch file not found exceptions
			try {
				//create file readers
				FileReader fileReader = new FileReader(txtQuestions);
				BufferedReader txtReader = new BufferedReader(fileReader);



				//loop through every line of the file
				while ((line = txtReader.readLine()) != null) {
					prevLine = currLine;
					currLine = line;

					//Line before the question has to be blank to start a new question
					if (prevLine.length() < 1) {
						//Increment count for question number
						count++;
						//add new question to question arraylist
						questions.add(new Question(count + " )  " + currLine));


					}
					//If the previous line is not empty and current line is not empty, it will be added to answers
					else if (currLine.length() >= 1) {
						//add the answer to the last question in question arraylist
						questions.get(questions.size()-1).answers.add(currLine);
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

			int pageCount;
			pageCount = index;
			//add the next button to the page
			if(pageCount < count-1){
				pane.getChildren().add(btnNext);
			}else {
				pane.getChildren().add(btnSubmit);
			}



			//create label for question
			Label question = new Label(questions.get(index).question);


			question.setTextFill(Color.WHITE);
			question.setFont(Font.font("Arial" , FontWeight.BOLD, 16));
			question.setPrefWidth(paneWidth-30);
			question.setLayoutX(15);
			question.setLayoutY(15);
			question.setWrapText(true);

			//add question to pane
			pane.getChildren().add(question);


			//create array of radio buttons  
			RadioButton[] rdioAnswer = new RadioButton[questions.get(index).answers.size()];
			
			double yPos = question.getLayoutY() + 90;

			Text answer = new Text("A) ");

			ToggleGroup group = new ToggleGroup();
			//loop through all answers and create radio buttons
			for (int i = 0; i <= rdioAnswer.length - 1; i++) {

				switch(i){
					case 1: answer.setText("B) "); break;
					case 2: answer.setText("C) "); break;
					case 3: answer.setText("D) "); break;
					case 4: answer.setText("E) "); break;
					case 5: answer.setText("F) "); break;
				}
				answer.setFont(Font.font("Arial", 20));
				// if the answer is a # create a text area
				if (questions.get(index).answers.get(i).equals("#")) {
					TextArea shortAnswer = new TextArea();
					shortAnswer.setWrapText(true);
					shortAnswer.setLayoutX(30);
					shortAnswer.setLayoutY(yPos);
					shortAnswer.setPrefWidth(paneWidth - 60);
					shortAnswer.setPrefHeight(paneHeight - 180);
					shortAnswer.setStyle(buttonStyle +"-fx-background-radius: 8px; -fx-border-radius: 8px; -fx-border-width: 5px;");
					pane.getChildren().add(shortAnswer);
				} else {

					//otherwise create a radio button for the answer
					rdioAnswer[i] = new RadioButton();
					rdioAnswer[i].setText(answer.getText() + questions.get(index).answers.get(i));
					rdioAnswer[i].setLayoutX(question.getLayoutX() + 20);
					rdioAnswer[i].setLayoutY(yPos);
					rdioAnswer[i].setTextFill(Color.WHITE);;
					rdioAnswer[i].setFont(Font.font("Arial", 14));


					rdioAnswer[i].setToggleGroup(group);
					pane.getChildren().add(rdioAnswer[i]);
					yPos += 30;
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
		public Button getBtnSubmit() {
			return btnSubmit;
		}


}


