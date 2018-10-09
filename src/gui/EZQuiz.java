package gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class EZQuiz extends Application{
	
	private int pageNum = 0;

    public static void main(String args[]) {
       launch(args);
    }

	@Override
	public void start(Stage stage) throws Exception {
		//create new login page
		LoginPage loginPage = new LoginPage();
		
		//create question page
		QuestionPages qPages = new QuestionPages();
		
		//set the action for the button to go to the 0 page
		loginPage.getBtnLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if(loginPage.getEmailField().getText().equals("Admin") && loginPage.getPswdField().getText().equals("password")) {
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
