package gui;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EmailVerificationPage {
    private Stage stage;
    private Pane pane;
    private Scene scene;
    private Label emailVerify;

    public EmailVerificationPage() {
        stage = new Stage();
        pane = new Pane();
        scene = new Scene(pane, 400, 200);
        buildEmailVerificationPage(stage, pane, scene);
    }

    public void buildEmailVerificationPage(Stage stage, Pane pane, Scene scene) {

        pane.setStyle(Styles.BACKGROUNDCOLOR);
        stage.setTitle("Successful Email");

        emailVerify = new Label("Email Successfully Sent");
        emailVerify.setFont(Font.font("Arial", FontWeight.BOLD,22));
        emailVerify.setTextFill(Paint.valueOf("#FFFFFF"));
        emailVerify.setLayoutX(15);
        emailVerify.setLayoutY(20);


        pane.getChildren().addAll(emailVerify);
        stage.getIcons().add(Styles.GSIcon);
        stage.setScene(scene);
    }

    public void showEmailVerificationPage() {
        stage.show();
    }

    public void close() {
    	stage.close();
    }

}