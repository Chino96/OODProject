package gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VerificationPage {
	private Stage stage;

	private Pane pane;
	private Scene scene;
	private TextField emailField;
	private PasswordField passField;
	private Button send;

	public VerificationPage() {
		stage = new Stage();
		pane = new Pane();
		scene = new Scene(pane, 300, 300);
		emailField = new TextField();
		passField = new PasswordField();
		send = new Button("Send");
		buildVerificationPage(stage, pane, scene);
	}
	
	public void buildVerificationPage(Stage stage, Pane pane, Scene scene) {
		
		pane.setStyle(Styles.BACKGROUNDCOLOR);
		stage.setTitle("Verification");
		
		emailField.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 16px; -fx-font-weight: bold;");
		emailField.setPrefWidth(290);
		emailField.setMaxHeight(20);
		emailField.setLayoutX(5);
		emailField.setLayoutY(80);
		emailField.setAlignment(Pos.CENTER);
		emailField.setPromptText("name@email.com");
		
		passField.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 16px; -fx-font-weight: bold;");
		passField.setPrefWidth(290);
		passField.setMaxHeight(20);
		passField.setLayoutX(5);
		passField.setLayoutY(165);
		passField.setAlignment(Pos.CENTER);
		
		Label emailLabel = new Label("Email:");
		emailLabel.setFont(Font.font("Arial", 18));
		emailLabel.setTextFill(Paint.valueOf("#FFFFFF"));
		emailLabel.setLayoutX(15);
		emailLabel.setLayoutY(55);
		
		Label passLabel = new Label("Password:");
		passLabel.setFont(Font.font("Arial", 18));
		passLabel.setTextFill(Paint.valueOf("#FFFFFF"));
		passLabel.setLayoutX(15);
		passLabel.setLayoutY(140);
		
		send.setLayoutX(pane.getWidth() - 100);
		send.setLayoutY(pane.getHeight() - 55);
		send.setPrefWidth(80);
		send.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		send.setStyle(Styles.BUTTONSTYLE);
		send.setOnMouseEntered(e -> send.setStyle(Styles.BUTTONHOVER));
		send.setOnMouseExited(e -> send.setStyle(Styles.BUTTONSTYLE));
		
		pane.getChildren().addAll(emailField, emailLabel, passField, passLabel, send);
		stage.getIcons().add(Styles.GSIcon);
		stage.setScene(scene);
	}
	
	public void showVerificationPage() {
		stage.show();
	}

	public TextField getEmailField() {
		return emailField;
	}

	public PasswordField getPassField() {
		return passField;
	}

	public Button getSend() {
		return send;
	}
}
