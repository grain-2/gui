package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.widgets.TableColumn;

import book.stdscore.data.ClassTreeNode;
import book.stdscore.data.Course;
import book.stdscore.data.CourseTreeNode;
import book.stdscore.data.Department;
import book.stdscore.data.DepartmentTreeNode;
import book.stdscore.data.GradeClass;
import book.stdscore.data.GradeTreeNode;
import book.stdscore.data.InitDB;
import book.stdscore.data.SelCourseTreeRoot;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

public class TableTreeDemo1 {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(columnIndex==0)
				return element.toString();
			if(columnIndex==1) {
				if(element instanceof CourseTreeNode) {
					int courseId = ((Course)(((CourseTreeNode) element).getValue())).getId();
					ClassTreeNode ctn = (ClassTreeNode)(((CourseTreeNode) element).getParent().getParent());
					int cClass = ((GradeClass)ctn.getValue()).getcClass();
					GradeTreeNode gtn = (GradeTreeNode) ctn.getParent();
					int grade = ((GradeClass) gtn.getValue()).getGrade();
					DepartmentTreeNode dtn = (DepartmentTreeNode) gtn.getParent();
					int deptId= ((Department) dtn.getValue()).getId();
					String sql= "select count(student_course.id) from student,student_course where student.departmentId=" +
							deptId +" and student.grade=" + grade + " and student.class=" + cClass + 
							" and student_course.studentID=student.id and  student_course.courseID=" + courseId;
					
					ResultSet rs = InitDB.getInitDB().getRs(sql);
					int num = 0 ;		
					try {
						if(rs.next())
							num = rs.getInt(1);
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					return num+"";
				}
				return "";
			}
			return "";
		}
	}
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object inputElement) {
			//return getChildren(inputElement);
			return ((TreeNode) inputElement).getChildren();
		}
		public Object[] getChildren(Object parentElement) {
			//return new Object[] { "item_0", "item_1", "item_2" };
			return ((TreeNode) parentElement).getChildren();
		}
		public Object getParent(Object element) {
			return null;
		}
		public boolean hasChildren(Object element) {
			//return getChildren(element).length > 0;
			return ((TreeNode) element).hasChildren();
		}
	}

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableTreeDemo1 window = new TableTreeDemo1();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TableTreeViewer tableTreeViewer = new TableTreeViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		TableTree tableTree = tableTreeViewer.getTableTree();
		tableTree.getTable().setLinesVisible(true);
		tableTree.getTable().setHeaderVisible(true);
		
		TableColumn tblclmnNewColumn = new TableColumn(tableTree.getTable(), SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(tableTree.getTable(), SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("选课学生人数");
		tableTreeViewer.setLabelProvider(new TableLabelProvider());
		tableTreeViewer.setContentProvider(new TreeContentProvider());
		tableTreeViewer.setInput(new SelCourseTreeRoot());

	}

}
