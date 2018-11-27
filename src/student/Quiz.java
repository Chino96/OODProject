package student;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import comm.Connector;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class Quiz extends Application {

	private int pageNum = 0;
	private String studentEmail;
	private int cans = 0;
	private ArrayList<String> resPon = new ArrayList();

	public static void main(String args[]) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		// create new login page
		LoginPage loginPage = new LoginPage();
		stage.setTitle("Quiz Title");

		// create question page
		QuestionPages qPages = new QuestionPages();

		// set the action for the button to go to the 0 page
		loginPage.getBtnLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {

				if (Proxy.checkCode(Integer.parseInt(loginPage.getCodeField().getText())) == 0) {
					Proxy.quizCode = Integer.parseInt(loginPage.getCodeField().getText());
					studentEmail = loginPage.getEmail();
					qPages.readData();
					qPages.generatePage(pageNum);
					stage.setScene(qPages.getScenes().get(pageNum));
					loginPage.resetError();
				} else {
					loginPage.setError();
				}
			}
		});

		qPages.getBtnNext().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent e) {
				if (qPages.questions.get(pageNum).cAnswer != "#") {
					RadioButton btn = (RadioButton) qPages.group.getSelectedToggle();

					String s = qPages.questions.get(pageNum).cAnswer;
					String ss = btn.getText().substring(3);
					if (btn.getText().substring(3).contains(qPages.questions.get(pageNum).cAnswer.trim())) {
						cans++;

					}

					resPon.add(btn.getText().substring(3));
				}
				else {
					resPon.add(qPages.shortAnswer.getText());
				}
				pageNum++;
				qPages.generatePage(pageNum);
				stage.setScene(qPages.getScenes().get(pageNum));
			}
		});

		qPages.getBtnSubmit().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				if (qPages.questions.get(pageNum).cAnswer != "#") {
					RadioButton btn = (RadioButton) qPages.group.getSelectedToggle();

					String s = qPages.questions.get(pageNum).cAnswer;
					String ss = btn.getText().substring(3);
					if (btn.getText().substring(3).contains(qPages.questions.get(pageNum).cAnswer.trim())) {
						cans++;
						System.out.println("Damn we made it");

					}

					resPon.add(btn.getText().substring(3));
				}
				try {
					try {
						String bleh[] = resPon.toArray(new String[0]);
						String quizName = Proxy.quizName;
						String sql = "INSERT INTO " + quizName + " VALUES (?, ?, ?);";

						Connector conn = new Connector();
						conn.connect();
						Array arrResponses = Connector.connect.createArrayOf("text", bleh); // Name of array
						PreparedStatement p = Connector.connect.prepareStatement(sql);
						p.setString(1, studentEmail);
						p.setArray(2, arrResponses);
						p.setDouble(3, (((double)cans / ((double)qPages.questions.size()-qPages.MCQ)) * 100)); // Final grade once calculated
						p.executeUpdate();
						System.out.println(((double)cans / ((double)qPages.questions.size()-qPages.MCQ)) * 100);
					} catch (SQLException e) {
						e.printStackTrace();
					}

					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				stage.close();				

				GradePage gp = new GradePage("Your Grade\n\n    "+Math.round((((double)cans / ((double)qPages.questions.size()-qPages.MCQ)) * 100) * 100.0) / 100.0 + "%");

			}

		});

		stage.setScene(loginPage.getScene());
		stage.show();

	}

}