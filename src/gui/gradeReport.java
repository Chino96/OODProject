package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import comm.Connector;

public class gradeReport {

	//delete main method once connected for real
	public static void main(String[] args) {
		String fileName = "testquiz";
		createGradeReportTxt(fileName);
		try {
			appendGradeReportTxt(fileName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Pulls in Information for Grade Report
	public static void appendGradeReportTxt(String fileName) throws SQLException {
		String userHomePath = System.getProperty("user.home");
		String gradeReport = userHomePath+"\\Documents\\Reports\\Scores\\"+fileName+".scores.txt";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(gradeReport))) {

			bw.write("\t\t\t\t\t"+fileName+" Scores");
			bw.newLine();
			
			Connector con = new Connector();
			con.connect();
			//select from statement gets emails and grade
			ResultSet resultSetAvg = con.MakeQuery("Select AVG(\"finalGrade\") from \""+fileName+"\"");
			
			//Calc the average for the class
			while (resultSetAvg.next()) { 
				Double average = resultSetAvg.getDouble(1);
				String saverage = Double.toString(Math.round(average * 100.0)/100.0);
				bw.write("The Class Average is "+saverage);
				bw.newLine();
				bw.newLine();
			}
			
			ResultSet resultSet = con.MakeQuery("Select \"studentEmail\",\"finalGrade\" from \""+fileName+"\"");	
			while (resultSet.next()) {    
				String email = resultSet.getString(1);
				Double grade = resultSet.getDouble(2);
				String sgrade = Double.toString(Math.round(grade * 100.0)/100.0);
				bw.write(email + " -\t "); //Print email
				bw.write(sgrade); //Print grade
				bw.newLine();//Move to the next line to print the next row.           
			}

			System.out.println("Done");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Creates Text File for Grade Reports
	public static void createGradeReportTxt(String fileName) {
			try {
   		 
			//Change file to where ever Harris wants
			//userHomePath is the default user default account 
			//Ex. C:\Users\as12660
			String userHomePath = System.getProperty("user.home");
			File gradeReport = new File(userHomePath+"\\Documents\\Reports\\Scores\\"+fileName+".scores.txt");
		      
		      if (gradeReport.createNewFile()){
		        System.out.println("File is created!");
		      }else{
		        System.out.println("File already exists.");
		      }
	    	} catch (IOException e) {
		      e.printStackTrace();
		}
	}

}