package gui;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import comm.Connector;
import comm.DataBase;

public class InsertIntoQQ {
	public static DataBase db = new DataBase();

	public static void main(String[] args) {
		try {
			System.out.println("here1");
			String bleh[] = {"d", "b", "c"};
			String quizName = "testquiz";
			String sql = "INSERT INTO " + quizName + " VALUES (?, ?, ?);";
		
			Connector conn = new Connector();
			conn.connect();
			Array arrBleh = Connector.connect.createArrayOf("text", bleh);
			PreparedStatement p = Connector.connect.prepareStatement(sql);
			p.setString(1, "abc@gmail.com"); // Change to email value once stored
			p.setArray(2, arrBleh); //Name of array
			p.setInt(3, 80); //Final grade once calculated
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

}
