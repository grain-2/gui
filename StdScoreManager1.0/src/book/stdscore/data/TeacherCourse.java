package book.stdscore.data;

public class TeacherCourse {
	private int id ;
	private int teacherID ;
	private int courseID ;
	private int departmentID ;
	private int grade ;
	private int cClass ;

	public TeacherCourse(int teacherID, int courseID, int departmentID,
			int grade, int cClass) {
		super();
		this.teacherID = teacherID;
		this.courseID = courseID;
		this.departmentID = departmentID;
		this.grade = grade;
		this.cClass = cClass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
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

}
