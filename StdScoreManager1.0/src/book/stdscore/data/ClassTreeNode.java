package book.stdscore.data;

import org.eclipse.jface.viewers.TreeNode;

public class ClassTreeNode extends TreeNode {
	private GradeClass cClass;
	private TreeNode parent;
	private TreeNode children[];
	
	public ClassTreeNode(Object value) {
		super(value);
		// TODO 自动生成的构造函数存根
		this.cClass = (GradeClass) value;
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
		return this.cClass;
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
/*
		CourseTypeTreeNode[] ctypenodes = new CourseTypeTreeNode[]{new CourseTypeTreeNode("公共基础课"),
				new CourseTypeTreeNode("专业基础课"),new CourseTypeTreeNode("专业选修课")};
		
		this.children = ctypenodes;
		*/
		this.children = children;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO 自动生成的方法存根
		//super.setParent(parent);
		this.parent = parent;
	}

	@Override
	public String toString() {
		return this.cClass.getcClass()+"";
	}

}
