package book.stdscore.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class ScoreTchData extends TreeNode {
	private Teacher teacher = null ;
	private ArrayList<CourseTreeNode> courseList = new ArrayList<CourseTreeNode>();
	private ArrayList<DepartmentTreeNode> departmentList = new ArrayList<DepartmentTreeNode>();
	private ArrayList<GradeClassTreeNode> gradeClassList =new ArrayList<GradeClassTreeNode>();
	private ArrayList<StudentCourse> studentList = new ArrayList<StudentCourse>() ;
	
	private TreeNode parent ;
	private CourseTreeNode[] children ;

	public ScoreTchData(Object value) {
		super(value);
		// TODO Auto-generated constructor stub
		this.teacher = (Teacher)value ;
		this.setCourseList(null) ;
		for(CourseTreeNode ctn : courseList) {
			this.setDepartmentList(ctn) ;
			ctn.setParent(this) ;
			ctn.setChildren(departmentList.toArray(new DepartmentTreeNode[departmentList.size()]));
			for(DepartmentTreeNode dtn : departmentList) {
				this.setGradeClassList(ctn, dtn);
				dtn.setParent(ctn) ;
				dtn.setChildren(gradeClassList.toArray(new GradeClassTreeNode[gradeClassList.size()]));
				for(GradeClassTreeNode gtn : gradeClassList) {
					//this.setStudentList(ctn,dtn,gtn) ;
					gtn.setParent(dtn);
					//gtn.setChildren(studentList.toArray(new StudentScore[studentList.size()]));
					gtn.setChildren(null);
				}

			}
		}
		
	}

	public ArrayList<CourseTreeNode> getCourseList() {
		return courseList;
	}

	public void setCourseList(ArrayList<CourseTreeNode> courseList) {
		this.courseList.clear();
		String sql = "select courseID from teacher_course where teacherID=" + 
			this.teacher.getId() +" group by courseID" ;
		ResultSet rs = new InitDB().getRs(sql) ;
		try {
			while(rs.next()) {
				this.courseList.add(new CourseTreeNode(Course.getFromDB(rs.getInt(1))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<DepartmentTreeNode> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(CourseTreeNode courseTreeNode) {
		this.departmentList.clear();
		int courseID = courseTreeNode.getCourse().getId();
		String sql = "select departmentID from department_course where courseID=" + courseID + " group by departmentID" ;
		ResultSet rs = new InitDB().getRs(sql);
		try {
			while(rs.next()) {
				departmentList.add(new DepartmentTreeNode(Department.getFromDB(rs.getInt(1))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<GradeClassTreeNode> getGradeClassList() {
		return gradeClassList;
	}

	public void setGradeClassList(CourseTreeNode courseTreeNode, DepartmentTreeNode departmentTreeNode) {
		this.gradeClassList.clear();
		int courseID = courseTreeNode.getCourse().getId();
		int departmentID = departmentTreeNode.getDepartment().getId() ;
		String sql = "select grade,class from teacher_course where courseID=" + courseID + 
			" and departmentID=" +departmentID + " and teacherID=" + this.teacher.getId();
		ResultSet rs = new InitDB().getRs(sql);
		try {
			while(rs.next()) {
				gradeClassList.add(new GradeClassTreeNode(new GradeClass(departmentID,rs.getInt(1),rs.getInt(2))));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<StudentCourse> getStudentList() {
		return studentList;
	}

	public void setStudentList(CourseTreeNode ctn, DepartmentTreeNode dtn, GradeClassTreeNode gtn) {
		this.studentList.clear();
		int courseID = ctn.getCourse().getId();
		int departmentID = dtn.getDepartment().getId();
		int grade = gtn.getGradeClass().getGrade();
		int classNo = gtn.getGradeClass().getcClass() ;
		
		String sql = "select student.id,student_course.id from student,student_course where departmentID=" +departmentID + " and grade=" +
			grade + " and class=" + classNo + " and courseID=" + courseID ;
		ResultSet rs = new InitDB().getRs(sql);
		try {
			while(rs.next()) {
				studentList.add(StudentCourse.getFromDB(rs.getLong(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	@Override
	public TreeNode[] getChildren() {
		// TODO Auto-generated method stub
		//return super.getChildren();
		return this.children ;
	}

	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		//return super.getParent();
		return this.parent;
	}

	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		//return super.getValue();
		return this ;
	}

	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		//return super.hasChildren();
		return this.children!=null && this.children.length>0 ;
	}

	@Override
	public void setChildren(TreeNode[] children) {
		// TODO Auto-generated method stub
		//super.setChildren(children);
		this.children = (CourseTreeNode[])children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = parent ;
	}

}
