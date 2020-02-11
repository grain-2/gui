package studentTree;

import org.eclipse.jface.viewers.TreeNode;

public class Student extends TreeNode {
	private Long id ;
	private String name;
	private String department;
	private int grade ;
	private int cClass ;
	private float score ;
	
	private CClass parent ;
	private TreeNode[] chldren ;
	
	public Student(Object value, Long id, String name, String department, int grade,
			int cClass, float score) {
		super(value);
		this.id = id;
		this.name = name;
		this.department = department;
		this.grade = grade;
		this.cClass = cClass;
		this.score = score;
		this.setChildren(null);
	}
	public Long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDepartment() {
		return department;
	}
	public int getGrade() {
		return grade;
	}
	public int getcClass() {
		return cClass;
	}
	public float getScore() {
		return score;
	}
	@Override
	public String toString() {
		return "Student [cClass=" + cClass + ", department=" + department
				+ ", grade=" + grade + ", id=" + id + ", name=" + name
				+ ", score=" + score + "]";
	}
	@Override
	public TreeNode[] getChildren() {
		// TODO Auto-generated method stub
		//return super.getChildren();
		return this.chldren ;
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
		return false ;
	}
	@Override
	public void setChildren(TreeNode[] children) {
		// TODO Auto-generated method stub
		//super.setChildren(children);
		this.chldren = null ;
	}
	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = (CClass)parent ;
	}

}
