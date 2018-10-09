package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import comm.DataBase;

public class ProfessorGUI extends Application {

	DataBase dataBase = new DataBase();
	
	private File questionList;
	private File emailList;
	
	private Desktop desktop = Desktop.getDesktop();

    private String backgroundColor = "-fx-background-color: #98B4C2";
    private String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #14336C; -fx-border-width: 3px; -fx-border-radius: 20px; -fx-background-insets: 0";

    private double bGroundWidth = 200;
    private double bGroundHeight = 112.5;
    

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane p = new Pane();
        Pane outerPane = new Pane();

        //Background Design
        outerPane.setStyle(backgroundColor);

        Image backGrd = new Image("http://static-11.sinclairstoryline.com/resources/media/a1513171-a451-48a6-ad84-2ea32c3c1a53-large16x9_1280x720_51223E00TCAUL.png", bGroundWidth, bGroundHeight, true, true);
        Image help = new Image(getClass().getResourceAsStream("images/help.png"), 25, 25, false, true);



        FileChooser fileChooser = new FileChooser();



        double hPosition = (400/2)-(bGroundWidth/2);

        //Set size and location of background image
        BackgroundSize backgroundSize = new BackgroundSize(bGroundWidth, bGroundHeight, false, false, false, false);
        BackgroundPosition bPosition = new BackgroundPosition(Side.LEFT, hPosition, false, Side.TOP, 23.75, false);
        BackgroundImage bGround = new BackgroundImage(backGrd, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,bPosition,backgroundSize);
        Background background = new Background(bGround);

        p.setBackground(background);

        //Create Buttons
        Button question = new Button("  Upload\n" +
                "Questions");
        Button email = new Button("Upload\n Emails");
        Button reports = new Button("  View\nReports");
        Button send = new Button("Send Out\n Quizzes");
        Button btnHelp = new Button();


        //Create tooltip for help button
        Tooltip helpTip = new Tooltip();
        helpTip.setText("Text File Format Help");

        //Set Location and size of buttons
        question.setLayoutX(70);
        email.setLayoutX(230);
        reports.setLayoutX(70);
        send.setLayoutX(230);
        btnHelp.setLayoutX(0);

        question.setLayoutY(160);
        email.setLayoutY(160);
        reports.setLayoutY(240);
        send.setLayoutY(240);
        btnHelp.setLayoutY(0);

        question.setPrefWidth(100);
        question.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        question.setStyle(buttonStyle);

        email.setPrefWidth(100);
        email.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        email.setStyle(buttonStyle);


        reports.setPrefWidth(100);
        reports.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        reports.setStyle(buttonStyle);


        send.setPrefWidth(100);
        send.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        send.setStyle(buttonStyle);


        btnHelp.setGraphic(new ImageView(help));
        btnHelp.setStyle(backgroundColor);
        btnHelp.setTooltip(helpTip);







        btnHelp.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage helpStage = new Stage();
                helpStage.setTitle("File Formats");

                Pane rPane = new Pane();
                Pane emailsPane = new Pane();
                Pane questionsPane = new Pane();

                Image emailImg = new Image(getClass().getResourceAsStream("images/emails.png"));

                Button close;

                BackgroundSize backgroundSize = new BackgroundSize(100, 100, false, false, true, false);
                BackgroundPosition bPosition = new BackgroundPosition(Side.LEFT, 0, false, Side.TOP, 30, false);
                BackgroundImage bGround = new BackgroundImage(emailImg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,bPosition,backgroundSize);
                Background background = new Background(bGround);

                emailsPane.setBackground(background);
                emailsPane.setStyle("-fx-border-color: #14336C; -fx-border-width: 3px;");


                questionsPane.setStyle("-fx-border-color: #14336C; -fx-border-width: 3px;");
                Scene rScene = new Scene(rPane,300,450);


                emailsPane.setPrefWidth(280);
                emailsPane.setPrefHeight(190);
                emailsPane.setLayoutX(10);
                emailsPane.setLayoutY(10);

                questionsPane.setPrefWidth(280);
                questionsPane.setPrefHeight(190);
                questionsPane.setLayoutX(10);
                questionsPane.setLayoutY(210);

                Label emailLabel = new Label("Email Text Format");
                emailLabel.setLayoutX(80);
                emailLabel.setLayoutY(9);
                emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Label questLabel = new Label("Question Text Format");
                questLabel.setLayoutX(75);
                questLabel.setLayoutY(9);
                questLabel.setFont(Font.font("Arial", FontWeight.BLACK,14));

                close = new Button("Close");
                close.setLayoutX(110);
                close.setLayoutY(405);
                close.setPrefWidth(80);
                close.setPrefHeight(30);
                close.setFont(Font.font(14) );
                close.setStyle(buttonStyle);

                close.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        helpStage.hide();
                    }
                });






                emailsPane.getChildren().addAll(emailLabel);
                questionsPane.getChildren().addAll(questLabel);

                rPane.setStyle(backgroundColor);
                rPane.getChildren().addAll(emailsPane,questionsPane, close);
                helpStage.setScene(rScene);
                helpStage.show();
            }
        });

        question.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage questionStage = new Stage();
                File file = fileChooser.showOpenDialog(questionStage);

                if(file != null){
                   questionList = file;
                }
            }
        });

        email.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                Stage emailStage = new Stage();
                File file = fileChooser.showOpenDialog(emailStage);

                if(file != null){
                    emailList = file;
                }
            }
        });

        reports.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Stage reportStage = new Stage();
                reportStage.setTitle("Reports");

                Button close;
                Pane rPane = new Pane();
                rPane.setStyle(backgroundColor);

                Scene rScene = new Scene(rPane,290,125);
                Button responses = new Button("   Student\nResponses");
                Button grades = new Button("Student\n Grades");

                responses.setLayoutX(30);
                responses.setLayoutY(20);
                responses.setPrefWidth(105);
                responses.setPrefHeight(50);
                responses.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                responses.setStyle(buttonStyle);

                grades.setLayoutX(160);
                grades.setLayoutY(20);
                grades.setPrefWidth(105);
                grades.setPrefHeight(50);
                grades.setFont(Font.font("Arial", FontWeight.BOLD, 14));
                grades.setStyle(buttonStyle);

                close = new Button("Close");
                close.setLayoutX(115);
                close.setLayoutY(80);
                close.setPrefWidth(60);
                close.setPrefHeight(20);
                close.setFont(Font.font("Arial", 12) );
                close.setStyle(buttonStyle);

                close.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        reportStage.hide();
                    }
                });


                rPane.getChildren().addAll(responses, grades, close);
                reportStage.setScene(rScene);
                reportStage.show();

            }

        });



        send.setOnAction(new EventHandler<ActionEvent>(){

            @Override
            public void handle(ActionEvent event) {
                Stage sendStage = new Stage();
                Pane sendPane = new Pane();
                Scene sendScene = new Scene(sendPane,500,400);
                sendPane.setStyle(backgroundColor);
                sendStage.setTitle("Send Quiz");

                //Button to send out quiz link
                Button sendOut = new Button("Send");
                sendOut.setLayoutX(400);
                sendOut.setLayoutY(350);
                sendOut.setPrefWidth(80);
                sendOut.setPrefHeight(20);
                sendOut.setFont(Font.font(14) );
                sendOut.setStyle(buttonStyle);

                sendOut.setOnAction(new EventHandler<ActionEvent>() {
 
					@Override
					public void handle(ActionEvent arg0) {
						dataBase.Write("CREATE TABLE public.\"TestQuiz\""
										+"("
										+ "\"studentEmail\" text COLLATE pg_catalog.\"default\","
										+ "responses text[] COLLATE pg_catalog.\"default\","
										+ "\"finalGrade\" double precision"
										+ ")"
										+ "WITH ("
										+ "OIDS = FALSE)"
										+ "TABLESPACE pg_default;"
										+ "ALTER TABLE public.\"TestQuiz\""
										+ "OWNER to harris;"
										+ "");
					}
                	
                });
                
                Label timeLimit = new Label("Set Time Limit:");
                timeLimit.setLayoutX(120);
                timeLimit.setLayoutY(65);
                timeLimit.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Label qcode = new Label("Set Quiz Code:");
                qcode.setLayoutX(120);
                qcode.setLayoutY(160);
                qcode.setFont(Font.font("Arial", FontWeight.BOLD, 14));


                Label feedback = new Label("Set Feedback Option:");
                feedback.setLayoutX(120);
                feedback.setLayoutY(230);
                feedback.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                Label dayLabel = new Label("Days:");
                dayLabel.setLayoutX(120);
                dayLabel.setLayoutY(90);

                Label hourLabel = new Label("Hours:");
                hourLabel.setLayoutX(190);
                hourLabel.setLayoutY(90);

                Label minLabel = new Label("Minutes:");
                minLabel.setLayoutX(260);
                minLabel.setLayoutY(90);

                //Boxes to set Time Limit
                ComboBox<Integer> days = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20));
                days.setLayoutX(120);
                days.setLayoutY(110);
                days.setStyle(buttonStyle);

                ComboBox<Integer> hours = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24));
                hours.setLayoutX(190);
                hours.setLayoutY(110);
                hours.setStyle(buttonStyle);

                ComboBox<Integer> mins = new ComboBox<Integer>(FXCollections.observableArrayList(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59));
                mins.setLayoutX(260);
                mins.setLayoutY(110);
                mins.setStyle(buttonStyle);


                TextField quizCode = new TextField();
                quizCode.setLayoutX(120);
                quizCode.setLayoutY(185);
                quizCode.setStyle(buttonStyle);
                quizCode.setTextFormatter(new TextFormatter<String>((Change change) -> {
                    String newText = change.getControlNewText();
                    if (newText.length() > 4) {
                        return null ;
                    } else {
                        return change ;
                    }
                }));
                //FeedBack Options
                final ToggleGroup group = new ToggleGroup();

                RadioButton rb1 = new RadioButton("Provide feedback with all questions.");
                rb1.setToggleGroup(group);
                rb1.setSelected(true);

                RadioButton rb2 = new RadioButton("Provide feedback with only incorrect answers.");
                rb2.setToggleGroup(group);
                RadioButton rb3 = new RadioButton("Provide feeedback with only correct answers.");
                rb3.setToggleGroup(group);

                rb1.setLayoutX(120);
                rb2.setLayoutX(120);
                rb3.setLayoutX(120);

                rb1.setLayoutY(255);
                rb2.setLayoutY(280);
                rb3.setLayoutY(305);

                //Button gives option to return to last stage
                Button cancel = new Button("Cancel");
                cancel.setLayoutX(300);
                cancel.setLayoutY(350);
                cancel.setPrefWidth(80);
                cancel.setPrefHeight(20);
                cancel.setFont(Font.font(14));
                cancel.setStyle(buttonStyle);

                cancel.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        sendStage.hide();
                        primaryStage.show();
                    }
                });


                sendPane.getChildren().addAll(sendOut,days,hours,mins,timeLimit,dayLabel,hourLabel,minLabel,quizCode,qcode,rb1,rb2,rb3,feedback, cancel);

                sendStage.setScene(sendScene);
                sendStage.show();
                primaryStage.hide();
            }

        });


        p.getChildren().addAll(question,email,reports,send,btnHelp);
        outerPane.getChildren().add(p);

        Scene s = new Scene(outerPane,400,335);

        primaryStage.setScene(s);
        primaryStage.show();

    }

    private void openFile(File file){
        try{
            desktop.open(file);
        }
        catch(IOException e){

        }
    }

}