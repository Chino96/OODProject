package gui;

import comm.DataBase;
import comm.EmailComm;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessorGUI extends Application {

    private DataBase dataBase = new DataBase();

    private String emailList;
    private String fileName;
    private File emailFile;
    private File questionList;


    ArrayList<Question> questions;
    ArrayList<String> answers;

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
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        questionList = new File("src/questions.txt");

        questions = new ArrayList<>();
        answers = new ArrayList<>();
        // Open help window

        startPage.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                HelpPage hPage = new HelpPage();
                hPage.show();
            }
        });// end btnHelp.setOnAction

        //Set options for "Upload Questions" button
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


        //Set options for "Upload Emails" button
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

                    //Add quiz name to a string so it can be sent to the database
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

        //Set options for "View Reports" button
        startPage.getReports().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Stage reportStage = new Stage();
                String rDirectory = System.getProperty("user.home") + "/Documents";
                fileChooser.setTitle("Open Reports File");
                fileChooser.setInitialDirectory(new File(rDirectory));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));

                //Remove the .txt from file name
                fileName = startPage.getLblQFile().getText();
                fileName = fileName.substring(0, fileName.length() - 4);

                //call create report
                gradeReport.createGradeReportTxt(fileName);
                //responseReport.createResponseReportTxt(fileName);
                try {
                    //call append report
                    gradeReport.appendGradeReportTxt(fileName);
                    //	responseReport.appendResponseReportTxt(fileName);
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

        //Open Send page
        startPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {

                sendPage.showSendPage();

            }
        });// end send.setOnAction

        //Options for what "Send Button" does in Send Page
        sendPage.getSend().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(questionList);
                //Add saved strings to for quiz options to be sent to the database
                String quizCode;
                String randQuest;
                String randAns;
                String inClass;
                String feedbackOption;
                String sMonth, sDay, sHour, sMin;
                String eMonth, eDay, eHour, eMin;
                boolean fileErrors = false;
                boolean timeErrors = false;
                boolean qCodeError = false;

                vPage.showVerificationPage();

                /*
                if (emailFile == null || questionList == null) {
                    fileErrors = true;
                } else {
                    try {
                        if (sendPage.getStartMonthIndex() == -1 || sendPage.getStartDayIndex() == -1 || sendPage.getStartHourIndex() == -1 || sendPage.getStartMinIndex() == -1) throw new NullPointerException();
                        if (sendPage.getStartMonthIndex() > sendPage.getEndMonthIndex()) {
                            if (sendPage.getStartMonthIndex() == 11 && (sendPage.getEndMonthIndex() == 0 || sendPage.getEndMonthIndex() == 1))  timeErrors = false;
                            else timeErrors = true;

                        } else if (sendPage.getStartDayIndex() > sendPage.getEndDayIndex()) {
                            if (sendPage.getEndMonthIndex() > sendPage.getStartMonthIndex()) timeErrors = false;
                            else timeErrors = true;

                        } else if (sendPage.getStartHourIndex() > sendPage.getEndHourIndex()) {
                            if (sendPage.getStartDayIndex() < sendPage.getEndDayIndex())  timeErrors = false;
                            else timeErrors = true;

                        } else if (sendPage.getStartMinIndex() > sendPage.getEndMinIndex()) {
                            if (sendPage.getStartHourIndex() < sendPage.getEndHourIndex()) {
                            	if (sendPage.getStartDayIndex() < sendPage.getEndDayIndex()) timeErrors = false;
                            } else timeErrors = true;

                        } else timeErrors = false;

                    } catch (NullPointerException e) {
                        timeErrors = true;
                    }
                }

                if(sendPage.getQuizCode().length() != 4){ qCodeError = true;}
                if(fileErrors){ sendPage.setErrorLabel();}
                else if(timeErrors){ sendPage.setTimeError();}
                else if(qCodeError){ sendPage.setQCodeError();}
                else{
                    sendPage.resetErrorLabel();
                    //Start time choices
                    sMonth = sendPage.getStartMonth();
                    sDay = sendPage.getStartDay();
                    sHour = sendPage.getStartHour();
                    sMin = sendPage.getStartMin();


                    //End time choices
                    eMonth = sendPage.getEndMonth();
                    eDay = sendPage.getEndDay();
                    eHour = sendPage.getEndHour();
                    eMin = sendPage.getEndMin();

                    //Random Questions
                    if(sendPage.getCb1().isSelected()){
                        randQuest = sendPage.getCb1().getText();
                    }

                    //Random Answers
                    if(sendPage.getCb2().isSelected()){
                        randAns = sendPage.getCb2().getText();
                    }

                    //In class quiz
                    if(sendPage.getCb3().isSelected()){
                        inClass = sendPage.getCb3().getText();
                    }

                    //Feedback Option choice
                    feedbackOption = sendPage.getSelectedToggle();

                    //Save Quiz Code saved in Send Page
                    quizCode = sendPage.getQuizCode();

                    */
                //string to hold value of line read
                String line;
                String prevLine;
                String currLine = "";
                int count = 0;

                //catch file not found exceptions
                try {
                    //create file readers
                    FileReader fileReader = new FileReader(questionList);
                    BufferedReader txtReader = new BufferedReader(fileReader);

                    //loop through every line of the file
                    while ((line = txtReader.readLine()) != null) {
                        prevLine = currLine;
                        currLine = line;

                        //Line before the question has to be blank to start a new question
                        if (prevLine.length() < 1) {
                            //add new question to question arraylist
                            questions.add(new Question(currLine));
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
                    /*


                }
                */
        }
    });

    //Options for Verification Page
        vPage.getSend().

    setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle (ActionEvent event){

            fileName = startPage.getLblQFile().getText();
            fileName = fileName.substring(0, fileName.length() - 4);

            EmailComm eCom = new EmailComm();
            eCom.sendEmails(vPage.getEmailField().getText(), vPage.getPassField().getText(), fileName, "This is a test for our Project", emailList);


            evPage.showEmailVerificationPage();


            dataBase.Write("CREATE TABLE public.\"" + fileName + "\"" + "("
                    + "\"studentEmail\" text COLLATE pg_catalog.\"default\","
                    + "responses text[] COLLATE pg_catalog.\"default\"," + "\"finalGrade\" double precision" + ")"
                    + "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;" + " " + "INSERT INTO " + "\"QuizCodes\" VALUES ('" + fileName +
                    "', " + sendPage.getQuizCode() + ");" +
                    " CREATE TABLE public.\"" + fileName + "questions\"" + "("
                    + "\"questions\" text COLLATE pg_catalog.\"default\","
                    + "canswers text COLLATE pg_catalog.\"default\"," + "\"panswers\" text[]" + ")"
                    + "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;");


            pause.setOnFinished(e -> evPage.closeEmailVerificationPage());
            pause.play();


        }


    });

        startPage.showStartPage();
}// end start

    public void setQuestions(File file) {
        questionList = file;
    }


}