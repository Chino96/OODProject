package gui;

import java.util.ArrayList;

import comm.DataBase;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Quiz extends Application{
	private DataBase db = new DataBase();

	private int pageNum = 0;

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		//create new login page
		LoginPage loginPage = new LoginPage();
		stage.setTitle("Quiz Title");

		//create question page
		QuestionPages qPages = new QuestionPages();

		//set the action for the button to go to the 0 page
		loginPage.getBtnLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if(loginPage.getEmailField().getText().equals("1234")) {
					qPages.generatePage(pageNum);
					stage.setScene(qPages.getScenes().get(pageNum));
					loginPage.resetError();
				}
				else{
					loginPage.setError();
				}
			}});

		qPages.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				System.out.println("here1");
				ArrayList<String> bleh = new ArrayList<>();
				bleh.add("a");
				bleh.add("b");
				bleh.add("c");
				String quizName = "testquiz";
				db.Write("INSERT INTO " + quizName + " VALUES ('abc@gmail.com', "
						+ "bleh" + 80 + ");");
				pageNum++;
				qPages.generatePage(pageNum);
				stage.setScene(qPages.getScenes().get(pageNum));
			}});
		
		qPages.getBtnSubmit().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
			/*	System.out.println("here1");
				ArrayList<String> bleh = new ArrayList<>();
				bleh.add("a");
				bleh.add("b");
				bleh.add("c");
				String quizName = "testquiz";
				System.out.println("here2");
				db.Write("INSERT INTO " + quizName + " VALUES ('abc@gmail.com', "
						+ "bleh" + 80 + ");");
				System.out.println("here3");*/
			}});

		stage.setScene(loginPage.getScene());
		stage.show();



	}
}