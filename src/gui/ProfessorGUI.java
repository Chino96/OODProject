package gui;

import comm.Connector;
import comm.DataBase;
import comm.EmailComm;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorGUI extends Application {

	private DataBase dataBase = new DataBase();

	private String emailList = "";
	private String fileName;
	private File questionList;
	private File emailFile;
	ArrayList<Question> questions;

	// Window style

	// Adds shadow effect to buttons when hovered over
	private FileChooser fileChooser = new FileChooser();

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		StartPage startPage = new StartPage(primaryStage);
		SendPage sendPage = new SendPage(primaryStage);
		VerificationPage vPage = new VerificationPage();
		EmailVerificationPage evPage = new EmailVerificationPage();

		// Open help window

		startPage.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				HelpPage hPage = new HelpPage();
				hPage.show();
			}
		});// end btnHelp.setOnAction

		// Set options for "Upload Questions" button
		startPage.getQuestion().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage questionStage = new Stage();
				String qDirectory = System.getProperty("user.home") + "/Documents";
				fileChooser.setTitle("Open Questions File");
				fileChooser.setInitialDirectory(new File(qDirectory));
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
				File file = fileChooser.showOpenDialog(questionStage);

				if (file != null) {
					startPage.getLblQFile().setText(file.getName());
					questionList = file;

				} else {
					startPage.getLblQFile().setText(null);
				}
			}
		});// end question.setOnAction

		// Set options for "Upload Emails" button
		startPage.getEmail().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Stage emailStage = new Stage();
				String eDirectory = System.getProperty("user.home") + "/Documents";
				fileChooser.setTitle("Open Emails File");
				fileChooser.setInitialDirectory(new File(eDirectory));
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
				File file = fileChooser.showOpenDialog(emailStage);

				if (file != null) {
					startPage.getLblEFile().setText(file.getName());

					// Add quiz name to a string so it can be sent to the database
					startPage.setQuizName(file.getName().substring(0, file.getName().length() - 4));
					startPage.getLblEFile().setTextFill(Color.WHITE);
					emailFile = file;
				}
				String line;

				try {
					FileReader fileReader = new FileReader(emailFile);
					BufferedReader txtReader = new BufferedReader(fileReader);

					while ((line = txtReader.readLine()) != null) {
						if (line.length() != 27) {
							throw new Exception();
						}
						emailList += line + ",";
					}
					txtReader.close();
				} catch (Exception e) {
					startPage.getLblEFile().setTextFill(Color.RED);
					startPage.getLblEFile().setText("Improper Format");
				}
			}

		});

		// Set options for "View Reports" button
		startPage.getReports().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Stage reportStage = new Stage();
				String rDirectory = System.getProperty("user.home") + "/Documents";
				fileChooser.setTitle("Open Reports File");
				fileChooser.setInitialDirectory(new File(rDirectory));
				fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

				// Remove the .txt from file name
				fileName = startPage.getLblQFile().getText();
				fileName = fileName.substring(0, fileName.length() - 4);

				// call create report
				gradeReport.createGradeReportTxt(fileName);
				// responseReport.createResponseReportTxt(fileName);
				try {
					// call append report
					gradeReport.appendGradeReportTxt(fileName);
					// responseReport.appendResponseReportTxt(fileName);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				File file = fileChooser.showOpenDialog(reportStage);
				Desktop openFile = Desktop.getDesktop();
				if (file.exists())
					try {
						openFile.open(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				if (file != null) {
					System.out.println(file.toString());
				}
			}
		});// end reports.setOnAction

		// Open Send page
		startPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				sendPage.showSendPage();
			}
		});// end send.setOnAction

		// Options for what "Send Button" does in Send Page
		sendPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (emailFile == null || questionList == null) {
					sendPage.setErrorLabel();
				} else {
					try {
						if (sendPage.getStartMonth() == -1 || sendPage.getStartDay() == -1
								|| sendPage.getStartHour() == -1 || sendPage.getStartMin() == -1)
							throw new NullPointerException();
						if (sendPage.getStartMonth() > sendPage.getEndMonth()) {
							if (sendPage.getStartMonth() == 11
									&& (sendPage.getEndMonth() == 0 || sendPage.getEndMonth() == 1)) {
								vPage.showVerificationPage();
								sendPage.resetErrorLabel();
							} else
								sendPage.setTimeError();

						} else if (sendPage.getStartDay() > sendPage.getEndDay()) {
							if (sendPage.getEndMonth() > sendPage.getStartMonth()) {
								vPage.showVerificationPage();
								sendPage.resetErrorLabel();
							} else
								sendPage.setTimeError();

						} else if (sendPage.getStartHour() > sendPage.getEndHour()) {
							if (sendPage.getStartDay() < sendPage.getEndDay()) {
								vPage.showVerificationPage();
								sendPage.resetErrorLabel();
							} else
								sendPage.setTimeError();

						} else if (sendPage.getStartMin() > sendPage.getEndMin()) {
							if (sendPage.getStartHour() < sendPage.getEndHour()) {
								if (sendPage.getStartDay() < sendPage.getEndDay()) {
									vPage.showVerificationPage();
									sendPage.resetErrorLabel();
								}
							} else
								sendPage.setTimeError();

						} else {
							vPage.showVerificationPage();
							sendPage.resetErrorLabel();
						}
					} catch (NullPointerException e) {
						sendPage.setTimeError();
					}
				}
			}

		});

		// Options for Verification Page
		vPage.getSend().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				fileName = startPage.getLblQFile().getText();
				fileName = fileName.substring(0, fileName.length() - 4);

				EmailComm eCom = new EmailComm();
				eCom.sendEmails(vPage.getEmailField().getText(), vPage.getPassField().getText(), fileName,
						"This is a test for our Project", emailList);

				evPage.showEmailVerificationPage();

				dataBase.Write("CREATE TABLE public.\"" + fileName + "\"" + "("
						+ "\"studentEmail\" text COLLATE pg_catalog.\"default\","
						+ "responses text[] COLLATE pg_catalog.\"default\"," + "\"finalGrade\" double precision" + ")"
						+ "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;" + " " + "INSERT INTO "
						+ "\"QuizCodes\" VALUES ('" + fileName + "', " + sendPage.getQuizCode().getText() + ");" +

						" CREATE TABLE public.\"" + fileName + "questions\"" + "("
						+ "\"questions\" text COLLATE pg_catalog.\"default\","
						+ "canswers text COLLATE pg_catalog.\"default\"," + "\"panswers\" text[]" + ")" + "WITH ("
						+ "OIDS = FALSE)" + "TABLESPACE pg_default;");
				
				int i = 0;
				while (i < questions.size() - 1) {
					try {
						String pAns[] = questions.get(i).answers.toArray(new String[0]);
						String question = questions.get(i).question;
						String sql = "INSERT INTO " + fileName + "Questions" + " VALUES (?, ?, ?);";

						Connector conn = new Connector();
						conn.connect();
						Array cAns = Connector.connect.createArrayOf("text", pAns); // Name of array
						PreparedStatement p = Connector.connect.prepareStatement(sql);
						p.setString(2, questions.get(i).cAnswers);
						p.setString(1, question);
						p.setArray(3, cAns); // Final grade once calculated
						p.executeUpdate();

						i++;
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

		});

		startPage.showStartPage();
	}// end start

	public void readDocument(File file) {

		// string to hold value of line read
		String line;
		String prevLine;
		String currLine = "";

		int count = 0;

		// catch file not found exceptions
		try {
			// create file readers
			FileReader fileReader = new FileReader(file);
			BufferedReader txtReader = new BufferedReader(fileReader);

			// loop through every line of the file
			while ((line = txtReader.readLine()) != null) {
				prevLine = currLine;
				currLine = line;

				// Line before the question has to be blank to start a new question
				if (prevLine.length() < 1) {
					// Increment count for question number
					count++;
					// add new question to question arraylist
					questions.add(new Question(count + " )  " + currLine));

				}
				// If the previous line is not empty and current line is not empty, it will be
				// added to answers
				else if (currLine.length() > 1 && !line.contains("*")) {
					// add the answer to the last question in question arraylist
					questions.get(questions.size() - 1).answers.add(currLine);
				} else if (currLine.length() > 1 && line.contains("*")) {
					questions.get(questions.size() - 1).cAnswers = currLine;
				}
			}
			txtReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
