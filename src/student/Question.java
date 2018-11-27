package student;

import java.util.ArrayList;

public class Question{

	public String question;

	public ArrayList<String> answers;
	public String cAnswer;

	public Question(String tQuestion) {
		answers = new ArrayList<String>();
		question = tQuestion;
		cAnswer = "";
	}
}