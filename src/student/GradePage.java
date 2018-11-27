package student;

import gui.Styles;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GradePage {
	Stage stg;
	Pane p;
	Scene sc;

	public GradePage(String s) {
		stg = new Stage();
		p = new Pane();
		sc = new Scene(p,250,150);
		buildGradePage(stg, p, sc, s);
	}

	public void buildGradePage(Stage stage, Pane pane, Scene scene, String s) {

		pane.setStyle(Styles.BACKGROUNDCOLOR);
		stage.setTitle("Grade");

		Label grade = new Label(s);
		grade.setFont(Font.font("Arial", FontWeight.BOLD, 22));
		grade.setTextFill(Paint.valueOf("#FFFFFF"));
		grade.setLayoutX(55);
		grade.setLayoutY(45);

		pane.getChildren().addAll(grade);
		stage.getIcons().add(Styles.GSIcon);
		stage.setScene(scene);
		stage.show();
	}
	
	

}
