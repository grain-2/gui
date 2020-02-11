package book.stdscore.data;

import org.eclipse.jface.viewers.TreeNode;

public class GradeClassTreeNode extends TreeNode {
	
	private GradeClass gradeClass ;
	private TreeNode parent ;
	private TreeNode[] children ;

	public GradeClassTreeNode(GradeClass value) {
		super(value);
		// TODO Auto-generated constructor stub
		this.gradeClass = value ;
	}

	public GradeClass getGradeClass() {
		return gradeClass;
	}

	public void setGradeClass(GradeClass gradeClass) {
		this.gradeClass = gradeClass;
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
		this.children = children ;
	}

	@Override
	public void setParent(TreeNode parent) {
		// TODO Auto-generated method stub
		//super.setParent(parent);
		this.parent = parent ;
	}

	@Override
	public String toString() {
		return this.gradeClass.getGrade() + "��" + this.gradeClass.getcClass() + "��";
	}

}
