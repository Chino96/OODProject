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
        // Open help window

        startPage.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                HelpPage hPage = new HelpPage();
                hPage.show();
            }
        });// end btnHelp.setOnAction

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

        startPage.getReports().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Stage reportStage = new Stage();
                String rDirectory = System.getProperty("user.home") + "/Documents";
                fileChooser.setTitle("Open Reports File");
                fileChooser.setInitialDirectory(new File(rDirectory));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
                               
                //Remove the .txt from file name
                String fileName = startPage.getLblEFile().getText();
                fileName = fileName.substring(0, fileName.length()-4);
                
                //call create report
                gradeReport.createGradeReportTxt(fileName);
                try {
                	//call append report
					gradeReport.appendGradeReportTxt(fileName);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
                File file = fileChooser.showOpenDialog(reportStage);
                Desktop openFile = Desktop.getDesktop();
                if(file.exists())
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

        startPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                sendPage.showSendPage();
            }
        });// end send.setOnAction

        sendPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (emailFile == null || questionList == null) {
                    sendPage.setErrorLabel();
                } else {
                    try {
                        if (sendPage.getStartMonth() == -1 || sendPage.getStartDay() == -1 || sendPage.getStartHour() == -1 || sendPage.getStartMin() == -1)
                            throw new NullPointerException();
                        if (sendPage.getStartMonth() > sendPage.getEndMonth()) {
                            if (sendPage.getStartMonth() == 11 && (sendPage.getEndMonth() == 0 || sendPage.getEndMonth() == 1)) {
                                vPage.showVerificationPage();
                                sendPage.resetErrorLabel();
                            } else sendPage.setTimeError();

                        } else if (sendPage.getStartDay() > sendPage.getEndDay()) {
                            if (sendPage.getEndMonth() > sendPage.getStartMonth()) {
                                vPage.showVerificationPage();
                                sendPage.resetErrorLabel();
                            } else sendPage.setTimeError();

                        } else if (sendPage.getStartHour() > sendPage.getEndHour()) {
                            if (sendPage.getStartDay() < sendPage.getEndDay()) {
                                vPage.showVerificationPage();
                                sendPage.resetErrorLabel();
                            } else sendPage.setTimeError();

                        } else if (sendPage.getStartMin() > sendPage.getEndMin()) {
                            if (sendPage.getStartHour() < sendPage.getEndHour()) {
                                vPage.showVerificationPage();
                                sendPage.resetErrorLabel();
                            } else sendPage.setTimeError();

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

        vPage.getSend().setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                EmailComm eCom = new EmailComm();
                eCom.sendEmails(vPage.getEmailField().getText(), vPage.getPassField().getText(), "This is a test",
                        "This is a test for our Project", emailList);

                dataBase.Write("CREATE TABLE public.\"" + questionList.getName() + "\"" + "("
                        + "\"studentEmail\" text COLLATE pg_catalog.\"default\","
                        + "responses text[] COLLATE pg_catalog.\"default\"," + "\"finalGrade\" double precision" + ")"
                        + "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;");
            }

        });

        startPage.showStartPage();
    }// end start

}
