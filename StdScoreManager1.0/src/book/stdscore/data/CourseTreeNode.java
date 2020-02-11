package book.stdscore.data;

import org.eclipse.jface.viewers.TreeNode;

public class CourseTreeNode extends TreeNode {

	private Course course ;
	private TreeNode parent ;
	private TreeNode children[] ;

	public CourseTreeNode(Course value) {
		super(value);
		// TODO Auto-generated constructor stub
		this.course = value ;
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
		return this.course ;
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
		this.children = children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = parent ;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return this.course.getName();
	}
	
}
