package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;

import book.stdscore.data.ClassTreeNode;
import book.stdscore.data.Course;
import book.stdscore.data.CourseTreeNode;
import book.stdscore.data.Department;
import book.stdscore.data.DepartmentTreeNode;
import book.stdscore.data.GradeClass;
import book.stdscore.data.GradeTreeNode;
import book.stdscore.data.InitDB;
import book.stdscore.data.ScoreCourseTreeRoot;
import book.stdscore.data.Teacher;
import book.stdscore.data.User;

public class TchScoreStat {

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}

		public String getText(Object element) {
			return element.toString();
		}
	}

	private User user;
	protected Shell shell;
	private TreeViewer treeViewer;
	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TchScoreStat window = new TchScoreStat(null);
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
		new Clipboard(display);
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public TchScoreStat(User user) {
		super();
		this.user = user;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(800, 400));
		shell.setSize(450, 300);
		shell.setText("班级排课");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		treeViewer = new TreeViewer(shell, SWT.BORDER);
		treeViewer.setAutoExpandLevel(4);
		Tree tree = treeViewer.getTree();
		tree.setHeaderVisible(true);

		TreeViewerColumn treeViewerColumnTree = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnTree.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				return element == null ? "" : element.toString();
			}
		});
		TreeColumn trclmnTree = treeViewerColumnTree.getColumn();
		trclmnTree.setWidth(260);

		TreeViewerColumn treeViewerColumnCount = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnCount.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				return new StatScore(element, 1).getColumnText();
				//return element == null ? "" : element.toString();
			}
		});
		TreeColumn trclmnCount = treeViewerColumnCount.getColumn();
		trclmnCount.setWidth(100);
		trclmnCount.setText("人数");

		TreeViewerColumn treeViewerColumnAvg = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnAvg.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				//return element == null ? "" : element.toString();
				return new StatScore(element, 2).getColumnText();
			}
		});
		TreeColumn trclmnAvg = treeViewerColumnAvg.getColumn();
		trclmnAvg.setWidth(100);
		trclmnAvg.setText("平均分");

		TreeViewerColumn treeViewerColumnMax = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnMax.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				//return element == null ? "" : element.toString();
				return new StatScore(element, 3).getColumnText();
			}
		});
		TreeColumn trclmnMax = treeViewerColumnMax.getColumn();
		trclmnMax.setWidth(100);
		trclmnMax.setText("最高分");

		TreeViewerColumn treeViewerColumnMin = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnMin.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				//return element == null ? "" : element.toString();
				return new StatScore(element, 4).getColumnText();
			}
		});
		TreeColumn trclmnMin = treeViewerColumnMin.getColumn();
		trclmnMin.setWidth(100);
		trclmnMin.setText("最低分");

		TreeViewerColumn treeViewerColumnPass = new TreeViewerColumn(treeViewer, SWT.NONE);
		treeViewerColumnPass.setLabelProvider(new ColumnLabelProvider() {
			public Image getImage(Object element) {
				// TODO Auto-generated method stub
				return null;
			}
			public String getText(Object element) {
				// TODO Auto-generated method stub
				//return element == null ? "" : element.toString();
				return new StatScore(element, 5).getColumnText();
			}
		});
		TreeColumn trclmnPass = treeViewerColumnPass.getColumn();
		trclmnPass.setWidth(100);
		trclmnPass.setText("及格人数");

		//treeViewer.setLabelProvider(new ViewerLabelProvider());
		treeViewer.setContentProvider(new TreeNodeContentProvider() {
			@Override
			public Object[] getElements(Object inputElement) {
				return ((TreeNode) inputElement).getChildren();
			}
		});
		treeViewer.setInput(new ScoreCourseTreeRoot(Teacher.getFromDB(Integer.parseInt(user.getName()))));
		//treeViewer.expandAll();
/*
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
				TreeNode selNode = (TreeNode) ss.getFirstElement();
				if (selNode instanceof CourseTreeNode) {
					GradeClass aclass = (GradeClass) ((ClassTreeNode) (selNode.getParent())).getValue();
					//ArrayList<String[]> scoreList = getStdList((Course) selNode.getValue(), aclass);
					treeViewer.refresh();
				}
				
			}
		});
*/
	}
/*
	public ArrayList<String[]> getStdList(Course course, GradeClass aclass) {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		String sql = "select S.id,S.name,SC.score from student AS S,student_course AS SC where S.id=SC.studentID "
				+ "and SC.courseID=" + course.getId() + " and S.departmentId=" + aclass.getDepartmentId()
				+ " and S.grade=" + aclass.getGrade() + " and S.class=" + aclass.getcClass();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while (rs.next()) {
				scoreList.add(new String[] { rs.getInt(1) + "", rs.getString(2), rs.getFloat(3) + "" });
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return scoreList;
	}
*/
	class StatScore {
		
		private TreeNode element;
		private int column ;
		private int count;
		private float sumScore;
		private float max;
		private float min;
		private int abovePeaple;
		private int[] frequency = new int[5];

		public StatScore(Object element, int column) {
			super();
			this.element = (TreeNode)element;
			this.column = column;
		}

		public String getColumnText() {
			// TODO Auto-generated method stub
			String result = "" ;
			if (element instanceof CourseTreeNode) {
				statCourse();
				switch (column) {
					case 1:						
						result = this.count + "";
						break;
					case 2:						
						result = this.sumScore / this.count + "";
						break;
					case 3:						
						result = this.max + "";
						break;
					case 4:						
						result = this.min + "";
						break;
					case 5:						
						result = this.abovePeaple + "";
						break;					
				}		
			}
			return result;
		}

		void statCourse() {
			count = 0;
			sumScore = 0.0F;
			max = -1;
			min = 1000;
			abovePeaple = 0;
			for (int i = 0; i < frequency.length; i++) {
				frequency[i] = 0;
			}

			int courseId = ((Course)(((CourseTreeNode) element).getValue())).getId();
			ClassTreeNode ctn = (ClassTreeNode)(((CourseTreeNode) element).getParent());
			int cClass = ((GradeClass)ctn.getValue()).getcClass();
			GradeTreeNode gtn = (GradeTreeNode) ctn.getParent();
			int grade = ((GradeClass) gtn.getValue()).getGrade();
			DepartmentTreeNode dtn = (DepartmentTreeNode) gtn.getParent();
			int deptId= ((Department) dtn.getValue()).getId();
			String sql = "select COUNT(DISTINCT student.id),SUM(student_course.score),MAX(student_course.score),MIN(student_course.score)"
				+ " from student,student_course where studentID=student.id and courseID=" + courseId 
				+ " and student.departmentId=" + deptId +" and student.grade=" + grade + " and student.class=" + cClass ;
			ResultSet rs = InitDB.getInitDB().getRs(sql);
			try {
				if (rs.next()) {
					this.count = rs.getInt(1);
					this.sumScore = rs.getFloat(2);
					this.max = rs.getFloat(3);
					this.min = rs.getFloat(4);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			sql = "select COUNT(DISTINCT student.id) from student,student_course where courseID=" + courseId
					+ " and studentID=student.id and student_course.score>=60"
					+ " and student.departmentId=" + deptId +" and student.grade=" + grade + " and student.class=" + cClass ;
			rs = InitDB.getInitDB().getRs(sql);
			try {
				if (rs.next()) {
					this.abovePeaple = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
