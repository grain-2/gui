package book.stdscore.data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student implements Serializable {
	private long id = -1 ;
	private String name ;
	private Department department ;
	private int grade ;
	private int cClass;
	private String pic ;
	private String interested ;
	private ArrayList<Course> courseList = new ArrayList<Course>() ;

	public ArrayList<Course> getCourseList() {
		return courseList;
	}

	public Student(long id, String name, Department department, int grade,
			int cClass, String pic, String interested) {
		super();
		this.id = id;
		this.name = name;
		this.department = department;
		this.grade = grade;
		this.cClass = cClass;
		this.pic = pic;
		this.interested = interested;
	}
	
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public void setDepartment(int departmentID) {
		String sql = "select * from department where id=" + departmentID ;
		ResultSet rs = new InitDB().getRs(sql) ;
		//Department department;
		try {
			rs.next() ;
			//department = new Department(rs.getInt(1),rs.getString(2));
			//department.setId(rs.getInt(1));
			this.department = new Department(rs.getInt(1),rs.getString(2));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public int getcClass() {
		return cClass;
	}
	public void setcClass(int cClass) {
		this.cClass = cClass;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getInterested() {
		return interested;
	}
	public void setInterested(String interested) {
		this.interested = interested;
	}
	
	public static Student getFromDB(long studentID) {
		ResultSet rs = new InitDB().getRs("select * from student where id=" + studentID) ;
		try {
			if(rs.next()) {
				Department department = Department.getFromDB(rs.getInt(3));
				Student student = new Student(rs.getLong(1),rs.getString(2),department,rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)) ;
				return student ;
			} else {
				return null ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public void setCourseListFromDB() {
		String sql = "select courseID from student_course where studentID=" + this.id ;
		ResultSet rs = new InitDB().getRs(sql) ;
		try {
			while(rs.next()) {
				courseList.add(Course.getFromDB(rs.getInt(1))) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int updateToDB() {
		if(this.id != -1) {
			try {
				String sql = "update student set name='" + this.name + "',departmentID=" + this.department.getId() +
				",grade=" + this.grade + ",class=" + this.cClass + ",pic='" + this.pic + "',interested='" +
				this.interested + "' where id=" +this.id ;
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
		try {
			String sql = "insert into student (id,name,departmentID,grade,class,pic,interested) values(" + this.id + ",'" + this.name + "',"
			+ this.department.getId() + "," + this.grade + "," + this.cClass + ",'" + this.pic + "','" + this.interested + "')";
			return new InitDB().getStmt().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}

}