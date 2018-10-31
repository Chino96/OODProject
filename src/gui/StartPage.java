package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class StartPage {

	private Stage primaryStage;

	private Pane pane;
	private Pane outerPane;
	private Scene scene;

	private Button btnQuestion;
	private Button btnEmail;
	private Button btnReports;
	private Button btnSend;
	private Button btnHelp;

	private Label lblQFile;
	private Label lblEFile;
	private Label email1;
	private Label email2;
	private Label question1;
	private Label question2;
	private Label send1;
	private Label send2;
	private Label report1;
	private Label report2;

	private VBox emailText;
	private VBox questionText;
	private VBox reportText;
	private VBox sendText;
	private VBox questionBox;
	private VBox emailBox;

	public StartPage(Stage tStage) {
		pane = new Pane();
		outerPane = new Pane();
		scene = new Scene(outerPane, 400, 335);
		
		btnQuestion = new Button();
		btnEmail = new Button();
		btnReports = new Button();
		btnSend = new Button();
		btnHelp = new Button();
		
		lblQFile = new Label();
		lblEFile = new Label();

		primaryStage = tStage;
		
		buildStartPage(primaryStage, scene, pane, outerPane);
	}

	private void buildStartPage(Stage primaryStage, Scene scene, Pane pane, Pane outerPane) {

		outerPane.setStyle(Styles.BACKGROUNDCOLOR);
		pane.setBackground(Styles.BACKGROUNDIMAGE);


		//Labels for buttons to be put inside a VBox
		question1 = new Label("Upload");
		question2 = new Label("Questions");

		email1 = new Label("Upload");
		email2 = new Label("Emails");

		send1 = new Label("Send Out");
		send2 = new Label("Quiz");

		report1 = new Label("View");
		report2 = new Label("Reports");


		//VBoxes for aligning labels correctly within buttons
		questionText = new VBox();
		questionText.setAlignment(Pos.CENTER);
		questionText.setStyle("-fx-font: 16px Arial; -fx-font-weight: bold");
		questionText.getChildren().addAll(question1, question2);

		emailText = new VBox();
		emailText.setAlignment(Pos.CENTER);
		emailText.setStyle("-fx-font: 16px Arial; -fx-font-weight: bold");
		emailText.getChildren().addAll(email1, email2);

		sendText = new VBox();
		sendText.setAlignment(Pos.CENTER);
		sendText.setStyle("-fx-font: 16px Arial; -fx-font-weight: bold");
		sendText.getChildren().addAll(send1, send2);

		reportText = new VBox();
		reportText.setAlignment(Pos.CENTER);
		reportText.setStyle("-fx-font: 16px Arial; -fx-font-weight: bold");
		reportText.getChildren().addAll(report1, report2);

		btnQuestion.setLayoutX(scene.getWidth() / 2 - 135);
		btnQuestion.setLayoutY(160);
		btnQuestion.setGraphic(questionText);

		btnEmail.setLayoutX(scene.getWidth() / 2 + 15);
		btnEmail.setLayoutY(160);
		btnEmail.setGraphic(emailText);

		btnSend.setLayoutX(scene.getWidth() / 2 + 15);
		btnSend.setLayoutY(240);
		btnSend.setGraphic(sendText);

		btnReports.setLayoutX(scene.getWidth() / 2 - 135);
		btnReports.setLayoutY(240);
		btnReports.setGraphic(reportText);

		btnHelp.setLayoutX(0);
		btnHelp.setLayoutY(0);

		lblQFile.setFont(Font.font("Arial", 14));
		lblQFile.setTextFill(Paint.valueOf("#FFFFFF"));

		lblEFile.setFont(Font.font("Arial", 14));
		lblEFile.setTextFill(Paint.valueOf("#FFFFFF"));

		btnQuestion.setPrefWidth(120);
		btnQuestion.setStyle(Styles.BUTTONSTYLE);
		btnQuestion.setOnMouseEntered(e -> btnQuestion.setStyle(Styles.BUTTONHOVER));
		btnQuestion.setOnMouseExited(e -> btnQuestion.setStyle(Styles.BUTTONSTYLE));

		btnEmail.setPrefWidth(120);
		btnEmail.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnEmail.setStyle(Styles.BUTTONSTYLE);
		btnEmail.setOnMouseEntered(e -> btnEmail.setStyle(Styles.BUTTONHOVER));
		btnEmail.setOnMouseExited(e -> btnEmail.setStyle(Styles.BUTTONSTYLE));

		btnReports.setPrefWidth(120);
		btnReports.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnReports.setStyle(Styles.BUTTONSTYLE);
		btnReports.setOnMouseEntered(e -> btnReports.setStyle(Styles.BUTTONHOVER));
		btnReports.setOnMouseExited(e -> btnReports.setStyle(Styles.BUTTONSTYLE));

		btnSend.setPrefWidth(120);
		btnSend.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnSend.setStyle(Styles.BUTTONSTYLE);
		btnSend.setOnMouseEntered(e -> btnSend.setStyle(Styles.BUTTONHOVER));
		btnSend.setOnMouseExited(e -> btnSend.setStyle(Styles.BUTTONSTYLE));


		Styles.SHADOWON.setSpread(0.7);
		btnHelp.setGraphic(new ImageView(Styles.HELPICON));
		btnHelp.setStyle("-fx-background-color: transparent");
		btnHelp.setTooltip(new Tooltip("Text File Format Help"));
		btnHelp.setOnMouseEntered(e -> btnHelp.setEffect(Styles.SHADOWON));
		btnHelp.setOnMouseExited(e -> btnHelp.setEffect(Styles.SHADOWOFF));
		btnHelp.setOnMousePressed(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 6 4 4 6;"));
		btnHelp.setOnMouseReleased(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 5 5 5 5;"));

		// Wrap Question and Email buttons/labels so labels stay centered under buttons
		questionBox = new VBox();
		questionBox.setPrefWidth(btnQuestion.getPrefWidth());
		questionBox.getChildren().addAll(btnQuestion, lblQFile);
		questionBox.setAlignment(Pos.CENTER);
		questionBox.setLayoutX(scene.getWidth() / 2 - 135);
		questionBox.setLayoutY(160);
		questionBox.setSpacing(3);

		emailBox = new VBox();
		emailBox.setPrefWidth(btnEmail.getPrefWidth());
		emailBox.getChildren().addAll(btnEmail, lblEFile);
		emailBox.setAlignment(Pos.CENTER);
		emailBox.setLayoutX(scene.getWidth() / 2 + 15);
		emailBox.setLayoutY(160);
		emailBox.setSpacing(1.5);

		pane.getChildren().addAll(btnReports, btnSend, btnHelp, questionBox, emailBox);
		outerPane.getChildren().add(pane);

		primaryStage.getIcons().add(Styles.GSIcon);
		primaryStage.setScene(scene);
	}

	public void showStartPage() {
		primaryStage.show();
	}

	public Button getQuestion() {
		return btnQuestion;
	}

	public Button getEmail() {
		return btnEmail;
	}

	public Button getReports() {
		return btnReports;
	}

	public Button getSend() {
		return btnSend;
	}

	public Button getBtnHelp() {
		return btnHelp;
	}

	public Label getLblQFile() {
		return lblQFile;
	}

	public Label getLblEFile() {
		return lblEFile;
	}

}
