package studentTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class Grade extends TreeNode {

	private int grade ;
	
	private Department parent ;
	private CClass[] children ;

//	private InitDB db ;
//	private ResultSet rs;
	
	public Grade(Object value, int grade) {
		super(value);
		// TODO Auto-generated constructor stub
		this.grade = grade ;
/*
		db = new InitDB();
		String sql = "select 班级  from [1班$] where 年级=" + this.grade ;
		rs = db.getRs(sql);
		try {
			ArrayList<CClass> classList = new ArrayList<CClass>();
			for(int i=0; rs.next(); i++) {
				CClass cClass = new CClass(null,rs.getInt(1)) ;
				cClass.setParent(this);
				classList.add(cClass) ;
			}
			//InitDB.getInitDB().closeDB();
			this.children = new CClass[classList.size()] ;
			for(int i=0;i<this.children.length;i++)
				this.children[i] = classList.get(i) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
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
		this.children = (CClass[])children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = (Department)parent ;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return this.grade + "级" ;
	}
}
