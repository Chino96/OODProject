package gui;

import javafx.geometry.Side;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LoginPage {

	private Pane pane;
	private Scene scene;
	private TextField emailField;
	private PasswordField codeField;
	private Button btnLogin;
	private VBox loginBox;
	private Label error;

	//Adds shadow effect to buttons when hovered over
	private Color colorOn = new Color(0.5843, 0.4902, 0.2471, 1);
	private Color colorOff = new Color(0.5, 0.5, 0.5, 0);
	private DropShadow shadowOn = new DropShadow(20, colorOn);
	private DropShadow shadowOff = new DropShadow(20, colorOff);

	public LoginPage() {
		pane = new Pane();
		Pane outerPane = new Pane();

		String buttonStyle = " -fx-background-radius: 25px; -fx-border-color: #957D3F ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
		String buttonHoverStyle = "-fx-base: #957D3F; -fx-background-radius: 25px; -fx-border-color: #FFFFFF ; -fx-border-width: 3.5px; -fx-border-radius: 25px; -fx-background-insets: 2;";
		String backgroundColor = "-fx-background-color: #041E60";

		//Create Georgia Southern background image in pane
		Image backGrd = new Image("http://static-11.sinclairstoryline.com/resources/media/a1513171-a451-48a6-ad84-2ea32c3c1a53-large16x9_1280x720_51223E00TCAUL.png", 200, 112.5, true, true);
		BackgroundSize backgroundSize = new BackgroundSize(150, 84.375, false, false, false, false);
		BackgroundPosition bPosition = new BackgroundPosition(Side.LEFT, 45, false, Side.TOP, 15, false);
		BackgroundImage bGround = new BackgroundImage(backGrd, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,bPosition,backgroundSize);
		Background background = new Background(bGround);

		pane.setPrefHeight(300);
		pane.setPrefWidth(240);

		Label lblEmail = new Label("Email");
		lblEmail.setLayoutX(57);
		lblEmail.setLayoutY(120);
		lblEmail.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		lblEmail.setTextFill(Paint.valueOf("WHITE"));

		Label lblCode = new Label("Quiz Code");
		lblCode.setLayoutX(20);
		lblCode.setLayoutY(lblEmail.getLayoutY()+40);
		lblCode.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		lblCode.setTextFill(Paint.valueOf("WHITE"));

		emailField = new TextField();
		emailField.setLayoutX(lblEmail.getLayoutX() + 53);
		emailField.setLayoutY(lblEmail.getLayoutY() + 18);
		emailField.setPrefWidth(190);
		emailField.setStyle(buttonStyle);

		codeField = new PasswordField();
		codeField.setLayoutX(lblCode.getLayoutX() + 130);
		codeField.setLayoutY(emailField.getLayoutY() + 40);
		codeField.setPrefWidth(100);
		codeField.setAlignment(Pos.CENTER);

		codeField.setStyle(buttonStyle);
		codeField.setTextFormatter(new TextFormatter<String>((TextFormatter.Change change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > 4) {
				return null ;
			} else {
				return change ;
			}
		}));

		error = new Label();
		error.setTextFill(Color.RED);
		error.setFont(Font.font("Arial", FontWeight.BOLD, 14));

		loginBox = new VBox();
		loginBox.setLayoutX(25);
		loginBox.setLayoutY(110);
		loginBox.setAlignment(Pos.CENTER);
		loginBox.setSpacing(4);
		loginBox.getChildren().addAll(lblEmail, emailField, lblCode, codeField, error);

		shadowOn.setSpread(0.6);



		//Create button to login to quiz
		btnLogin = new Button("Submit");
		btnLogin.setLayoutX(70);
		btnLogin.setLayoutY(252);
		btnLogin.setPrefWidth(100);
		btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, 16));
		btnLogin.setStyle(buttonStyle);
		btnLogin.setOnMouseEntered(e -> btnLogin.setStyle(buttonHoverStyle));
		btnLogin.setOnMouseExited(e -> btnLogin.setStyle(buttonStyle));
		
		codeField.setOnKeyPressed(e -> {
			if (e.getCode() == KeyCode.ENTER) {
				btnLogin.fire();
			}
		});



		pane.setBackground(background);
		pane.getChildren().addAll(btnLogin, loginBox);


		outerPane.setStyle(backgroundColor);
		outerPane.getChildren().addAll(pane);

		scene = new Scene(outerPane);

	}

	public Scene getScene() {
		return scene;
	}

	public Pane getPane() {
		return pane;
	}

	public TextField getEmailField() {
		return codeField;
	}

	public PasswordField getCodeField() {
		return codeField;
	}

	public Button getBtnLogin() {
		return btnLogin;
	}

	public void setError(){
		error.setText("Invalid Email or Code");
	}

	public void resetError(){
		error.setText("");
	}

}