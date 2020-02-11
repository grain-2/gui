package book.stdscore.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class ScoreCourseTreeRoot extends TreeNode {
	private Teacher teacher;
	private TreeNode parent ;
	private DepartmentTreeNode children[] ;
	private ArrayList<DepartmentTreeNode> departmentList = new ArrayList<DepartmentTreeNode>();
	private ArrayList<GradeTreeNode> gradeList = new ArrayList<GradeTreeNode>();
	private ArrayList<ClassTreeNode> cClassList = new ArrayList<ClassTreeNode>();
//	private CourseTypeTreeNode[] ctypenodes;
	private ArrayList<CourseTreeNode> courseList = new ArrayList<CourseTreeNode>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private InitDB db ;

	public ScoreCourseTreeRoot(Teacher teacher) {
		super(null);
		// TODO Auto-generated constructor stub
		this.teacher = teacher;
		this.db = InitDB.getInitDB();
		this.setDepartmentList() ;
		setParent(null);
		setChildren(departmentList.toArray(new DepartmentTreeNode[departmentList.size()]));
		
		for(DepartmentTreeNode department : departmentList) {
			this.setGradeList(department.getDepartment().getId()) ;
			department.setChildren(gradeList.toArray(new GradeTreeNode[gradeList.size()]));
			department.setParent(this);
			for(GradeTreeNode grade : gradeList) {
				this.setcClassList(department.getDepartment().getId(), ((GradeClass)grade.getValue()).getGrade()) ;
				grade.setChildren(cClassList.toArray(new ClassTreeNode[cClassList.size()])) ;
				grade.setParent(department);
				for(ClassTreeNode cClass : cClassList) {
//					ctypenodes = new CourseTypeTreeNode[]{new CourseTypeTreeNode("公共基础课"),
//							new CourseTypeTreeNode("专业基础课"),new CourseTypeTreeNode("专业选修课")};
					this.setCourselist(department.getDepartment(),((GradeClass)grade.getValue()).getGrade(),
							((GradeClass)cClass.getValue()).getcClass());
					cClass.setChildren(courseList.toArray(new CourseTreeNode[courseList.size()]));
					cClass.setParent(grade);
					for(CourseTreeNode course : courseList) {// 各课程的选课学生表
						course.setChildren(null);
						course.setParent(cClass);
					}
/*
					for(CourseTypeTreeNode ctype : ctypenodes) {// 各课程类型下的课程
						this.setCourselist(department.getDepartment(),(String)ctype.getValue());
						ctype.setChildren(courseList.toArray(new CourseTreeNode[courseList.size()]));
						ctype.setParent(cClass);
						for(CourseTreeNode course : courseList) {// 各课程的选课学生表
							//setStudentList(department.getDepartment(), ((GradeClass)grade.getValue()).getGrade(),
							//		((GradeClass)cClass.getValue()).getcClass());
							// 设置已选学生表tableSelected组件中的数据集为 studentList，需要为表格tableSelected创建查看器，通过树节点选择事件设置其input属性
							course.setParent(ctype);
						}

					}
*/					
				}
			}
		}
	}

	public void setCourselist(Department dept,int grade,int cClass) {
		//dept.setCoursesListFromDB();
		//ArrayList<Course> coursesList = new ArrayList<Course>();
		this.courseList.clear();
		String sql = "select T.courseID,C.name,C.type from teacher_course AS T,course as C where C.id=T.courseID" + 
				" and departmentId=" + dept.getId() + " and teacherID=" + teacher.getId() +
				 " and grade=" + grade +" and class=" + cClass + " group by CourseID";
		ResultSet rs = this.db.getRs(sql) ;
		try {
			while(rs.next()) {
				Course course = new Course(rs.getInt(1),rs.getString(2),rs.getString(3)) ;
				this.courseList.add(new CourseTreeNode(course)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		
		for(Course course : coursesList) {
			if(course.getType().equals(ctype))
				this.courseList.add(new CourseTreeNode(course));
		}
*/
	}

	public ArrayList<DepartmentTreeNode> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList() {
		this.departmentList.clear();
		ResultSet rs = this.db.getRs("select D.id,D.name from department AS D,teacher_course AS TC where TC.teacherID=" + 
				teacher.getId() + " and D.id=TC.departmentID group by TC.departmentID") ;
		try {
			while(rs.next()) {
				Department department = new Department(rs.getInt(1),rs.getString(2)) ;
				this.departmentList.add(new DepartmentTreeNode(department)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setGradeList(int departmentId) {
		this.gradeList.clear();
		String sql = "select grade from teacher_course where departmentId=" + departmentId + 
				" and teacherID=" + teacher.getId() + " group by grade";
		ResultSet rs = this.db.getRs(sql) ;
		try {
			while(rs.next()) {
				GradeClass grade = new GradeClass(departmentId,rs.getInt(1)) ;
				this.gradeList.add(new GradeTreeNode(grade)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setcClassList(int departmentId, int grade) {
		this.cClassList.clear();
		String sql = "select class from teacher_course where departmentId=" + departmentId + 
				" and teacherID=" + teacher.getId() + " and grade=" + grade +" group by class";
		ResultSet rs = this.db.getRs(sql) ;
		try {
			while(rs.next()) {
				GradeClass cClass = new GradeClass(departmentId,grade,rs.getInt(1)) ;
				this.cClassList.add(new ClassTreeNode(cClass)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
/*
	public void setStudentList(Department department, int grade, int cClass) {
		this.studentList.clear();
		ResultSet rs = this.db.getRs("select * from student where departmentID=" + department.getId() + 
				" and grade=" + grade + " and class=" + cClass ) ;
		try {
			while(rs.next()) {
				Student student = new Student(rs.getLong(1),rs.getString(2),department,rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)) ;
				this.studentList.add(student) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						
	}
*/
	@Override
	public TreeNode[] getChildren() {
		// TODO Auto-generated method stub
		return this.children ;
	}
	@Override
	public TreeNode getParent() {
		// TODO Auto-generated method stub
		return this.parent ;
	}
	@Override
	public Object getValue() {
		// TODO Auto-generated method stub
		return this ;
	}
	@Override
	public boolean hasChildren() {
		// TODO Auto-generated method stub
		return this.children!=null && this.children.length>0 ;
	}
	@Override
	public void setChildren(TreeNode[] children) {
		// TODO Auto-generated method stub
		
		this.children = (DepartmentTreeNode[]) children;
	}
	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		this.parent = parent ;
	}

	@Override
	public String toString() {
		return "选课树";
	}
	
}
