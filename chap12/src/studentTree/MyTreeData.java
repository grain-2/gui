package studentTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class MyTreeData extends TreeNode {

	private ArrayList<Student> studentAll = new ArrayList<Student>();
	private ArrayList<Department> departmentList = new ArrayList<Department>();
	private ArrayList<Grade> gradeList = new ArrayList<Grade>();
	private ArrayList<CClass> cClassList = new ArrayList<CClass>();
	private ArrayList<Student> studentList = new ArrayList<Student>();
	
	private TreeNode parent ;
	private TreeNode children[] ;

	public MyTreeData() {
		super(null);
		// TODO Auto-generated constructor stub
		this.setStudentAll();
		this.setDepartmentList(null) ;
		for(Department department : departmentList) {
			this.setGradeList(department.getName()) ;
			department.setChildren(gradeList.toArray(new Grade[gradeList.size()]));
			department.setParent(null);
			for(Grade grade : gradeList) {
				this.setcClassList(department.getName(), grade.getGrade()) ;
				grade.setChildren(cClassList.toArray(new CClass[cClassList.size()])) ;
				grade.setParent(department);
				for(CClass cClass : cClassList) {
					this.setStudentList(department.getName(), grade.getGrade(), cClass.getcClass());
					cClass.setChildren(studentList.toArray(new Student[studentList.size()]));
					cClass.setParent(grade);
				}
			}
		}
	}

	public void setStudentAll() {
		this.studentAll.clear();
		ResultSet rs = InitDB.getInitDB().getRs("select * from [Java语言$]") ;
		try {
			while(rs.next()) {
				Student student = new Student(null, rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6)) ;
				this.studentAll.add(student) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
	}
	
	public ArrayList<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(ArrayList<Department> departmentList) {
		this.departmentList.clear();
		ResultSet rs = InitDB.getInitDB().getRs("select 专业 from [Java语言$] group by 专业") ;
		try {
			while(rs.next()) {
				Department department = new Department(null, rs.getString(1)) ;
				this.departmentList.add(department) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public ArrayList<Grade> getGradeList() {
		return gradeList;
	}

	public void setGradeList(String department) {
		this.gradeList.clear();
		ResultSet rs = InitDB.getInitDB().getRs("select 年级  from [Java语言$] where 专业='" + department + "' group by 年级") ;
		try {
			while(rs.next()) {
				Grade grade = new Grade(null, rs.getInt(1)) ;
				this.gradeList.add(grade) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public ArrayList<CClass> getcClassList() {
		return cClassList;
	}

	public void setcClassList(String department, int grade) {
		this.cClassList.clear();
		ResultSet rs = InitDB.getInitDB().getRs("select 班级  from [Java语言$] where 专业='" + department + "' and 年级=" + grade + " group by 班级") ;
		try {
			while(rs.next()) {
				CClass cClass = new CClass(null, rs.getInt(1)) ;
				this.cClassList.add(cClass) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}				
	}

	public ArrayList<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(String department, int grade, int cClass) {
		this.studentList.clear();
		ResultSet rs = InitDB.getInitDB().getRs("select * from [Java语言$] where 专业='" + department + 
				"' and 年级=" + grade + " and 班级=" + cClass ) ;
		try {
			while(rs.next()) {
				Student student = new Student(null, rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6)) ;
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
		this.children = children ;
	}
	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		this.parent = parent ;
	}

	public ArrayList<Student> getStudentAll() {
		return studentAll;
	}

}
