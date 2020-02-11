package book.stdscore.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class SelCourseTreeRoot extends TreeNode {
	private TreeNode parent ;
	private DepartmentTreeNode children[] ;
	private ArrayList<DepartmentTreeNode> departmentList = new ArrayList<DepartmentTreeNode>();
	private ArrayList<GradeTreeNode> gradeList = new ArrayList<GradeTreeNode>();
	private ArrayList<ClassTreeNode> cClassList = new ArrayList<ClassTreeNode>();
	private CourseTypeTreeNode[] ctypenodes;
	private ArrayList<CourseTreeNode> courseList = new ArrayList<CourseTreeNode>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	private InitDB db ;

	public SelCourseTreeRoot() {
		super(null);
		// TODO Auto-generated constructor stub
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
					ctypenodes = new CourseTypeTreeNode[]{new CourseTypeTreeNode("公共基础课"),
							new CourseTypeTreeNode("专业基础课"),new CourseTypeTreeNode("专业选修课")};
					cClass.setChildren(ctypenodes);
					cClass.setParent(grade);
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
					
				}
			}
		}
	}

	public void setCourselist(Department dept,String ctype) {
		dept.setCoursesListFromDB();
		ArrayList<Course> coursesList = dept.getCoursesList();
		this.courseList.clear();
		for(Course course : coursesList) {
			if(course.getType().equals(ctype))
				this.courseList.add(new CourseTreeNode(course));
		}
	}
	public ArrayList<DepartmentTreeNode> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList() {
		this.departmentList.clear();
		ResultSet rs = this.db.getRs("select * from department") ;
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
		String sql = "select * from department_grade_class where departmentId=" + departmentId + " group by grade";
		ResultSet rs = this.db.getRs(sql) ;
		try {
			while(rs.next()) {
				GradeClass grade = new GradeClass(rs.getInt(2),rs.getInt(3)) ;
				this.gradeList.add(new GradeTreeNode(grade)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setcClassList(int departmentId, int grade) {
		this.cClassList.clear();
		ResultSet rs = this.db.getRs("select * from department_grade_class where departmentId=" +
				departmentId + " and grade=" + grade + " group by class") ;
		try {
			while(rs.next()) {
				GradeClass cClass = new GradeClass(departmentId,grade,rs.getInt(4)) ;
				this.cClassList.add(new ClassTreeNode(cClass)) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}


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
