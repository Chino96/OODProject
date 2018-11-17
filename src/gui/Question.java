package gui;

import java.util.ArrayList;

public class Question{

	public String question;

	public ArrayList<String> pAnswers;

	public Question(String tQuestion) {
		pAnswers = new ArrayList<String>();
		question = tQuestion;
	}
}