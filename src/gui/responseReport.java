package gui;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import comm.Connector;

public class responseReport {
	
//	public static void main(String[] args) {
//		String fileName = "testquiz";
//		createResponseReportTxt(fileName);
//		try {
//			appendResponseReportTxt(fileName);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	public static void appendResponseReportTxt(String fileName) throws SQLException {
		String userHomePath = System.getProperty("user.home");
		
		try {
			Connector con = new Connector();
			con.connect();
			ResultSet rsEmail = con.MakeQuery("SELECT \"studentEmail\" FROM \"" + fileName+"\"");
			
			while(rsEmail.next()) {
				String email = rsEmail.getString(1);
				email = email.substring(0, 6);
				String responseReport = userHomePath+"\\Documents\\Reports\\Responses\\"+fileName+email+".answers.txt";
			
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(responseReport))) {
			bw.write("\t\t\t\t\t"+fileName+" Responses");
			bw.newLine();
			
			//select from statement gets emails and grade
			
			ResultSet resultSet = con.MakeQuery("Select \"studentEmail\",\"responses\" from \""+fileName+"\"");	
			ResultSet questions = con.MakeQuery("SELECT \"questions\" FROM \"" + fileName +"questions\"");
			while (resultSet.next() && questions.next()) {    
				String question = questions.getString(1);
				Array responses = resultSet.getArray(2);
				String[] sResponses = (String[])responses.getArray();
				bw.write(email); //Print email
				bw.newLine();
				bw.write(question);
				bw.newLine();
				for (int i=0; i < sResponses.length; i++)  {
					bw.write(sResponses[i]); //Print Response
					bw.newLine();
					questions.next();
					question = questions.getString(1);
					bw.write(question);
					bw.newLine();
				}
				bw.newLine();
				questions.next();//Move to the next line to print the next row.           
			}

		} catch (IOException e) {
			e.printStackTrace();
			}
		}
		} catch(Exception e) {
			e.printStackTrace();
		}
}
	
	//Creates Text File for Response Report
	public static void createResponseReportTxt(String fileName) {
			try {
			Connector con = new Connector();
			con.connect();
			
			ResultSet resultSet = con.MakeQuery("SELECT \"studentEmail\" FROM \"" + fileName+"\"");
			
			while(resultSet.next()) {
				String email = resultSet.getString(1);
				email = email.substring(0, 6);
				String userHomePath = System.getProperty("user.home");
				File gradeReport = new File(userHomePath+"\\Documents\\Reports\\Responses\\"+fileName+ email+".answers.txt");
				if (gradeReport.createNewFile()){
		    		System.out.println("File is created!");
		    	}else{
		    		System.out.println("File already exists.");
		    	}
			}
	   		 
			//Change file to where ever Harris wants
			//userHomePath is the default user default account 
			//Ex. C:\Users\as12660
		
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} catch (Exception ie) {
	    		ie.printStackTrace();
		}
	}
}