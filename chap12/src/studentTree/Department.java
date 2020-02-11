package studentTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

//import edu.hyedu.stdscoredata.Course;

public class Department extends TreeNode {
	private String name ;
	
	private TreeNode parent ;
	private Grade[] children ;

//	private InitDB db ;
//	private ResultSet rs;

	private ArrayList<Grade> gradeList;

	public Department(Object value,String name) {
		super(value);
		// TODO Auto-generated constructor stub
		this.name = name;
/*
		db = new InitDB();
		String sql = "select 年级  from [1班$] where 专业='" + this.name +"' group by 年级" ;
		rs = db.getRs(sql);
		try {
			gradeList = new ArrayList<Grade>();
			while(rs.next()) {
				Grade grade = new Grade(null,rs.getInt(1));
				grade.setParent(this);
				gradeList.add(grade) ;
			}
			//InitDB.getInitDB().closeDB();
			this.children = new Grade[gradeList.size()] ;
			for(int i=0;i<this.children.length;i++)
				this.children[i] = gradeList.get(i) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}
	
	public String getName() {
		return name;
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
		return this.parent ;
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
		this.children = (Grade[])children ;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = parent ;
	}

	@Override
	public String toString() {
		return this.name ;
	}
}
