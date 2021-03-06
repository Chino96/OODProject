package comm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import gui.VerificationPage;

public class Connector {

	public static Connection connect;
	private Statement statment;
	private ResultSet resultSet;
	
	private final String JDBCCON = "jdbc:postgresql:";
	private final String URL = "//georgiasouthern.chqyded5qfex.us-east-1.rds.amazonaws.com:";
	private String port;
	private String dbName;
	private String user;
	private String password;

	
	public Connector() {
		port = "5432";
		dbName = "/harrisgs";
		user = "harris";
		password = "professorgs";
	}

	public Connector(String usr, String pass) {
		port = "5432";
		dbName = "/harrisgs";
		user = usr;
		password = pass;
	}

	public Connector(String por, String db, String usr, String pass) {
		this.port = por;
		this.dbName = db;
		this.user = usr;
		this.password = pass;
	}

	public int connect() {
		try {
			Class.forName("org.postgresql.Driver");
			System.out.println("Connecting to Database......");
			connect = DriverManager.getConnection(JDBCCON + URL + this.port + this.dbName, 
					this.user,
					this.password);
			System.out.println("Connection Successful");
		} catch (Exception e) {
			return 1;
		}

		return 0;
	}
	
	public ResultSet MakeQuery(String s) {
		try {
			this.resultSet = this.getStatement().executeQuery(s);
		} catch (SQLException e) {
		}
		return this.resultSet;
	}
	
	public void update(String s) {
		try {
			this.getStatement().executeUpdate(s);
		}
		catch(SQLException e) {
		}
	}
	
	public Statement getStatement() {
		try {
			this.statment = connect.createStatement();
		} catch (SQLException e) {
		}
		return statment;
	}
}
