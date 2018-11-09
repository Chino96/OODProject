package comm;

import java.sql.ResultSet;

public class DataBase {

	private Connector con;
	public DataBase() {
		con = new Connector();
	}
	
	public void Write(String s) {
		this.con.connect();
		this.con.update(s);
	}
	
	public ResultSet Read(String s) {
		if(!s.contains("SELECT")) {
			return null;
		}
		this.con.connect();
		ResultSet set = this.con.MakeQuery(s);
		return set;
	}

}
