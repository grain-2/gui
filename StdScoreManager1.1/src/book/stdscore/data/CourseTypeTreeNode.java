package book.stdscore.data;

import org.eclipse.jface.viewers.TreeNode;

public class CourseTypeTreeNode extends TreeNode {
	private String coureType;
	private TreeNode parent;
	private TreeNode children[];

	public CourseTypeTreeNode(Object value) {
		super(value);
		// TODO 自动生成的构造函数存根
		this.coureType = (String) value;
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
		return this.coureType;
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
		this.children = children ;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO 自动生成的方法存根
		//super.setParent(parent);
		this.parent = parent;
	}

	@Override
	public String toString() {
		return this.coureType;
	}

}
