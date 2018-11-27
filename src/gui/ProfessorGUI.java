package gui;

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
import java.sql.SQLException;

public class ProfessorGUI extends Application {

	private DataBase dataBase = new DataBase();

	private String emailList = "";
	private String fileName;
	private File questionList;
	private File emailFile;

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
				fileName = fileName.substring(0, fileName.length() - 4).toLowerCase();

				// call create report
				gradeReport.createGradeReportTxt(fileName);
				responseReport.createResponseReportTxt(fileName);
				try {
					// call append report
					gradeReport.appendGradeReportTxt(fileName);
					responseReport.appendResponseReportTxt(fileName);
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
				fileName = fileName.substring(0, fileName.length() - 4).toLowerCase();

				EmailComm eCom = new EmailComm();
				if (sendPage.getCb3().isSelected()) {
					eCom.sendEmails(vPage.getEmailField().getText(), vPage.getPassField().getText(), fileName,
							"Quiz Code will be provided in class.\nhttps://drive.google.com/open?id=1a8Lnf0xyymfGx8sUHWr7vpZ64ljGHCKr",
							emailList);
				} else {
					eCom.sendEmails(vPage.getEmailField().getText(), vPage.getPassField().getText(), fileName,
							"Quiz Code: " + sendPage.getQuizCode().getText()
									+ "\nhttps://drive.google.com/open?id=1a8Lnf0xyymfGx8sUHWr7vpZ64ljGHCKr",
							emailList);
				}

				evPage.showEmailVerificationPage();

				dataBase.Write("CREATE TABLE public.\"" + fileName + "\"" + "("
						+ "\"studentEmail\" text COLLATE pg_catalog.\"default\","
						+ "responses text[] COLLATE pg_catalog.\"default\"," + "\"finalGrade\" double precision" + ")"
						+ "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;" + " " + "INSERT INTO "
						+ "\"QuizCodes\" VALUES ('" + fileName + "', " + sendPage.getQuizCode().getText() + ");"
						+ " CREATE TABLE public.\"" + fileName + "questions\"" + "("
						+ "id serial primary key,"
						+ "\"questions\" text COLLATE pg_catalog.\"default\","
						+ "canswers text COLLATE pg_catalog.\"default\"," + "\"panswers\" text[]" + ")" + "WITH ("
						+ "OIDS = FALSE)" + "TABLESPACE pg_default;"
						+"grant Insert, Select On table\""+fileName+"questions\" to student;"
						);

				pushQuestions(questionList);
			}

		});

		startPage.showStartPage();
	}// end start

	public void pushQuestions(File file) {

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
					count++;
					// add new question to question arraylist
					dataBase.Write("INSERT INTO " + fileName + "questions(questions) VALUES(\'" + currLine + "\')");

				}
				// If the previous line is not empty and current line is not empty, it will be
				// added to answers
				else if (currLine.length() > 1) {
					// add the answer to the last question in question arraylist
					if (currLine.contains("*")) {
						currLine = currLine.substring(1);
						dataBase.Write("UPDATE " + fileName + "questions SET canswers = \'" + currLine + " \' WHERE id="+ count);
						dataBase.Write("UPDATE " + fileName + "questions SET panswers = panswers|| \'{" + currLine
								+ "}\' WHERE id=" + count);

					} else {
						dataBase.Write("UPDATE " + fileName + "questions SET panswers = panswers|| \'{" + currLine+ " }\' WHERE id=" + count);
					}
				}
			}
			txtReader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}