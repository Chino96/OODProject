package gui;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class LoginPage {
	Pane pane;
	Scene scene;
	TextField emailField;
	PasswordField pswdField;
	Button btnLogin;
	
	public LoginPage() {
		pane = new Pane();
		
		pane.setPrefHeight(385);
		pane.setPrefWidth(290);

		emailField = new TextField();
		emailField.setLayoutX(51);
		emailField.setLayoutY(148);
		emailField.setPrefWidth(196);

		Label lblEmail = new Label("Email:");
		lblEmail.setLayoutX(51);
		lblEmail.setLayoutY(127);

		pswdField = new PasswordField();
		pswdField.setLayoutX(51);
		pswdField.setLayoutY(226);
		pswdField.setPrefWidth(188);

		Label lblPassword = new Label("Password:");
		lblPassword.setLayoutX(51);
		lblPassword.setLayoutY(210);

		btnLogin = new Button("Login");
		btnLogin.setLayoutX(233);
		btnLogin.setLayoutY(352);

		pane.getChildren().addAll(btnLogin, lblPassword, lblEmail, emailField, pswdField);
		scene = new Scene(pane);

	}
	
	public Scene getScene() {
		return scene;
	}
	
	public Pane getPane() {
		return pane;
	}

	public TextField getEmailField() {
		return emailField;
	}

	public PasswordField getPswdField() {
		return pswdField;
	}
	public Button getBtnLogin() {
		return btnLogin;
	}

}
