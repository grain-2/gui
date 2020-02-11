package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;

import book.stdscore.data.InitDB;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.wb.swt.TableViewerColumnSorter;
import org.eclipse.jface.viewers.Viewer;

public class TableViewerColumnDemo1 extends ApplicationWindow {
	private Table table;
	ArrayList<String[]> users ;

	/**
	 * Create the application window.
	 */
	public TableViewerColumnDemo1() {
		super(null);
		ResultSet rs = new InitDB().getRs("select * from users");
		users = new ArrayList();
		String[] user;
		try {
			while(rs.next()) {
				user = new String[3];
				user[0]=rs.getString(1);
				user[1]=rs.getString(2);
				user[2]=rs.getInt(3)+"";
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
			Composite composite = new Composite(container, SWT.NONE);
			TableColumnLayout tcl_composite = new TableColumnLayout();
			composite.setLayout(tcl_composite);
			{
				TableViewer tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
				tableViewer.setColumnProperties(new String[] {"name", "password", "job"});
				table = tableViewer.getTable();
				table.setHeaderVisible(true);
				table.setLinesVisible(true);
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					new TableViewerColumnSorter(tableViewerColumn) {
						@Override
						protected int doCompare(Viewer viewer, Object e1, Object e2) {
							// TODO Remove this method, if your getValue(Object) returns Comparable.
							// Typical Comparable are String, Integer, Double, etc.
							String[] item1 = (String[])e1;
							String[] item2 = (String[])e2;
							return item1[0].compareTo(item2[0]);
							// return super.doCompare(viewer, e1, e2);
						}

						@Override
						protected Object getValue(Object o) {
							// TODO remove this method, if your EditingSupport returns value
							return super.getValue(o);
						}
					};
					tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){

						@Override
						public Color getForeground(Object element) {
							// TODO 自动生成的方法存根
							if(((String[])element)[2].equals("2")){
								return new Color(null,255,0,0);
							}
							return super.getForeground(element);
						}

						@Override
						public String getText(Object element) {
							// TODO 自动生成的方法存根
							if(element instanceof String[]) {
								return super.getText(((String[])element)[0]);
							}
							return null;
						}
						
					});
					TableColumn tableColumnName = tableViewerColumn.getColumn();
					tcl_composite.setColumnData(tableColumnName, new ColumnPixelData(150, true, true));
					tableColumnName.setText("用户名");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					tableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
						protected boolean canEdit(Object element) {
							// TODO Auto-generated method stub
							return true;
						}
						protected CellEditor getCellEditor(Object element) {
							// TODO Auto-generated method stub
							return new TextCellEditor(table);
						}
						protected Object getValue(Object element) {
							// TODO Auto-generated method stub
							return ((String[])element)[1];
						}
						protected void setValue(Object element, Object value) {
							// TODO Auto-generated method stub
							((String[])element)[1]=(String)value;
							tableViewer.update(element, (String[])tableViewer.getColumnProperties());
						}
					});
					tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public Color getBackground(Object element) {
							// TODO 自动生成的方法存根
							if(((String[])element)[2].equals("0")){
								return new Color(null,255,255,0);
							}							
							return super.getBackground(element);
						}

						@Override
						public String getText(Object element) {
							// TODO 自动生成的方法存根
							String[] row = (String[])element;
							return super.getText(row[1]);
						}						
					});
					TableColumn tableColumnPassword = tableViewerColumn.getColumn();
					tcl_composite.setColumnData(tableColumnPassword, new ColumnPixelData(150, true, true));
					tableColumnPassword.setText("密码");
				}
				{
					TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
					tableViewerColumn.setEditingSupport(new UserJobEditor(tableViewer));
					tableViewerColumn.setLabelProvider(new ColumnLabelProvider(){
						@Override
						public Font getFont(Object element) {
							// TODO 自动生成的方法存根
							if(((String[])element)[2].equals("1")){
								return new Font(null, "黑体", 12, SWT.BOLD);
							}							
							return super.getFont(element);
						}

						@Override
						public String getText(Object element) {
							// TODO 自动生成的方法存根
							String[] row = (String[])element;
							String jobStr = null;
							switch (row[2]) {
								case "0":
									jobStr = "学生";
									break;
								case "1":
									jobStr = "教师";
									break;
								case "2":
									jobStr = "管理员";
									break;
							}
							return jobStr;
						}

					});
					TableColumn tableColumnJob = tableViewerColumn.getColumn();
					tcl_composite.setColumnData(tableColumnJob, new ColumnPixelData(150, true, true));
					tableColumnJob.setText("身份");
				}
				tableViewer.setContentProvider(new ArrayContentProvider());
				tableViewer.setInput(users);
			}
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
			TableViewerColumnDemo1 window = new TableViewerColumnDemo1();
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
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

}
