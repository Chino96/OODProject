package gui;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

public class gradeReport {

	public static void main(String[] args) {
		String fileName = "testquiz";
		createGradeReportTxt(fileName);
		appendGradeReportTxt(fileName);

	}
	
	//Pulls in Information for Grade Report
	public static void appendGradeReportTxt(String fileName) {
		String userHomePath = System.getProperty("user.home");
		String gradeReport = userHomePath+"\\Documents\\Reports\\Scores\\"+fileName+".scores.txt";
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(gradeReport))) {

			String content = "\t\t\t\t\t"+fileName+" Scores";

			bw.write(content);

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