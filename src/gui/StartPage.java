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

	public StartPage(Stage tStage) {
		pane = new Pane();
		outerPane = new Pane();
		scene = new Scene(outerPane, 400, 335);
		
		btnQuestion = new Button("   Upload\nQuestions");
		btnEmail = new Button(" Upload\n Emails");
		btnReports = new Button("  View\nReports");
		btnSend = new Button("Send Out\n    Quiz");
		btnHelp = new Button();
		
		lblQFile = new Label();
		lblEFile = new Label();

		primaryStage = tStage;
		
		buildStartPage(primaryStage, scene, pane, outerPane);
	}

	private void buildStartPage(Stage primaryStage, Scene scene, Pane pane, Pane outerPane) {

		outerPane.setStyle(Styles.BACKGROUNDCOLOR);
		pane.setBackground(Styles.BACKGROUNDIMAGE);

		btnQuestion.setLayoutX(scene.getWidth() / 2 - 135);
		btnQuestion.setLayoutY(160);

		btnEmail.setLayoutX(scene.getWidth() / 2 + 15);
		btnEmail.setLayoutY(160);

		btnReports.setLayoutX(scene.getWidth() / 2 - 135);
		btnReports.setLayoutY(240);

		btnSend.setLayoutX(scene.getWidth() / 2 + 15);
		btnSend.setLayoutY(240);

		btnHelp.setLayoutX(0);
		btnHelp.setLayoutY(0);

		lblQFile.setFont(Font.font("Arial", 14));
		lblQFile.setTextFill(Paint.valueOf("#FFFFFF"));

		lblEFile.setFont(Font.font("Arial", 14));
		lblEFile.setTextFill(Paint.valueOf("#FFFFFF"));

		btnQuestion.setPrefWidth(120);
		btnQuestion.setFont(Font.font("Arial", FontWeight.BOLD, 16));
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

		btnHelp.setGraphic(new ImageView(Styles.HELPICON));
		btnHelp.setStyle("-fx-background-color: transparent");
		btnHelp.setTooltip(new Tooltip("Text File Format Help"));
		btnHelp.setOnMouseEntered(e -> btnHelp.setEffect(Styles.SHADOWON));
		btnHelp.setOnMouseExited(e -> btnHelp.setEffect(Styles.SHADOWOFF));
		btnHelp.setOnMousePressed(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 6 4 4 6;"));
		btnHelp.setOnMouseReleased(e -> btnHelp.setStyle("-fx-background-color: transparent; -fx-padding: 5 5 5 5;"));

		// Wrap Question and Email buttons/labels so labels stay centered under buttons
		VBox questionBox = new VBox();
		questionBox.setPrefWidth(btnQuestion.getPrefWidth());
		questionBox.getChildren().addAll(btnQuestion, lblQFile);
		questionBox.setAlignment(Pos.CENTER);
		questionBox.setLayoutX(scene.getWidth() / 2 - 135);
		questionBox.setLayoutY(160);
		questionBox.setSpacing(3);

		VBox emailBox = new VBox();
		emailBox.setPrefWidth(btnEmail.getPrefWidth());
		emailBox.getChildren().addAll(btnEmail, lblEFile);
		emailBox.setAlignment(Pos.CENTER);
		emailBox.setLayoutX(scene.getWidth() / 2 + 15);
		emailBox.setLayoutY(160);
		emailBox.setSpacing(3);

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
