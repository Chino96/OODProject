package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class HelpPage {

	private Stage helpStage;

	private Pane pane;
	private Pane ePane;
	private Pane qPane;

	private Image eFormat;
	private Image qFormat;

	private Button close;

	private Scene scene;

	public HelpPage() {
		helpStage = new Stage();
		helpStage.setTitle("File Formats");

		buildPage(helpStage, scene);
	}

	private void buildPage(Stage stage, Scene scene) {
		pane = new Pane();
		ePane = new Pane();
		qPane = new Pane();
		eFormat = new Image(getClass().getResourceAsStream("images/emails.png"));
		qFormat = new Image(getClass().getResourceAsStream("images/questions.png"));

		ePane.setStyle(Styles.BACKGROUNDCOLOR);
		qPane.setStyle(Styles.BACKGROUNDCOLOR);

		buildEmailPane(ePane);
		buildQuestionPane(qPane);

		Label emailLabel = new Label("Email Text Format");
		emailLabel.setLayoutX(165);
		emailLabel.setLayoutY(3);
		emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		emailLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		ImageView emailView = new ImageView(eFormat);
		emailView.setFitWidth(ePane.getPrefWidth() * 0.95);
		emailView.setFitHeight(ePane.getPrefHeight() - 42);
		emailView.setX((ePane.getPrefWidth() * 0.025));
		emailView.setY(ePane.getLayoutY() + 20);

		Label questLabel = new Label("Question Text Format");
		questLabel.setLayoutX(155);
		questLabel.setLayoutY(3);
		questLabel.setFont(Font.font("Arial", FontWeight.BLACK, 18));
		questLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		ImageView questionView = new ImageView(qFormat);
		questionView.setFitWidth(qPane.getPrefWidth() * 0.95);
		questionView.setFitHeight(qPane.getPrefHeight() - 42);
		questionView.setX(qPane.getLayoutX() + (qPane.getPrefWidth() * 0.025));
		questionView.setY(qPane.getLayoutY() + 30);

		close = new Button("Close");
		close.setPrefWidth(150);
		close.setPrefHeight(30);
		close.setLayoutX(30);
		close.setLayoutY(emailView.getLayoutY()+ePane.getPrefHeight() + 10);
		close.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		close.setStyle(Styles.BUTTONSTYLE);
		close.setOnAction(event1 -> helpStage.hide());
		close.setOnMouseEntered(e -> close.setStyle(Styles.BUTTONHOVER));
		close.setOnMouseExited(e -> close.setStyle(Styles.BUTTONHOVER));

		Label star = new Label("*");
		star.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		star.setTextFill(Paint.valueOf("#FFFFFF"));
		star.setLayoutY(413);
		star.setLayoutX(513);

		Label legend1 = new Label(" = CORRECT ANSWER");
		legend1.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
		legend1.setTextFill(Paint.valueOf("#FFFFFF"));
		legend1.setLayoutY(413);
		legend1.setLayoutX(523);

		ePane.getChildren().addAll(emailLabel, emailView);
		qPane.getChildren().addAll(questLabel);

		pane.setStyle(Styles.BACKGROUNDCOLOR);
		pane.getChildren().addAll(ePane, qPane, close, questionView, legend1, star);

		scene = new Scene(pane, 1000, close.getLayoutY() + close.getPrefHeight() + 20);
		stage.getIcons().add(Styles.GSIcon);
		stage.setScene(scene);
		stage.show();
	}

	public void show() {
		helpStage.show();
	}

	private void buildEmailPane(Pane emailsPane) {
		emailsPane.setPrefWidth(483);
		emailsPane.setPrefHeight(400);
		emailsPane.setLayoutX(15);
		emailsPane.setLayoutY(10);
	}

	private void buildQuestionPane(Pane questionsPane) {
		questionsPane.setPrefWidth(480);
		questionsPane.setPrefHeight(400);
		questionsPane.setLayoutX(502);
		questionsPane.setLayoutY(10);
	}

}
