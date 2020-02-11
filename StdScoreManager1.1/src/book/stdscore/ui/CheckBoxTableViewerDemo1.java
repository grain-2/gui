package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.widgets.TableColumn;

import book.stdscore.data.InitDB;

import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.CheckStateChangedEvent;

public class CheckBoxTableViewerDemo1 extends ApplicationWindow {
	private Table table;
	ArrayList<String[]> users ;

	/**
	 * Create the application window.
	 */
	public CheckBoxTableViewerDemo1() {
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
			CheckboxTableViewer checkboxTableViewer = CheckboxTableViewer.newCheckList(container, SWT.BORDER | SWT.FULL_SELECTION);
			checkboxTableViewer.addCheckStateListener(new ICheckStateListener() {
				public void checkStateChanged(CheckStateChangedEvent arg0) {
					System.out.println(((String[])arg0.getElement())[0]);
				}
			});
			table = checkboxTableViewer.getTable();
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(160);
				tableColumn.setText("用户名");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(100);
				tableColumn.setText("密码");
			}
			{
				TableViewerColumn tableViewerColumn = new TableViewerColumn(checkboxTableViewer, SWT.NONE);
				TableColumn tableColumn = tableViewerColumn.getColumn();
				tableColumn.setWidth(100);
				tableColumn.setText("身份");
			}
			checkboxTableViewer.setLabelProvider(new UserLabelProvider());
			checkboxTableViewer.setContentProvider(new ArrayContentProvider());
			checkboxTableViewer.setInput(users);
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
			CheckBoxTableViewerDemo1 window = new CheckBoxTableViewerDemo1();
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
