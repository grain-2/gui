package book.stdscore.data;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.TreeNode;

public class GradeClass {
	private int id ;
	private int departmentId ;
	private int grade ;
	private int cClass ;
	
	public GradeClass(int departmentId, int grade, int cClass) {
		// TODO Auto-generated constructor stub
		this.departmentId = departmentId ;
		this.grade = grade ;
		this.cClass = cClass ;
	}

	public GradeClass(int departmentId, int grade) {
		// TODO Auto-generated constructor stub
		this.departmentId = departmentId ;
		this.grade = grade ;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	public void setDepartment(int departmentId) {
		this.departmentId = departmentId;
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

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int insertToDB() { // ���ز����¼��ID
		ResultSet rs = new InitDB().getRs("select * from department_grade_class where departmentId=" +
				this.departmentId +" and grade=" +this.grade + " and class=" +this.cClass) ;
		try {
			if(!rs.next()) {
				String sql = "insert into department_grade_class (departmentId,grade,class) values("+
						this.departmentId + "," + this.grade +"," + this.cClass + ")";
				new InitDB().getStmt().executeUpdate(sql);
				rs = new InitDB().getRs("select * from department_grade_class where departmentId=" +
						this.departmentId +" and grade=" +this.grade + " and class=" +this.cClass) ;
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
