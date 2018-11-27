package student;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import comm.Connector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Quiz extends Application{

	private int pageNum = 0;
	private String studentEmail;

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

				if(Proxy.checkCode(Integer.parseInt(loginPage.getCodeField().getText())) == 0) {

					Proxy.quizCode = Integer.parseInt(loginPage.getCodeField().getText());
					studentEmail = loginPage.getEmail();
					qPages.readData();
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
				pageNum++;
				qPages.generatePage(pageNum);
				stage.setScene(qPages.getScenes().get(pageNum));
			}});

		qPages.getBtnSubmit().setOnAction(new EventHandler<ActionEvent>() {


			@Override
			public void handle(ActionEvent arg0) {
				try {
					try {
						String bleh[] = {"d", "b", "c"};
						String quizName = "testquiz";
						String sql = "INSERT INTO " + quizName + " VALUES (?, ?, ?);";
					
						Connector conn = new Connector();
						conn.connect();
						Array arrResponses = Connector.connect.createArrayOf("text", bleh); //Name of array
						PreparedStatement p = Connector.connect.prepareStatement(sql);
						p.setString(1, studentEmail);
						p.setArray(2, arrResponses); 
						p.setInt(3, 80); //Final grade once calculated
						p.executeUpdate();
					} catch (SQLException e) {
						e.printStackTrace();
					}
					
					Thread.sleep(1000);
				}
				catch (InterruptedException e){
					e.printStackTrace();
				}
				stage.close();

			}


			
		});

		stage.setScene(loginPage.getScene());
		stage.show();



	}

}