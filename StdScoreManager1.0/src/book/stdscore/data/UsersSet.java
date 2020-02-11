package book.stdscore.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Iterator;

public class UsersSet {
	private InitDB db;
	private Statement stmt;

	public UsersSet() {
		super();
		// TODO 自动生成的构造函数存根
		db = new InitDB();
		stmt = db.getStmt();
	}
	
	public boolean isValid(User user) {
		boolean valid = false;
		ResultSet rs = db.getRs("select * from users where name='" + user.getName() +
				"' and password='" + user.getPassword() + "' and job=" + user.getJob());
		try {
			if(rs.next())
				valid = true ;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return valid ;
	}

	public int addUser(User user) {
		int num = 0;
		if(user==null) {
			return -1;
		}

		ResultSet rs = db.getRs("select * from users where name='" + user.getName() +
				"' and job=" + user.getJob());
		try {
			if(rs.next())
				return -2;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}		
		
		String sqlStr = "insert into users (name,password,job) " +"values ('" + user.getName() + "'," + user.getPassword() + "'," + user.getJob() +")" ;
		try {
			num = stmt.executeUpdate(sqlStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public int modifyUser(User oldUser, User newUser) {
		int num=0;
		String updateStr = "update users set name='" + newUser.getName() +"',passwrod='" + 
				newUser.getPassword() + "',job=" + newUser.getJob() +
				" where name='" + oldUser.getName() + "' and job="
				+ oldUser.getJob();
		try {
			num = stmt.executeUpdate(updateStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public int delUser(User user){
		int num=0;
		String delStr = "delete from users where name='" + user.getName() +"'";
		try {
			num = stmt.executeUpdate(delStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return num;
	}

	public User findUser(String name) {
		User user = null ;
		String sql = "select * from users where name='" + name +"'" ;
		ResultSet rs;
		try {
			rs = db.getRs(sql);
			if(rs.next())
				user = new User(rs.getString(1),rs.getString(2),rs.getInt(3));
			else
				return null ;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user ;
	}

}
