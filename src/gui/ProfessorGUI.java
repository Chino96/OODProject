package gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import comm.DataBase;
import comm.EmailComm;

public class ProfessorGUI extends Application {

	private DataBase dataBase = new DataBase();

	private String emailList = "";

	private File questionList;
	private File emailFile;

	// Window style
	private String backgroundColor = "-fx-background-color: #041E60";
	private String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #957D3F ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
	private String buttonHoverStyle = "-fx-base: #957D3F; -fx-background-radius: 25px; -fx-border-color: #FFFFFF ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";

	// Adds shadow effect to buttons when hovered over
	private Color colorOn = new Color(0.5843, 0.4902, 0.2471, 1);
	private Color colorOff = new Color(0.5, 0.5, 0.5, 0);
	private DropShadow shadowOn = new DropShadow(20, colorOn);
	private DropShadow shadowOff = new DropShadow(20, colorOff);

	private Image GSIcon = new Image("/gui/images/GSIcon.png");

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		Pane p = new Pane();
		Pane outerPane = new Pane();

		Scene s = new Scene(outerPane, 400, 335);

		// Background Design
		outerPane.setStyle(backgroundColor);

		// GS logo size
		double bGroundWidth = 200;
		double bGroundHeight = 112.5;

		Image backGrd = new Image(getClass().getResourceAsStream("/gui/images/GSLogo.png"), bGroundWidth, bGroundHeight,
				true, true);
		Image help = new Image(getClass().getResourceAsStream("/gui/images/help.png"), 25, 25, false, true);

		shadowOn.setSpread(0.7);

		FileChooser fileChooser = new FileChooser();

		double hPosition = 200 - (bGroundWidth / 2);

		// Set size and location of background image
		BackgroundSize backgroundSize = new BackgroundSize(bGroundWidth, bGroundHeight, false, false, false, false);
		BackgroundPosition bPosition = new BackgroundPosition(Side.LEFT, hPosition, false, Side.TOP, 23.75, false);
		BackgroundImage bGround = new BackgroundImage(backGrd, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				bPosition, backgroundSize);
		Background background = new Background(bGround);

		p.setBackground(background);

		// Create Buttons
		Button question = new Button();
		Button email = new Button();
		Button reports = new Button();
		Button send = new Button();
		Button btnHelp = new Button();

		// Create VBoxes within buttons to center text properly
		VBox questionText = new VBox();
		Label questionLbl1 = new Label("Upload");
		Label questionLbl2 = new Label("Questions");
		questionLbl1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		questionLbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		questionText.getChildren().addAll(questionLbl1, questionLbl2);
		questionText.setAlignment(Pos.CENTER);
		question.setGraphic(questionText);

		VBox emailText = new VBox();
		Label emailLbl1 = new Label("Upload");
		emailLbl1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		Label emailLbl2 = new Label("Emails");
		emailLbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		emailText.getChildren().addAll(emailLbl1, emailLbl2);
		emailText.setAlignment(Pos.CENTER);
		email.setGraphic(emailText);

		VBox reportText = new VBox();
		Label reportLbl1 = new Label("View");
		reportLbl1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		Label reportLbl2 = new Label("Reports");
		reportLbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		reportText.getChildren().addAll(reportLbl1, reportLbl2);
		reportText.setAlignment(Pos.CENTER);
		reports.setGraphic(reportText);

		VBox sendText = new VBox();
		Label sendLbl1 = new Label("Send Out");
		Label sendLbl2 = new Label("Quiz");
		sendLbl1.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		sendLbl2.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		sendText.getChildren().addAll(sendLbl1, sendLbl2);
		sendText.setAlignment(Pos.CENTER);
		send.setGraphic(sendText);

		// Create tooltip for help button
		Tooltip helpTip = new Tooltip();
		helpTip.setText("Text File Format Help");

		// Set Location and size of buttons
		question.setLayoutX(s.getWidth() / 2 - 135);
		email.setLayoutX(s.getWidth() / 2 + 15);
		reports.setLayoutX(s.getWidth() / 2 - 135);
		send.setLayoutX(s.getWidth() / 2 + 15);
		btnHelp.setLayoutX(0);

		question.setLayoutY(160);
		email.setLayoutY(160);
		reports.setLayoutY(240);
		send.setLayoutY(240);
		btnHelp.setLayoutY(0);

		// Labels to show which files are being uploaded
		Label chosenQuestion = new Label();
		chosenQuestion.setFont(Font.font("Arial", 14));
		chosenQuestion.setTextFill(Paint.valueOf("#FFFFFF"));

		Label chosenEmail = new Label();
		chosenEmail.setFont(Font.font("Arial", 14));
		chosenEmail.setTextFill(Paint.valueOf("#FFFFFF"));

		question.setPrefWidth(120);
		question.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		question.setStyle(buttonStyle);
		question.setOnMouseEntered(e -> question.setStyle(buttonHoverStyle));
		question.setOnMouseExited(e -> question.setStyle(buttonStyle));

		email.setPrefWidth(120);
		email.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		email.setStyle(buttonStyle);
		email.setOnMouseEntered(e -> email.setStyle(buttonHoverStyle));
		email.setOnMouseExited(e -> email.setStyle(buttonStyle));

		reports.setPrefWidth(120);
		reports.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		reports.setStyle(buttonStyle);
		reports.setOnMouseEntered(e -> reports.setStyle(buttonHoverStyle));
		reports.setOnMouseExited(e -> reports.setStyle(buttonStyle));

		send.setPrefWidth(120);
		send.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		send.setStyle(buttonStyle);
		send.setOnMouseEntered(e -> send.setStyle(buttonHoverStyle));
		send.setOnMouseExited(e -> send.setStyle(buttonStyle));

		btnHelp.setGraphic(new ImageView(help));
		btnHelp.setStyle("-fx-background-color: transparent");
		btnHelp.setTooltip(helpTip);
		btnHelp.setOnMouseEntered(e -> btnHelp.setEffect(shadowOn));
		btnHelp.setOnMouseExited(e -> btnHelp.setEffect(shadowOff));
		btnHelp.setOnMousePressed(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 6 4 4 6;"));
		btnHelp.setOnMouseReleased(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 5 5 5 5;"));

		// Wrap Question and Email buttons/labels so labels stay centered under buttons
		VBox questionBox = new VBox();
		questionBox.setPrefWidth(question.getPrefWidth());
		questionBox.getChildren().addAll(question, chosenQuestion);
		questionBox.setAlignment(Pos.CENTER);
		questionBox.setLayoutX(s.getWidth() / 2 - 135);
		questionBox.setLayoutY(160);
		questionBox.setSpacing(3);

		VBox emailBox = new VBox();
		emailBox.setPrefWidth(email.getPrefWidth());
		emailBox.getChildren().addAll(email, chosenEmail);
		emailBox.setAlignment(Pos.CENTER);
		emailBox.setLayoutX(s.getWidth() / 2 + 15);
		emailBox.setLayoutY(160);
		emailBox.setSpacing(3);

		// Open help window
		btnHelp.setOnAction(event -> {
			Stage helpStage = new Stage();
			helpStage.setTitle("File Formats");

			Pane rPane = new Pane();
			Pane emailsPane = new Pane();
			Pane questionsPane = new Pane();

			Image emailFmt = new Image(getClass().getResourceAsStream("images/emails.png"));
			Image questionFmt = new Image(getClass().getResourceAsStream("images/questions.png"));

			Button close;

			emailsPane.setStyle(backgroundColor);
			questionsPane.setStyle(backgroundColor);

			Scene rScene = new Scene(rPane, 1000, 460);

			emailsPane.setPrefWidth(483);
			emailsPane.setPrefHeight(400);
			emailsPane.setLayoutX(15);
			emailsPane.setLayoutY(10);

			questionsPane.setPrefWidth(480);
			questionsPane.setPrefHeight(400);
			questionsPane.setLayoutX(502);
			questionsPane.setLayoutY(10);

			Label emailLabel = new Label("Email Text Format");
			emailLabel.setLayoutX(165);
			emailLabel.setLayoutY(3);
			emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			emailLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			ImageView emailView = new ImageView(emailFmt);
			emailView.setFitWidth(emailsPane.getPrefWidth() * 0.95);
			emailView.setFitHeight(emailsPane.getPrefHeight() - 42);
			emailView.setX((emailsPane.getPrefWidth() * 0.025));
			emailView.setY(emailsPane.getLayoutY() + 20);

			Label questLabel = new Label("Question Text Format");
			questLabel.setLayoutX(155);
			questLabel.setLayoutY(3);
			questLabel.setFont(Font.font("Arial", FontWeight.BLACK, 18));
			questLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			ImageView questionView = new ImageView(questionFmt);
			questionView.setFitWidth(questionsPane.getPrefWidth() * 0.95);
			questionView.setFitHeight(questionsPane.getPrefHeight() - 42);
			questionView.setX(questionsPane.getLayoutX() + (questionsPane.getPrefWidth() * 0.025));
			questionView.setY(questionsPane.getLayoutY() + 30);

			double closeWidth = 150;
			close = new Button("Close");
			close.setPrefWidth(closeWidth);
			close.setPrefHeight(30);
			close.setLayoutX(30);
			close.setLayoutY(413);
			close.setFont(Font.font("Arial", FontWeight.BOLD, 16));
			close.setStyle(buttonStyle);
			close.setOnAction(event1 -> helpStage.hide());
			close.setOnMouseEntered(e -> close.setStyle(buttonHoverStyle));
			close.setOnMouseExited(e -> close.setStyle(buttonStyle));

			Label star = new Label("*");
			star.setFont(Font.font("Arial", FontWeight.BOLD, 24));
			star.setTextFill(Paint.valueOf("#FFFFFF"));
			star.setLayoutY(413);
			star.setLayoutX(513);
			Label legend = new Label(" = CORRECT ANSWER");
			legend.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
			legend.setTextFill(Paint.valueOf("#FFFFFF"));
			legend.setLayoutY(413);
			legend.setLayoutX(523);

			emailsPane.getChildren().addAll(emailLabel, emailView);
			questionsPane.getChildren().addAll(questLabel);

			rPane.setStyle(backgroundColor);
			rPane.getChildren().addAll(emailsPane, questionsPane, close, questionView, legend, star);

			helpStage.getIcons().add(GSIcon);
			helpStage.setScene(rScene);
			helpStage.show();

		});// end btnHelp.setOnAction

		question.setOnAction(event -> {
			Stage questionStage = new Stage();
			String qDirectory = System.getProperty("user.home") + "/Documents";
			fileChooser.setTitle("Open Questions File");
			fileChooser.setInitialDirectory(new File(qDirectory));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			File file = fileChooser.showOpenDialog(questionStage);

			if (file != null) {
				chosenQuestion.setText(file.getName());
				questionList = file;

			} else {
				chosenQuestion.setText(null);
			}
		});// end question.setOnAction

		email.setOnAction(event -> {
			Stage emailStage = new Stage();
			String eDirectory = System.getProperty("user.home") + "/Documents";
			fileChooser.setTitle("Open Emails File");
			fileChooser.setInitialDirectory(new File(eDirectory));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			File file = fileChooser.showOpenDialog(emailStage);

			if (file != null) {
				chosenEmail.setText(file.getName());
				emailFile = file;
			}
			String line;
			int questionLength = 0;

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
				chosenEmail.setTextFill(Color.RED);
				chosenEmail.setText("Improper Format");
			}			
		});// end email.setOnAction

		reports.setOnAction(event -> {
			Stage reportStage = new Stage();
			String rDirectory = System.getProperty("user.home") + "/Documents";
			fileChooser.setTitle("Open Reports File");
			fileChooser.setInitialDirectory(new File(rDirectory));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			File file = fileChooser.showOpenDialog(reportStage);

			if (file != null) {
				System.out.println(file.toString());
			}
		});// end reports.setOnAction

		send.setOnAction(event -> {
			Stage sendStage = new Stage();
			Pane sendPane = new Pane();
			sendPane.setStyle(backgroundColor);
			sendStage.setTitle("Send Quiz");

			// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			// Boxes to set Start Time
			ComboBox<String> startMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February",
					"March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));
			startMonth.setLayoutX(20);
			startMonth.setPrefWidth(120);
			startMonth.setLayoutY(82);
			startMonth.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> startDay = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
					10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));
			startDay.setLayoutX(startMonth.getLayoutX() + startMonth.getPrefWidth() + 5);
			startDay.setPrefWidth(80);
			startDay.setLayoutY(82);
			startDay.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> startHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
					10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
			startHour.setLayoutX(startDay.getLayoutX() + startDay.getPrefWidth() + 5);
			startHour.setPrefWidth(80);
			startHour.setLayoutY(82);
			startHour.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> startMin = new ComboBox<>(
					FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
							19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
							42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
			startMin.setLayoutX(startHour.getLayoutX() + startHour.getPrefWidth() + 5);
			startMin.setPrefWidth(80);
			startMin.setLayoutY(82);
			startMin.setStyle(buttonStyle + "-fx-font-size: 14px");

			// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			Label startTime = new Label("Start Time");
			startTime.setFont(Font.font("Arial", FontWeight.BOLD, 28));
			startTime.setTextFill(Paint.valueOf("#FFFFFF"));

			Label monthLabel = new Label("Month:");
			monthLabel.setLayoutX(startMonth.getLayoutX() + 10);
			monthLabel.setLayoutY(60);
			monthLabel.setFont(Font.font("Arial", 14));
			monthLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			Label dayLabel = new Label("Day:");
			dayLabel.setLayoutX(startDay.getLayoutX() + 10);
			dayLabel.setLayoutY(60);
			dayLabel.setFont(Font.font("Arial", 14));
			dayLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			Label hourLabel = new Label("Hour:");
			hourLabel.setLayoutX(startDay.getLayoutX() + startDay.getPrefWidth() + 15);
			hourLabel.setLayoutY(60);
			hourLabel.setFont(Font.font("Arial", 14));
			hourLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			VBox startTimeBox = new VBox();
			startTimeBox.setPrefWidth(startMin.getLayoutX() + startMin.getPrefWidth() - startMonth.getLayoutX());
			startTimeBox.setLayoutX(startMonth.getLayoutX());
			startTimeBox.setLayoutY(20);
			startTimeBox.getChildren().addAll(startTime);
			startTimeBox.setAlignment(Pos.CENTER);

			// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			// Boxes to set End Time
			ComboBox<String> endMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February", "March",
					"April", "May", "June", "July", "August", "September", "October", "November", "December"));
			endMonth.setLayoutX(20);
			endMonth.setLayoutY(200);
			endMonth.setPrefWidth(120);
			endMonth.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> endDay = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
					10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));
			endDay.setLayoutX(endMonth.getLayoutX() + endMonth.getPrefWidth() + 5);
			endDay.setLayoutY(endMonth.getLayoutY());
			endDay.setPrefWidth(80);
			endDay.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> endHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9,
					10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));
			endHour.setLayoutX(endDay.getLayoutX() + endDay.getPrefWidth() + 5);
			endHour.setLayoutY(endMonth.getLayoutY());
			endHour.setPrefWidth(80);
			endHour.setStyle(buttonStyle + "-fx-font-size: 14px");

			ComboBox<Integer> endMin = new ComboBox<>(
					FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18,
							19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41,
							42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));
			endMin.setLayoutX(endHour.getLayoutX() + endHour.getPrefWidth() + 5);
			endMin.setPrefWidth(80);
			endMin.setLayoutY(endMonth.getLayoutY());
			endMin.setStyle(buttonStyle + "-fx-font-size: 14px");

			Label endTime = new Label("End Time");
			endTime.setFont(Font.font("Arial", FontWeight.BOLD, 28));
			endTime.setTextFill(Paint.valueOf("#FFFFFF"));

			Label endMonthLabel = new Label("Month:");
			endMonthLabel.setLayoutX(endMonth.getLayoutX() + 10);
			endMonthLabel.setLayoutY(endMonth.getLayoutY() - 22);
			endMonthLabel.setFont(Font.font("Arial", 14));
			endMonthLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			Label endDayLabel = new Label("Day:");
			endDayLabel.setLayoutX(endDay.getLayoutX() + 10);
			endDayLabel.setLayoutY(endDay.getLayoutY() - 22);
			endDayLabel.setFont(Font.font("Arial", 14));
			endDayLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			Label endHourLabel = new Label("Hour:");
			endHourLabel.setLayoutX(endHour.getLayoutX() + 10);
			endHourLabel.setLayoutY(endHour.getLayoutY() - 22);
			endHourLabel.setFont(Font.font("Arial", 14));
			endHourLabel.setTextFill(Paint.valueOf("#FFFFFF"));

			VBox endTimeBox = new VBox();
			endTimeBox.setPrefWidth(endMin.getLayoutX() + endMin.getPrefWidth() - endMonth.getLayoutX());
			endTimeBox.setLayoutX(endMonth.getLayoutX());
			endTimeBox.setLayoutY(endMonth.getLayoutY() - 62);
			endTimeBox.getChildren().addAll(endTime);
			endTimeBox.setAlignment(Pos.CENTER);

			// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			Scene sendScene = new Scene(sendPane, 800, 340);

			Label qcode = new Label("Set Quiz Code");
			qcode.setFont(Font.font("Arial", FontWeight.BOLD, 28));
			qcode.setTextFill(Paint.valueOf("#FFFFFF"));

			VBox qBox = new VBox();
			qBox.setPrefWidth((startMin.getPrefWidth() + startMin.getLayoutX()) - startMonth.getLayoutX());
			qBox.setLayoutX(startMin.getPrefWidth() + startMin.getLayoutX());
			qBox.setLayoutY(20);
			qBox.setSpacing(5);
			qBox.getChildren().addAll(qcode);
			qBox.setAlignment(Pos.CENTER);

			TextField quizCode = new TextField();
			quizCode.setStyle(buttonStyle + "-fx-font-size: 34px; -fx-font-weight: bold;");
			quizCode.setPrefWidth(130);
			quizCode.setMaxHeight(50);
			quizCode.setAlignment(Pos.CENTER);
			quizCode.setLayoutX((qBox.getPrefWidth() - quizCode.getPrefWidth()) / 2 + qBox.getLayoutX());
			quizCode.setLayoutY(qBox.getLayoutY() + 45);
			quizCode.setTextFormatter(new TextFormatter<String>((Change change) -> {
				String newText = change.getControlNewText();
				if (newText.length() > 4) {
					return null;
				} else {
					return change;
				}
			}));

			// --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			// FeedBack Options
			Label feedback = new Label("Set Feedback Option");
			feedback.setFont(Font.font("Arial", FontWeight.BOLD, 28));
			feedback.setTextFill(Paint.valueOf("#FFFFFF"));

			VBox feedbackBox = new VBox();
			feedbackBox.setPrefWidth(279);
			feedbackBox.setLayoutX(440);
			feedbackBox.setLayoutY(160);
			feedbackBox.setSpacing(5);
			feedbackBox.getChildren().addAll(feedback);
			feedbackBox.setAlignment(Pos.CENTER);

			final ToggleGroup group = new ToggleGroup();
			RadioButton rb1 = new RadioButton("Provide feedback with all questions.");
			rb1.setToggleGroup(group);
			rb1.setFont(Font.font("Arial", 14));
			rb1.setSelected(true);

			RadioButton rb2 = new RadioButton("Provide feedback with only incorrect answers.");
			rb2.setToggleGroup(group);
			rb2.setFont(Font.font("Arial", 14));

			RadioButton rb3 = new RadioButton("Provide feeedback with only correct answers.");
			rb3.setToggleGroup(group);
			rb3.setFont(Font.font("Arial", 14));

			VBox rBox = new VBox();
			rBox.setPrefWidth(310);
			rBox.setLayoutX(feedbackBox.getLayoutX());
			rBox.setLayoutY(feedbackBox.getLayoutY() + 40);
			rBox.setAlignment(Pos.TOP_LEFT);
			rBox.setSpacing(10);
			rBox.getChildren().addAll(rb1, rb2, rb3);

			CheckBox cb1 = new CheckBox("Randomize Questions");
			CheckBox cb2 = new CheckBox("Randomize Answer Choices");

			cb1.setLayoutX(33);
			cb1.setLayoutY(260);
			cb1.setTextFill(Color.WHITE);
			cb1.setFont(Font.font("Arial", 13));

			cb2.setLayoutX(cb1.getLayoutX() + 160);
			cb2.setLayoutY(cb1.getLayoutY());
			cb2.setTextFill(Color.WHITE);
			cb2.setFont(Font.font("Arial", 13));

			rb1.setLayoutX(feedbackBox.getLayoutX());
			rb2.setLayoutX(feedbackBox.getLayoutX());
			rb3.setLayoutX(feedbackBox.getLayoutX());

			rb1.setLayoutY(feedbackBox.getLayoutY() + 40);
			rb2.setLayoutY(feedbackBox.getLayoutY() + 70);
			rb3.setLayoutY(feedbackBox.getLayoutY() + 100);

			rb1.setTextFill(Paint.valueOf("#FFFFFF"));
			rb2.setTextFill(Paint.valueOf("#FFFFFF"));
			rb3.setTextFill(Paint.valueOf("#FFFFFF"));

			// Button to send out quiz link
			Button sendOut = new Button("Send");
			sendOut.setLayoutX(sendScene.getWidth() - 100);
			sendOut.setLayoutY(sendScene.getHeight() - 55);
			sendOut.setPrefWidth(80);
			sendOut.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			sendOut.setStyle(buttonStyle);
			sendOut.setOnMouseEntered(e -> sendOut.setStyle(buttonHoverStyle));
			sendOut.setOnMouseExited(e -> sendOut.setStyle(buttonStyle));

			sendOut.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					if (emailFile != null && questionList != null) {
						EmailComm eCom = new EmailComm();
						eCom.sendEmails("chino.s.ugwumadu@gmail.com", "busybee1", "This is a test", "We in this bitch", emailList);
						/*
						dataBase.Write("CREATE TABLE public.\"" + questionList.getName() + "\"" + "("
								+ "\"studentEmail\" text COLLATE pg_catalog.\"default\","
								+ "responses text[] COLLATE pg_catalog.\"default\"," + "\"finalGrade\" double precision"
								+ ")" + "WITH (" + "OIDS = FALSE)" + "TABLESPACE pg_default;");*/

					} else {
						Label err = new Label("Missing Required Field");
						err.setLayoutY(295);
						err.setLayoutX(100);
						err.setStyle("-fx-font-family: serif; -fx-font-size: 24px; -fx-text-fill: red;");
						sendPane.getChildren().add(err);
					}
				}

			});

			// Button gives option to return to last stage
			Button cancel = new Button("Cancel");
			cancel.setLayoutX(sendOut.getLayoutX() - 110);
			cancel.setLayoutY(sendScene.getHeight() - 55);
			cancel.setPrefWidth(95);
			cancel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
			cancel.setStyle(buttonStyle);
			cancel.setOnMouseEntered(e -> cancel.setStyle(buttonHoverStyle));
			cancel.setOnMouseExited(e -> cancel.setStyle(buttonStyle));
			cancel.setOnAction(event12 -> {
				sendStage.hide();
				primaryStage.show();
			});

			sendPane.getChildren().addAll(cb1, cb2, sendOut, startMonth, startDay, startHour, startMin, feedbackBox,
					dayLabel, hourLabel, monthLabel, endDayLabel, endHourLabel, endMonthLabel, qBox, quizCode, rBox,
					startTimeBox, endTimeBox, cancel, endMonth, endDay, endHour, endMin);
			sendStage.getIcons().add(GSIcon);
			sendStage.setScene(sendScene);
			sendStage.show();
			primaryStage.hide();
		});// end send.setOnAction

		p.getChildren().addAll(reports, send, btnHelp, questionBox, emailBox);
		outerPane.getChildren().add(p);

		primaryStage.getIcons().add(GSIcon);
		primaryStage.setScene(s);
		primaryStage.show();

	}// end start

	
}