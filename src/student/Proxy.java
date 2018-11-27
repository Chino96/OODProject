package student;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import comm.DataBase;

public class Proxy {

	public static int quizCode;
	public static String email;
	public static String quizName;

	public static String getQuizName(int quizCode) {
		DataBase db = new DataBase("student", "gsouthern");
		String s = "";
		ResultSet rs = db.Read("SELECT \"QuizName\" from \"QuizCodes\" WHERE \"QuizCode\"=" + quizCode);
		try {
			ResultSetMetaData rsm = rs.getMetaData();

			while (rs.next()) {
				for (int i = 1; i <= rsm.getColumnCount(); i++) {
					s = rs.getString(i);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	public static int checkCode(int qCode) {
		DataBase db = new DataBase();

		ResultSet rs = db.Read("SELECT \"QuizCode\" from \"QuizCodes\" WHERE \"QuizCode\"=" + qCode);
		try {
			while (rs.next()) {
				if (rs.wasNull()) {
					return 1;
				} else {
					return 0;
				}
			}
			return 3;
		} catch (Exception e) {
			return 2;
		}
	}
}
