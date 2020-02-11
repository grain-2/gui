package book.stdscore.data;

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
//	private static final InitDB initDB_obj = new InitDB() ;

	public InitDB() {
		DBDriver = "com.mysql.jdbc.Driver" ;
		url = "jdbc:mysql://localhost/scoremanage?useUnicode=true" + "&characterEncoding=GBK";
		user = "root";
		try {
			// 1. 注册驱动
			Class.forName(DBDriver);
			// 2. 获得与数据库的连接
			conn=DriverManager.getConnection(url,user, password);
			// 3. 获取表达式
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
/*	
	public static InitDB getInitDB() {
		return initDB_obj ;
	}
*/	
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
				e.printStackTrace();
			}
		}
		return rs;
	}
	
	public void closeDB() {
		try {
			if(rs!=null)
				rs.close();
			if(stmt!=null)
				stmt.close();
			if(conn!=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
