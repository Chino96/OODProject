package gui;

import java.util.ArrayList;

public class Question{

	public String question;

	public ArrayList<String> answers;
	public String cAnswers;

	
	public Question(String tQuestion) {
		answers = new ArrayList<String>();
		cAnswers = "";
		question = tQuestion;
	}
}