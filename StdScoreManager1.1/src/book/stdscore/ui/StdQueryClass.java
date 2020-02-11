package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import book.stdscore.data.*;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

public class StdQueryClass extends ApplicationWindow {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			Student std = (Student) element;
			if(columnIndex==0) {
				return std.getId()+"" ;
			} else if(columnIndex==1) {
				return std.getName();
			} else if(columnIndex==2) {
				return std.getDepartment().getName();
			} else if(columnIndex==3) {
				return std.getGrade()+"";
			} else if(columnIndex==4) {
				return std.getcClass()+"";
			} else if(columnIndex==5) {
				return std.getInterested();
			}
			
			return element.toString();
		}
	}
	private Table table;
	private TableViewer tableViewer;
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	/**
	 * Create the application window.
	 */
	public StdQueryClass() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			SashForm sashForm = new SashForm(container, SWT.NONE);
			{
				TreeViewer treeViewer = new TreeViewer(sashForm, SWT.BORDER);
				treeViewer.setUseHashlookup(true);
				Tree tree = treeViewer.getTree();
				{
					tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
					table = tableViewer.getTable();
					table.setLinesVisible(true);
					table.setHeaderVisible(true);
					{
						TableColumn tableColumn = new TableColumn(table, SWT.NONE);
						tableColumn.setWidth(100);
						tableColumn.setText("学号");
					}
					{
						TableColumn tableColumn = new TableColumn(table, SWT.NONE);
						tableColumn.setWidth(100);
						tableColumn.setText("姓名");
					}
					{
						TableColumn tableColumn = new TableColumn(table, SWT.NONE);
						tableColumn.setWidth(120);
						tableColumn.setText("专业");
					}
					{
						TableColumn tableColumn = new TableColumn(table, SWT.NONE);
						tableColumn.setWidth(60);
						tableColumn.setText("年级");
					}
					{
						TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
						tblclmnNewColumn.setWidth(60);
						tblclmnNewColumn.setText("班级");
					}
					{
						TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
						tblclmnNewColumn_1.setWidth(200);
						tblclmnNewColumn_1.setText("兴趣");
					}
					tableViewer.setLabelProvider(new TableLabelProvider());
					tableViewer.setContentProvider(new ArrayContentProvider());
				}
				treeViewer.setLabelProvider(new ViewerLabelProvider());

				treeViewer.setContentProvider(new TreeNodeContentProvider(){
					@Override
					public Object[] getElements(Object inputElement) {
						return ((TreeNode) inputElement).getChildren();
					}
				});
				
				treeViewer.setInput(new SelClassTreeRoot());
				treeViewer.addDoubleClickListener(new IDoubleClickListener() {
					public void doubleClick(DoubleClickEvent arg0) {
						StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection() ;
						TreeNode selNode = (TreeNode) ss.getFirstElement();
						if(selNode instanceof ClassTreeNode) {
							GradeClass aclass = (GradeClass)selNode.getValue();
							tableViewer.setInput(getStdList(aclass));
							tableViewer.refresh();
						}
					}
				});
			}
			sashForm.setWeights(new int[] {1, 2});
		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			StdQueryClass window = new StdQueryClass();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("班级学生表");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	ArrayList<Student> getStdList(GradeClass aclass) {
		ArrayList<Student> stdList = new ArrayList<Student>();
		ResultSet rs = InitDB.getInitDB().getRs("select * from student where departmentID=" + 
				aclass.getDepartmentId() + " and grade=" + aclass.getGrade() + " and class=" + aclass.getcClass()) ;
		try {
			while(rs.next()) {
				Student student = new Student(rs.getLong(1),rs.getString(2),Department.getFromDB(rs.getInt(3)),
						rs.getInt(4),rs.getInt(5),rs.getString(6),rs.getString(7)) ;
				stdList.add(student) ;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}						

		return stdList;
	}
	
}
