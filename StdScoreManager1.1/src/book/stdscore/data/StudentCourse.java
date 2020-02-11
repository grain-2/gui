package book.stdscore.data;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class StudentCourse {

	private long id ;
	private long studentID ;
	private int courseID ;
	private float score ;
	private Date updateTime ;

	public StudentCourse(long studentID, int courseID, float score) {
		this.studentID = studentID;
		this.courseID = courseID;
		this.score = score;
	}
	public StudentCourse(long id, long studentID, int courseID, float score) {
		this.id = id ;
		this.studentID = studentID;
		this.courseID = courseID;
		this.score = score;
	}
	public StudentCourse(long id, long studentID, int courseID, float score, Date updateTime) {
		this.id = id ;
		this.studentID = studentID;
		this.courseID = courseID;
		this.score = score;
		this.updateTime = updateTime ;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getStudentID() {
		return studentID;
	}
	public void setStudentID(long studentID) {
		this.studentID = studentID;
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public static StudentCourse[] getFromDB(long studentID, int courseID) {
		ArrayList<StudentCourse> stdScoreList = new ArrayList<StudentCourse>();
		String sql = "select * from student_course where studentID=" + studentID + " and courseID=" + courseID ;
		ResultSet rs = new InitDB().getRs(sql);
		try {
			while(rs.next()) {
				StudentCourse ss = new StudentCourse(rs.getLong(1),rs.getLong(2),rs.getInt(3),rs.getFloat(4),rs.getDate(5));
				stdScoreList.add(ss) ;
			}
			return stdScoreList.toArray(new StudentCourse[stdScoreList.size()]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public static StudentCourse getFromDB(long id) {
		ResultSet rs = new InitDB().getRs("select * from student_course where id=" +id);
		try {
			if(rs.next()) {
				return new StudentCourse(rs.getLong(1),rs.getLong(2),rs.getInt(3),rs.getFloat(4),rs.getDate(5)) ;
			} else {
				return null ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null ;
		}
	}
	
	public int updateToDB() {
		try {
			String sql = "update student_course set score=" + this.score + " where id=" +this.id ;
			return new InitDB().getStmt().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1 ;
		}

	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "StudentScore [courseID=" + courseID + ", id=" + id + ", score="
				+ score + ", studentID=" + studentID + ", updateTime="
				+ updateTime + "]";
	}
	@Override
	public int hashCode() { 
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + courseID;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + Float.floatToIntBits(score);
		result = prime * result + (int) (studentID ^ (studentID >>> 32));
		result = prime * result
				+ ((updateTime == null) ? 0 : updateTime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) { 
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentCourse other = (StudentCourse) obj;
		if (courseID != other.courseID)
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(score) != Float.floatToIntBits(other.score))
			return false;
		if (studentID != other.studentID)
			return false;
		if (updateTime == null) {
			if (other.updateTime != null)
				return false;
		} else if (!updateTime.equals(other.updateTime))
			return false;
		return true;
	}
}
