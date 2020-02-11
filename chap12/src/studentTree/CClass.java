package studentTree;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeNode;

public class CClass extends TreeNode {

	private int cClass;

	private Grade parent;
	private Student[] children;

//	private InitDB db;
//	private ResultSet rs;

	public CClass(Object value, int cClass) {
		super(value);
		// TODO Auto-generated constructor stub
		this.cClass = cClass;
/*
		String sql = "select * from [1°à$] where °à¼¶=" + this.cClass ;
		db = new InitDB();
		rs = db.getRs(sql);
		try {
			ArrayList<Student> stdList = new ArrayList<Student>();

			while(rs.next()) {
				Student student = new Student(null, rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getInt(5),rs.getFloat(6)) ;
				student.setParent(this) ;
				stdList.add(student);
			}
			//InitDB.getInitDB().closeDB();
			this.children = new Student[stdList.size()];
			for(int i=0;i<this.children.length;i++)
				this.children[i] = stdList.get(i);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/
	}

	public int getcClass() {
		return cClass;
	}

	public void setcClass(int cClass) {
		this.cClass = cClass;
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
		
		//return this.children!=null && this.children.length>0 ;
		return false ;
	}

	@Override
	public void setChildren(TreeNode[] children) {
		// TODO Auto-generated method stub
		// super.setChildren(children);
		this.children = (Student[])children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		// super.setParent(parent);
		this.parent = (Grade)parent;
	}

	@Override
	public String toString() {
		return this.cClass + "°à" ;
	}

}
