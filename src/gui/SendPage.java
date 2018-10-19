package gui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class SendPage {

	private Stage sendStage;
	private Stage parentStage;
	private Pane sendPane;
	private Scene sendScene;

	private ComboBox<String> startMonth;
	private ComboBox<Integer> startDay;
	private ComboBox<Integer> startHour;
	private ComboBox<Integer> startMin;
	private ComboBox<String> endMonth;
	private ComboBox<Integer> endDay;
	private ComboBox<Integer> endHour;
	private ComboBox<Integer> endMin;

	private Button send;

	private Button cancel;

	private RadioButton rb1;
	private RadioButton rb2;
	private RadioButton rb3;

	private CheckBox cb1;
	private CheckBox cb2;

	private ToggleGroup group = new ToggleGroup();

	private TextField quizCode;

	public SendPage(Stage parentStage) {
		sendStage = new Stage();
		sendPane = new Pane();
		quizCode = new TextField();
		this.parentStage = parentStage;
		cancel = new Button("Cancel");
		send = new Button("Send");

		cb1 = new CheckBox("Randomize Questions");
		cb2 = new CheckBox("Randomize Answer Choices");

		rb1 = new RadioButton("Provide feedback with all questions.");
		rb2 = new RadioButton("Provide feedback with only incorrect answers.");
		rb3 = new RadioButton("Provide feeedback with only correct answers.");

		startMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

		startDay = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));

		startHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

		startMin = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));

		endMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

		endDay = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));

		endHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

		endMin = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));

		buildSendPage(sendStage, parentStage, sendPane, sendScene);
	}

	public void buildSendPage(Stage sendStage, Stage parentStage, Pane sendPane, Scene sendScene) {
		sendPane.setStyle(Styles.BACKGROUNDCOLOR);
		sendStage.setTitle("Send Quiz");
		sendScene = new Scene(sendPane, 800, 340);

		startMonth.setLayoutX(20);
		startMonth.setPrefWidth(120);
		startMonth.setLayoutY(82);
		startMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startMonth.setOnMouseEntered(e -> startMonth.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startMonth.setOnMouseExited(e -> startMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		startDay.setLayoutX(startMonth.getLayoutX() + startMonth.getPrefWidth() + 5);
		startDay.setPrefWidth(90);
		startDay.setLayoutY(82);
		startDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startDay.setOnMouseEntered(e -> startDay.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startDay.setOnMouseExited(e -> startDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		startHour.setLayoutX(startDay.getLayoutX() + startDay.getPrefWidth() + 5);
		startHour.setPrefWidth(90);
		startHour.setLayoutY(82);
		startHour.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startHour.setOnMouseEntered(e -> startHour.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startHour.setOnMouseExited(e -> startHour.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		startMin.setLayoutX(startHour.getLayoutX() + startHour.getPrefWidth() + 5);
		startMin.setPrefWidth(80);
		startMin.setLayoutY(82);
		startMin.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startMin.setOnMouseEntered(e -> startMin.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startMin.setOnMouseExited(e -> startMin.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		endMonth.setLayoutX(20);
		endMonth.setLayoutY(200);
		endMonth.setPrefWidth(120);
		endMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		endMonth.setOnMouseEntered(e -> endMonth.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		endMonth.setOnMouseExited(e -> endMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		endDay.setLayoutX(endMonth.getLayoutX() + endMonth.getPrefWidth() + 5);
		endDay.setLayoutY(endMonth.getLayoutY());
		endDay.setPrefWidth(80);
		endDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		endDay.setOnMouseEntered(e -> endDay.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		endDay.setOnMouseExited(e -> endDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		endHour.setLayoutX(endDay.getLayoutX() + endDay.getPrefWidth() + 5);
		endHour.setLayoutY(endMonth.getLayoutY());
		endHour.setPrefWidth(80);
		endHour.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		endHour.setOnMouseEntered(e -> endHour.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		endHour.setOnMouseExited(e -> endHour.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		endMin.setLayoutX(endHour.getLayoutX() + endHour.getPrefWidth() + 5);
		endMin.setPrefWidth(80);
		endMin.setLayoutY(endMonth.getLayoutY());
		endMin.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		endMin.setOnMouseEntered(e -> endMin.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		endMin.setOnMouseExited(e -> endMin.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

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

		quizCode.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 34px; -fx-font-weight: bold;");
		quizCode.setPrefWidth(130);
		quizCode.setMaxHeight(50);
		quizCode.setAlignment(Pos.CENTER);
		quizCode.setLayoutX((qBox.getPrefWidth() - quizCode.getPrefWidth()) / 2 + qBox.getLayoutX());
		quizCode.setLayoutY(qBox.getLayoutY() + 45);
		quizCode.setPromptText("1234");
		quizCode.setTextFormatter(new TextFormatter<String>((Change change) -> {
			String newText = change.getControlNewText();
			if (newText.length() > 4) {
				return null;
			} else {
				return change;
			}
		}));

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

		rb1.setToggleGroup(group);
		rb1.setFont(Font.font("Arial", 14));
		rb1.setSelected(true);

		rb2.setToggleGroup(group);
		rb2.setFont(Font.font("Arial", 14));

		rb3.setToggleGroup(group);
		rb3.setFont(Font.font("Arial", 14));

		VBox rBox = new VBox();
		rBox.setPrefWidth(310);
		rBox.setLayoutX(feedbackBox.getLayoutX());
		rBox.setLayoutY(feedbackBox.getLayoutY() + 40);
		rBox.setAlignment(Pos.TOP_LEFT);
		rBox.setSpacing(10);
		rBox.getChildren().addAll(rb1, rb2, rb3);

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

		send.setLayoutX(sendScene.getWidth() - 100);
		send.setLayoutY(sendScene.getHeight() - 55);
		send.setPrefWidth(80);
		send.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		send.setStyle(Styles.BUTTONSTYLE);
		send.setOnMouseEntered(e -> send.setStyle(Styles.BUTTONHOVER));
		send.setOnMouseExited(e -> send.setStyle(Styles.BUTTONSTYLE));

		cancel.setLayoutX(send.getLayoutX() - 110);
		cancel.setLayoutY(sendScene.getHeight() - 55);
		cancel.setPrefWidth(95);
		cancel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		cancel.setStyle(Styles.BUTTONSTYLE);
		cancel.setOnMouseEntered(e -> cancel.setStyle(Styles.BUTTONHOVER));
		cancel.setOnMouseExited(e -> cancel.setStyle(Styles.BUTTONSTYLE));
		cancel.setOnAction(event12 -> {
			sendStage.hide();
			parentStage.show();
		});

		sendPane.getChildren().addAll(cb1, cb2, send, startMonth, startDay, startHour, startMin, feedbackBox, dayLabel,
				hourLabel, monthLabel, endDayLabel, endHourLabel, endMonthLabel, qBox, quizCode, rBox, startTimeBox,
				endTimeBox, cancel, endMonth, endDay, endHour, endMin);
		sendStage.getIcons().add(Styles.GSIcon);
		sendStage.setScene(sendScene);
	}

	public void showSendPage() {
		this.sendStage.show();
		this.parentStage.hide();
	}

	public ComboBox<String> getStartMonth() {
		return startMonth;
	}

	public ComboBox<Integer> getStartDay() {
		return startDay;
	}

	public ComboBox<Integer> getStartHour() {
		return startHour;
	}

	public ComboBox<Integer> getStartMin() {
		return startMin;
	}

	public ComboBox<String> getEndMonth() {
		return endMonth;
	}

	public ComboBox<Integer> getEndDay() {
		return endDay;
	}

	public ComboBox<Integer> getEndHour() {
		return endHour;
	}

	public ComboBox<Integer> getEndMin() {
		return endMin;
	}

	public Button getSend() {
		return send;
	}

	public Button getCancel() {
		return cancel;
	}

	public RadioButton getRb1() {
		return rb1;
	}

	public RadioButton getRb2() {
		return rb2;
	}

	public RadioButton getRb3() {
		return rb3;
	}

	public CheckBox getCb1() {
		return cb1;
	}

	public CheckBox getCb2() {
		return cb2;
	}

	public TextField getQuizCode() {
		return quizCode;
	}
}
