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
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.Viewer;

public class TchScoreBrowse {
	private static class Sorter extends ViewerSorter {
		private static int orderId = -1;
		private static int orderName = -1 ;
		private static int orderScore = -1 ;
		private int col = 0;
		private static final Sorter sorter = new Sorter(); 
		public Sorter() {
			super();
		}
		public int getCol() {
			return col;
		}
		public void setCol(int col) {
			this.col = col;
		}
		public static Sorter getSorter() {
			return sorter;
		}
		public static int getOrderName() {
			return orderName;
		}
		public static void setOrderName(int orderName) {
			Sorter.orderName = orderName;
		}
		public static int getOrderScore() {
			return orderScore;
		}
		public static void setOrderScore(int orderScore) {
			Sorter.orderScore = orderScore;
		}
		public static int getOrderId() {
			return orderId;
		}
		public static void setOrderId(int orderId) {
			Sorter.orderId = orderId;
		}
		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			String[] item1 = (String[])e1;
			String[] item2 = (String[])e2;
			
			if(this.col==1) {				
				if(orderId>0) 
					return Long.parseLong(item1[0]) > Long.parseLong(item2[0])?1:
						(Long.parseLong(item1[0]) < Long.parseLong(item2[0])?-1:0);
				else if(orderId<0)
					return Long.parseLong(item2[0]) > Long.parseLong(item1[0])?1:
						(Long.parseLong(item2[0]) < Long.parseLong(item1[0])?-1:0);
			}
			else if(col==2) {
				if(orderName>0) 
					return item1[1].compareTo(item2[1]);
				else if(orderName<0)
					return item2[1].compareTo(item1[1]);
			}
			else if(col==3) {
				if(orderScore>0) 
					return Float.parseFloat(item1[2]) > Float.parseFloat(item2[2])?1:
						(Float.parseFloat(item1[2]) < Float.parseFloat(item2[2])?-1:0);
				else if(orderScore<0)
					return Float.parseFloat(item2[2]) > Float.parseFloat(item1[2])?1:
						(Float.parseFloat(item2[2]) < Float.parseFloat(item1[2])?-1:0);
			}
			return super.compare(viewer, e1, e2);
		}
/*
		public int compare(Viewer viewer, Object e1, Object e2) {
			String[] item1 = (String[]) e1;
			String[] item2 = (String[]) e2;
			if(Float.parseFloat(item1[2]) > Float.parseFloat(item2[2]))
				return 1;
			else if(Float.parseFloat(item1[2]) < Float.parseFloat(item2[2]))
				return -1;
			else
				return 0;
		}
*/
	}
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
	private Sorter sorter;

	public Table getTableSelected() {
		return tableSelected;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TchScoreBrowse window = new TchScoreBrowse(null);
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

	public TchScoreBrowse(User user) {
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
		tableViewer.setColumnProperties(new String[] {"id", "name", "score"});
		sorter = Sorter.getSorter();
		//tableViewer.setSorter(new Sorter());
		tableSelected = tableViewer.getTable();
		tableSelected.setHeaderVisible(true);
		tableSelected.setLinesVisible(true);
		
		TableColumn tableColumnSelID = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelID.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new Sorter());
				sorter.setCol(1);
				tableViewer.setSorter(sorter);
				tableViewer.refresh();
				Sorter.setOrderId(-Sorter.getOrderId());
			}
		});
		tableColumnSelID.setWidth(120);
		tableColumnSelID.setText("学号");
		
		TableColumn tableColumnSelName = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new Sorter());
				sorter.setCol(2);
				tableViewer.setSorter(sorter);
				tableViewer.refresh();
				Sorter.setOrderName(-Sorter.getOrderName());
			}
		});
		tableColumnSelName.setWidth(80);
		tableColumnSelName.setText("姓名");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
/*
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
*/
		TableColumn tableColumnScore = tableViewerColumn.getColumn();
		tableColumnScore.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.setSorter(new Sorter());
				sorter.setCol(3);
				tableViewer.setSorter(sorter);
				tableViewer.refresh();
				Sorter.setOrderScore(-Sorter.getOrderScore());
			}
		});
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

}
