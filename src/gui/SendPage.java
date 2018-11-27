package gui;

import java.util.Calendar;

import javafx.collections.FXCollections;
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
	private RadioButton rb4;

	private CheckBox cb1;
	private CheckBox cb2;
	private CheckBox cb3;

	private Label errorLabel;

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
		cb2 = new CheckBox("Show Feed Back");
		cb3 = new CheckBox("In Class Quiz");
		

		rb1 = new RadioButton("Provide feedback with all questions.");
		rb2 = new RadioButton("Provide feedback with only incorrect answers.");
		rb3 = new RadioButton("Provide feeedback with only correct answers.");
		rb4 = new RadioButton("Do not provide any feedback.");

		startMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

		startDay = new ComboBox<>(FXCollections.observableArrayList( 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));

		startHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

		startMin = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));

		endMonth = new ComboBox<>(FXCollections.observableArrayList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"));

		endDay = new ComboBox<>(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31));

		endHour = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23));

		endMin = new ComboBox<>(FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59));

		buildSendPage(sendStage, parentStage, sendPane, sendScene);
	}

	public void buildSendPage(Stage sendStage, Stage parentStage, Pane sendPane, Scene sendScene) {
		sendPane.setStyle(Styles.BACKGROUNDCOLOR);
		sendStage.setTitle("Send Quiz");
		sendScene = new Scene(sendPane, 800, 350);

		startMonth.setLayoutX(20);
		startMonth.setPrefWidth(130);
		startMonth.setLayoutY(82);
		startMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startMonth.setOnMouseEntered(e -> startMonth.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startMonth.setOnMouseExited(e -> startMonth.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		startDay.setLayoutX(startMonth.getLayoutX() + startMonth.getPrefWidth() + 5);
		startDay.setPrefWidth(80);
		startDay.setLayoutY(82);
		startDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px");
		startDay.setOnMouseEntered(e -> startDay.setStyle(Styles.BUTTONHOVER + "-fx-font-size: 14px"));
		startDay.setOnMouseExited(e -> startDay.setStyle(Styles.BUTTONSTYLE + "-fx-font-size: 14px"));

		startHour.setLayoutX(startDay.getLayoutX() + startDay.getPrefWidth() + 5);
		startHour.setPrefWidth(80);
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
		endMonth.setPrefWidth(130);
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

		Label startMonthLabel = new Label("Month:");
		startMonthLabel.setLayoutX(startMonth.getLayoutX() + 10);
		startMonthLabel.setLayoutY(startMonth.getLayoutY() - 20);
		startMonthLabel.setFont(Font.font("Arial", 14));
		startMonthLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		Label startDayLabel = new Label("Day:");
		startDayLabel.setLayoutX(startDay.getLayoutX() + 10);
		startDayLabel.setLayoutY(startDay.getLayoutY() - 20);
		startDayLabel.setFont(Font.font("Arial", 14));
		startDayLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		Label startHourLabel = new Label("Hour:");
		startHourLabel.setLayoutX(startHour.getLayoutX() + 15);
		startHourLabel.setLayoutY(startHour.getLayoutY() - 20);
		startHourLabel.setFont(Font.font("Arial", 14));
		startHourLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		Label startMinLabel = new Label("Minute:");
		startMinLabel.setLayoutX(startMin.getLayoutX() + 15);
		startMinLabel.setLayoutY(startMin.getLayoutY() - 20);
		startMinLabel.setFont(Font.font("Arial", 14));
		startMinLabel.setTextFill(Paint.valueOf("#FFFFFF"));

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
		endMonthLabel.setLayoutY(endMonth.getLayoutY() - 20);
		endMonthLabel.setFont(Font.font("Arial", 14));
		endMonthLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		Label endDayLabel = new Label("Day:");
		endDayLabel.setLayoutX(endDay.getLayoutX() + 10);
		endDayLabel.setLayoutY(endDay.getLayoutY() - 20);
		endDayLabel.setFont(Font.font("Arial", 14));
		endDayLabel.setTextFill(Paint.valueOf("#FFFFFF"));

		Label endHourLabel = new Label("Hour:");
		endHourLabel.setLayoutX(endHour.getLayoutX() + 10);
		endHourLabel.setLayoutY(endHour.getLayoutY() - 20);
		endHourLabel.setFont(Font.font("Arial", 14));
		endHourLabel.setTextFill(Paint.valueOf("#FFFFFF"));


		Label endMinLabel = new Label("Minute:");
		endMinLabel.setLayoutX(endMin.getLayoutX() + 15);
		endMinLabel.setLayoutY(endMin.getLayoutY() - 20);
		endMinLabel.setFont(Font.font("Arial", 14));
		endMinLabel.setTextFill(Paint.valueOf("#FFFFFF"));

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
		qBox.setLayoutY(80);
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

		cb2.setLayoutX(30);
		cb2.setLayoutY(255);
		cb2.setTextFill(Color.WHITE);
		cb2.setFont(Font.font("Arial", 13));
		
		cb3.setLayoutX(30);
		cb3.setLayoutY(cb2.getLayoutY() + 30);
		cb3.setTextFill(Color.WHITE);
		cb3.setFont(Font.font("Arial", 13));

		send.setLayoutX(sendScene.getWidth() - 100);
		send.setLayoutY(sendScene.getHeight() - 55);
		send.setPrefWidth(80);
		send.setFont(Font.font("Arial", FontWeight.BOLD,18));
		send.setStyle(Styles.BUTTONSTYLE);
		send.setOnMouseEntered(e -> send.setStyle(Styles.BUTTONHOVER));
		send.setOnMouseExited(e -> {send.setStyle(Styles.BUTTONSTYLE);
		check();
		});

		errorLabel = new Label();
		errorLabel.setLayoutX(90);
		errorLabel.setLayoutY(sendPane.getHeight()-40);
		errorLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

		cancel.setLayoutX(send.getLayoutX() - 110);
		cancel.setLayoutY(sendScene.getHeight() - 55);
		cancel.setPrefWidth(95);
		cancel.setFont(Font.font("Arial", FontWeight.BOLD, 18));
		cancel.setStyle(Styles.BUTTONSTYLE);
		cancel.setOnMouseEntered(e -> cancel.setStyle(Styles.BUTTONHOVER));
		cancel.setOnMouseExited(e -> cancel.setStyle(Styles.BUTTONSTYLE));
		cancel.setOnAction(event12 -> {
			errorLabel.setText("");
			sendStage.hide();
			parentStage.show();
		});



		sendPane.getChildren().addAll(cb2, cb3, send, startMonth, startDay, startHour, startMin, startDayLabel,
				startHourLabel, startMonthLabel, startMinLabel, endDayLabel, endHourLabel, endMonthLabel, endMinLabel, qBox, quizCode, startTimeBox,
				endTimeBox, cancel, endMonth, endDay, endHour, endMin, errorLabel);
		sendStage.getIcons().add(Styles.GSIcon);
		sendStage.setScene(sendScene);
	}
	
	public void check() {
		if(cb3.isSelected()) {
		}else {
		}
	}

	public void showSendPage() {
		this.sendStage.show();
		this.parentStage.hide();
	}

	public int getStartMonth() {
		return startMonth.getSelectionModel().getSelectedIndex();
	}

	public int getStartDay() {
		return startDay.getValue();
	}

	public int getStartHour() {
		return startHour.getValue();
	}

	public int getStartMin() {
		return startMin.getValue();
	}

	public int getEndMonth() {
		return endMonth.getSelectionModel().getSelectedIndex();
	}

	public int getEndDay() {
		return endDay.getValue();
	}

	public int getEndHour() {
		return endHour.getValue();
	}

	public int getEndMin() {
		return endMin.getValue();
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

	public CheckBox getCb3() {
		return cb3;
	}

	public TextField getQuizCode() {
		return quizCode;
	}

	public void setErrorLabel() {
		errorLabel.setText("Missing Required Files");
		errorLabel.setTextFill(Color.RED);
	}

	public void setTimeError(){
		errorLabel.setText("Quiz Time Incorrect");
		errorLabel.setTextFill(Color.RED);
	}

	public void resetErrorLabel() {
		errorLabel.setText("");
	}
	public String getStartTime(){
		   String sYear, sMonth, sDay, sHour, sMin;
		   int year = Calendar.getInstance().get(Calendar.YEAR);
		   sYear = "" + year;
		   sMonth = "" + startMonth.getSelectionModel().getSelectedIndex();
		   sDay = startDay.getValue().toString();
		   sHour = startHour.getValue().toString();
		   sMin = startMin.getValue().toString();

		   if(startMonth.getSelectionModel().getSelectedIndex()< 9){
		      sMonth = "0" + (startMonth.getSelectionModel().getSelectedIndex()+1);
		   }
		   if(startDay.getValue()< 10){
		      sDay = "0" + startDay.getValue();
		   }
		   if(startHour.getValue()< 10){
		      sHour = "0" + startHour.getValue();
		   }
		   if(startMin.getValue()< 10){
		      sMin = "0" + startMin.getValue();
		   }

		   return sYear + "-" + sMonth + "-" + sDay + " " + sHour + ":" + sMin + ":00";
		}

		public String getEndTime(){
		   String eYear, eMonth, eDay, eHour, eMin;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		   eYear = "" + year;
		   eMonth = "" + endMonth.getSelectionModel().getSelectedIndex();
		   eDay = endDay.getValue().toString();
		   eHour = endHour.getValue().toString();
		   eMin = endMin.getValue().toString();

		   if(endMonth.getSelectionModel().getSelectedIndex()< 9){
		      eMonth = "0" + (endMonth.getSelectionModel().getSelectedIndex()+1);
		   }
		   if(endDay.getValue()< 10){
		      eDay = "0" + endDay.getValue();
		   }
		   if(endHour.getValue()< 10){
		      eHour = "0" + endHour.getValue();
		   }
		   if(endMin.getValue()< 10){
		      eMin = "0" + endMin.getValue();
		   }

		   return eYear + "-" + eMonth + "-" + eDay + " " + eHour + ":" + eMin + ":00";
		}
}
