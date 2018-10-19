package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Quiz extends Application{

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
				}
			}});

		qPages.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				pageNum++;
				qPages.generatePage(pageNum);
				stage.setScene(qPages.getScenes().get(pageNum));
			}});

		stage.setScene(loginPage.getScene());
		stage.show();



	}

}