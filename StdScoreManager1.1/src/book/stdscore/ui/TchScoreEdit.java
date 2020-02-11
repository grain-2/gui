package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import book.stdscore.data.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;

public class TchScoreEdit {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			String[] score = (String[])element;
			if(columnIndex==0) {
				return score[0];
			} else if(columnIndex==1) {
				return score[1];
			} else if(columnIndex==2) {
				return score[2];
			}
			return element.toString();
		}
	}
	private static class ViewerLabelProvider_2 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Teacher)element).getName();
		}
	}
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Department)element). getName();
		}
	}
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
	private Table tableSelected;
	private Clipboard clipboard;
	private TreeViewer treeViewer;
	private Table table;

	public Table getTableSelected() {
		return tableSelected;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TchScoreEdit window = new TchScoreEdit(null);
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
		clipboard = new Clipboard(display);
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public TchScoreEdit(User user) {
		super();
		this.user = user;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(600, 400));
		shell.setSize(450, 300);
		shell.setText("班级排课");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		treeViewer = new TreeViewer(sashForm, SWT.BORDER);
		treeViewer.setUseHashlookup(true);
		Tree tree = treeViewer.getTree();
		treeViewer.setLabelProvider(new ViewerLabelProvider());

		treeViewer.setContentProvider(new TreeNodeContentProvider(){
			@Override
			public Object[] getElements(Object inputElement) {
				return ((TreeNode) inputElement).getChildren();
			}
		});
		
		treeViewer.setInput(new ScoreCourseTreeRoot(Teacher.getFromDB(Integer.parseInt(user.getName()))));		

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableSelected = tableViewer.getTable();
		tableSelected.setHeaderVisible(true);
		tableSelected.setLinesVisible(true);
		
		TableColumn tableColumnSelID = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelID.setWidth(120);
		tableColumnSelID.setText("学号");
		
		TableColumn tableColumnSelName = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelName.setWidth(80);
		tableColumnSelName.setText("姓名");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
		tableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
			protected boolean canEdit(Object element) {
				// TODO Auto-generated method stub
				return true;
			}
			protected CellEditor getCellEditor(Object element) {
				// TODO Auto-generated method stub
				return new TextCellEditor(tableSelected);
			}
			protected Object getValue(Object element) {
				// TODO Auto-generated method stub
				return ((String[])element)[2];
			}
			protected void setValue(Object element, Object value) {
				// TODO Auto-generated method stub
				((String[])element)[2]=(String)value;
				tableViewer.update(element, (String[])tableViewer.getColumnProperties());
				// 更新数据库表 student_course中的score字段及updatetime字段
				updateScore(Long.parseLong(((String[])element)[0]),Float.parseFloat((String)value));
			}
		});
		TableColumn tableColumnScore = tableViewerColumn.getColumn();
		tableColumnScore.setWidth(100);
		tableColumnScore.setText("成绩");

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
				TreeNode selNode = (TreeNode) ss.getFirstElement();
				if(selNode instanceof CourseTreeNode) {
					GradeClass aclass = (GradeClass) ((ClassTreeNode)(selNode.getParent())).getValue();
					ArrayList<String[]> scoreList = getStdList((Course)selNode.getValue(),aclass);
					tableViewer.setInput(scoreList);
					tableViewer.refresh();
					/*
					tableSelected.removeAll();
					TableItem item;
					for(String[] strArr : scoreList) {
						item = new TableItem(tableSelected, SWT.NONE);
						item.setText(strArr);			
					}
					*/
				}
			}
		});
				
		sashForm.setWeights(new int[] {1, 2});

	}
	
	public ArrayList<String[]> getStdList(Course course, GradeClass aclass) {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		String sql = "select S.id,S.name,SC.score from student AS S,student_course AS SC where S.id=SC.studentID " +
				"and SC.courseID=" + course.getId() + " and S.departmentId=" + aclass.getDepartmentId() +
				" and S.grade=" + aclass.getGrade() +" and S.class=" + aclass.getcClass();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				scoreList.add(new String[]{rs.getInt(1) + "",rs.getString(2),rs.getFloat(3)+""});
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return scoreList;
	}
/*	
	ArrayList<Teacher> getTchList(Department dept) {
		ArrayList<Teacher> tchList = new ArrayList<Teacher>();
		String sql = "select * from teacher where departmentID="+dept.getId();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				Teacher teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),dept,rs.getString(6),rs.getString(7),rs.getString(8)) ;
				tchList.add(teacher);
			}
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return tchList;
	}

	static Department[] getDepartments() {
		String sql = "select * from department";
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		ArrayList<Department> depts = new ArrayList<Department>();
		try {
			while(rs.next()) {
				depts.add(new Department(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return depts.toArray(new Department[depts.size()]);
	}
	
	void dgc_courseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		ClassTreeNode classNode = (ClassTreeNode) ((CourseTypeTreeNode)courseNode.getParent()).getParent();
		GradeTreeNode gradeNode = (GradeTreeNode) classNode.getParent();
		DepartmentTreeNode deptNode = (DepartmentTreeNode) gradeNode.getParent();
		int courseID = ((Course)courseNode.getValue()).getId();
		int deptID = ((Department)deptNode.getValue()).getId();
		int grade = ((GradeClass)gradeNode.getValue()).getGrade();
		int aclass = ((GradeClass)classNode.getValue()).getcClass();
		int dgcID ;
		String sql="select id from department_grade_class where departmentID=" + deptID +
				" and grade=" +grade +" and class=" +aclass;
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		try {
			if(rs.next()) {
				dgcID = rs.getInt(1);
				sql = "select * from dgc_course where dgcID=" + dgcID +
						" and courseID=" + courseID;
				rs = db.getRs(sql);
				if(!rs.next()) {
					sql = "insert into dgc_course (dgcID,courseID) values(" + dgcID + "," + courseID +")" ;
					db.getStmt().executeUpdate(sql);
				}
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	void tchCourseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		ClassTreeNode classNode = (ClassTreeNode) ((CourseTypeTreeNode)courseNode.getParent()).getParent();
		GradeTreeNode gradeNode = (GradeTreeNode) classNode.getParent();
		DepartmentTreeNode deptNode = (DepartmentTreeNode) gradeNode.getParent();
		int courseID = ((Course)courseNode.getValue()).getId();
		int deptID = ((Department)deptNode.getValue()).getId();
		int grade = ((GradeClass)gradeNode.getValue()).getGrade();
		int aclass = ((GradeClass)classNode.getValue()).getcClass();
		int dgcID ;
		
		ss = (StructuredSelection) comboViewerName.getStructuredSelection();
		Teacher teacher = (Teacher) ss.getFirstElement();		
		int teacherID = teacher.getId();
		
		String sql="select id from teacher_course where departmentID=" + deptID +
				" and grade=" +grade +" and class=" +aclass + " and courseID=" + courseID;
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		try {
			if(!rs.next()) {
				sql = "insert into teacher_course (teacherID,courseID,departmentID,grade,class) values(" + 
						teacherID + "," + courseID + "," + deptID + "," + grade + "," + aclass + ")" ;
				db.getStmt().executeUpdate(sql);	
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	void stdCourseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		int courseID = ((Course)courseNode.getValue()).getId();
		
		TableItem[] items = tableSelected.getItems();
		long stdID;

		for(int i=0; i<items.length; i++) {
			stdID = Long.parseLong(items[i].getText(0));
			String sql="select * from student_course where studentID=" + stdID + " and courseID=" + courseID;
			InitDB db = InitDB.getInitDB();
			ResultSet rs = db.getRs(sql);
			try {
				if(!rs.next()) {
					Date date  = new Date();
					Timestamp tt = new Timestamp(date.getTime());					
					sql = "insert into student_course (studentID,courseID,updatetime) values(" +
							stdID + "," + courseID + ",'" + tt + "')" ;
					db.getStmt().executeUpdate(sql);	
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}		
		}
	}
*/	
	void updateScore(long stdID,float score) {
		// Long.parseLong(((String[])element)[0]),courseID,Float.parseFloat((String)value)
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		int courseID = ((Course)courseNode.getValue()).getId();
		Date date = new Date();
		Timestamp tt = new Timestamp(date.getTime());
		String sql = "update student_course set score=" + score +",updatetime='" + tt +
				"' where studentID=" + stdID +" and courseID=" +courseID ;
		try {
			InitDB.getInitDB().getStmt().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
