package studentTree;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDB {
	private String DBDriver = null;
	private String url = null ;
	private String user = null ;
	private String password = null ;
	private Connection conn = null;
	private Statement stmt = null ;
	private ResultSet rs = null ;
	
	private static final InitDB initDB_obj = new InitDB() ;
	
	private InitDB() {
		DBDriver = "sun.jdbc.odbc.JdbcOdbcDriver" ;
		url = "jdbc:odbc:TestTree";
		user = "";
		password = "";
		try {
			// 1. ע������
			Class.forName(DBDriver);
			// 2. ��������ݿ������
			conn = DriverManager.getConnection(url, user, password);
			// 3. ��ȡ���ʽ
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static InitDB getInitDB() {
		return initDB_obj ;
	}

	public Connection getConn() {
		return conn;
	}
	public Statement getStmt() {
		return stmt;
	}
	public ResultSet getRs(String sql) {
		if(sql.toLowerCase().indexOf("select")!=-1){
			try {
				rs = stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public void closeDB() {
		try {
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
