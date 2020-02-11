package book.stdscore.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class GradeTreeNode extends TreeNode {
	private GradeClass grade;
	private TreeNode parent ;
	private TreeNode children[] ;
	
	public GradeTreeNode(GradeClass value) {
		super(value);
		// TODO 自动生成的构造函数存根
		this.grade = value;
/*
		ArrayList<ClassTreeNode> cClassList = new ArrayList<ClassTreeNode>();
		InitDB db = new InitDB();
		System.out.println(((Department)parent.getValue()));
		ResultSet rs = db.getRs("select * from department_grade_class where departmentId=" +
				((Department)parent.getValue()).getId() + " and grade=" + grade.getGrade() + " group by class") ;
		try {
			while(rs.next()) {
				GradeClass cClass = new GradeClass(((Department)parent.getValue()).getId(),grade.getGrade(),rs.getInt(4)) ;
				cClassList.add(new ClassTreeNode(cClass)) ;
			}
			db.closeDB();
			db=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			

		setChildren(cClassList.toArray(new ClassTreeNode[cClassList.size()]));
*/
	}

	@Override
	public TreeNode[] getChildren() {
		// TODO 自动生成的方法存根
		return this.children;
	}

	@Override
	public TreeNode getParent() {
		// TODO 自动生成的方法存根
		return this.parent;
	}

	@Override
	public Object getValue() {
		// TODO 自动生成的方法存根
		return this.grade;
	}

	@Override
	public boolean hasChildren() {
		// TODO 自动生成的方法存根
		return this.children!=null && this.children.length>0 ;
	}

	@Override
	public void setChildren(TreeNode[] children) {
		// TODO 自动生成的方法存根
		//super.setChildren(children);

		this.children = children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO 自动生成的方法存根
		//super.setParent(parent);
		this.parent = parent;
	}
	
	public String toString() {
		return this.grade.getGrade()+"";
	}	

}
