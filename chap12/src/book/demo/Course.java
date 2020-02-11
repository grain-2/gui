package book.demo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Course implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4906331992862999016L;
	private int id = -1 ;
	private String name;
	private String type;
	private float score;
	static InitDB db = InitDB.getInitDB();

	public Course(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public Course(int id, String name, String type) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static Course getFromDB(int courseID) { // 从数据库中创建
		ResultSet rs = db.getRs("select * from course where id=" + courseID) ;
		try {
			if(rs.next())
				return new Course(rs.getInt(1),rs.getString(2),rs.getString(3)) ;
			else 
				return null ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public static Course getFromDB(String name, String type) {
		ResultSet rs = db.getRs("select * from course where name='" + name + "' and type='" + type + "'") ;
		try {
			if(rs.next())
				return new Course(rs.getInt(1),rs.getString(2),rs.getString(3)) ;
			else 
				return null ;
		} catch (SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}

	public int insertToDB() { // 返回插入记录的ID
		ResultSet rs = db.getRs("select * from course where name='"+this.name+"' and type='"+this.type+"'") ;
		try {
			if(!rs.next()) {
				String sql = "insert into course (name,type) values('" + this.name + "','" + this.type +"')";
				db.getStmt().executeUpdate(sql);
				rs = db.getRs("select id from course where name='"+this.name+"' and type='"+this.type+"'");
				rs.next();
				System.out.println("cid:"+rs.getInt(1));
				return rs.getInt(1) ; 
			} else {
				return rs.getInt(1) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1 ;
		}
	}

	public int updateToDB() {
		if(this.id != -1) {
			ResultSet rs = db.getRs("select * from course	where name='"+this.name+"' and type='"+this.type+"'");
			try {
				if(rs.next()) 
					return -3 ; // 新修改的课程名与课程类型已经存在
				String sql = "update course set name='" +this.name +"',type='" +this.type+ "'where id = " +this.id ;
				return db.getStmt().executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				return -1 ;
			}
		} else {
			return -2 ;
		}
	}

}
