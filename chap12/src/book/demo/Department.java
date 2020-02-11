package book.demo;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Department implements Serializable {
	private int id = -1 ;
	private String name ;
	private ArrayList<Course> coursesList ;
	
	public Department(String name) {
		super();
		this.name = name;
		coursesList = new ArrayList<Course>() ;
	}

	public Department(int id,String name) {
		this.id = id ;
		this.name = name;
		coursesList = new ArrayList<Course>() ;
	}
	
	public void addCourse(Course course){
		coursesList.add(course);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Course> getCoursesList() {
		return coursesList;
	}

	public void setCoursesList(ArrayList<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Department getFromDB(int departmentID) { // �����ݿ��д���
		ResultSet rs = new InitDB().getRs("select * from department where id=" + departmentID) ;
		try {
			if(rs.next())
				return new Department(rs.getInt(1),rs.getString(2)) ;
			else 
				return null ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public static Department getFromDB(String departmentName) { // �����ݿ��д���
		ResultSet rs = new InitDB().getRs("select * from department where name='" + departmentName +"'") ;
		try {
			if(rs.next())
				return new Department(rs.getInt(1),rs.getString(2)) ;
			else 
				return null ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}

	public void setCoursesListFromDB() {
		coursesList.clear();
		String sql = "select courseID from department_course where departmentID=" + this.id ;
		ResultSet rs = new InitDB().getRs(sql) ;
		try {
			while(rs.next()) {
				coursesList.add(Course.getFromDB(rs.getInt(1))) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int updateToDB() {
		if(this.id != -1) {
			try {
				String sql = "update department set name='" + this.name + "' where id=" +this.id ;
				return new InitDB().getStmt().executeUpdate(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return -1 ;
			}
		} else {
			return -2 ;
		}
	}
	
	public int insertToDB() { 
		ResultSet rs = new InitDB().getRs("select * from department where name='" + this.name +"'") ;
		try {
			if(!rs.next()) {
				String sql = "insert into department (name) values('"+this.name+"')";
				new InitDB().getStmt().executeUpdate(sql);
				rs = new InitDB().getRs("select * from department where name='" + this.name + "'") ;
				if(rs.next()) {
					return rs.getInt(1);
				} else {
					return -2 ;
				}
			} else {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}

}
