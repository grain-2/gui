package book.stdscore.data;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Teacher implements Serializable {
	private int id;
	private String name;
	private String sex;
	private int age;
	private Department department;
	private String address;
	private String pic;
	private String intro;
	private ArrayList<Course> courseList = new ArrayList<Course>() ;

	public Teacher(int id, String name, String sex, int age,
			Department department, String address, String pic, String intro) {
		super();
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.age = age;
		this.department = department;
		this.address = address;
		this.pic = pic;
		this.intro = intro;
	}

/*	public ArrayList<Student> getStudentList() {
		return studentList;
	}
*/
	public ArrayList<Course> getCourseList() {
		return courseList;
	}
	
	public int getId() {
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		Department department;
		try {
			department = new Department(rs.getString(2));
			department.setId(rs.getInt(1));
			this.department = department;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	
	public static Teacher getFromDB(int teacherID) {
		ResultSet rs = new InitDB().getRs("select * from teacher where id=" + teacherID) ;
		try {
			if(rs.next()) {
				Department department = Department.getFromDB(rs.getInt(5));
				Teacher teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),department,rs.getString(6),rs.getString(7),rs.getString(8)) ;
				return teacher ;
			} else 
				return null ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public void setCourseListFromDB() {
		String sql = "select courseID from teacher_course where teacherID=" + this.id ;
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
				String sql = "update teacher set name='" + this.name + "',sex='" + this.sex + "',age=" + this.age + ",departmentID=" + this.department.getId() +
				",address='" + this.address + "',pic='" + this.pic + "',intro='" + this.intro + "' where id=" +this.id ;
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
			ResultSet rs = new InitDB().getRs("select * from teacher where id=" + this.id) ;
			if(!rs.next()) {
				String sql = "insert into teacher (id,name,sex,age,departmentID,address,pic,intro) values("+ this.id +",'" + this.name + "','"
				+ this.sex + "'," + this.age + "," + this.department.getId() + ",'" + this.address + "','" + this.pic + "','" + this.intro + "')";
				return new InitDB().getStmt().executeUpdate(sql);
			}else {
				return -2 ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}
	}

}
