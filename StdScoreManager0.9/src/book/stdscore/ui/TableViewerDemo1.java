package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import book.stdscore.data.InitDB;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class TableViewerDemo1 extends ApplicationWindow {

	private static class Sorter extends ViewerSorter {
		private static int orderName = -1;
		private static int orderPass = -1;
		private static int orderJob = -1;
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

		public static int getOrderPass() {
			return orderPass;
		}

		public static void setOrderPass(int orderPass) {
			Sorter.orderPass = orderPass;
		}

		public static int getOrderJob() {
			return orderJob;
		}

		public static void setOrderJob(int orderJob) {
			Sorter.orderJob = orderJob;
		}

		@Override
		public int compare(Viewer viewer, Object e1, Object e2) {
			String[] item1 = (String[]) e1;
			String[] item2 = (String[]) e2;

			if (this.col == 1) {
				if (orderName > 0)
					return item1[0].compareTo(item2[0]);
				else if (orderName < 0)
					return item2[0].compareTo(item1[0]);
			} else if (col == 2) {
				if (orderPass > 0)
					return item1[1].compareTo(item2[1]);
				else if (orderPass < 0)
					return item2[1].compareTo(item1[1]);
			} else if (col == 3) {
				if (orderJob > 0)
					return item1[2].compareTo(item2[2]);
				else if (orderJob < 0)
					return item2[2].compareTo(item1[2]);
			}
			return super.compare(viewer, e1, e2);
		}
		/*
		 * public int compare(Viewer viewer, Object e1, Object e2) { String[]
		 * item1 = (String[])e1; String[] item2 = (String[])e2; return
		 * item1[0].compareTo(item2[0]); }
		 */
	}

	private DataBindingContext m_bindingContext;
	private Table table;
	ArrayList<String[]> users;
	private TableViewer tableViewer;
	private Sorter sorter;
	private Action action;
	private Action action_1;
	private Action action_2;
	private Action actionPlus;
	private Action actionSub;
	private Action actionNextline;
	private Action actionPrevLine;
	private static int index;
	private static int seleRow ;

	/**
	 * Create the application window.
	 */
	public TableViewerDemo1() {
		super(null);
		ResultSet rs = new InitDB().getRs("select * from users");
		users = new ArrayList();
		String[] user;
		try {
			while (rs.next()) {
				user = new String[3];
				user[0] = rs.getString(1);
				user[1] = rs.getString(2);
				user[2] = rs.getInt(3) + "";
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
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			// tableViewer.setSorter(new Sorter());
			sorter = Sorter.getSorter();
			tableViewer = new TableViewer(container, SWT.BORDER | SWT.CHECK | SWT.FULL_SELECTION | SWT.MULTI);
			tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
				public void selectionChanged(SelectionChangedEvent arg0) {
					IStructuredSelection selItems = (IStructuredSelection) arg0.getSelection();
					Iterator it = selItems.iterator();
					String eStr = "";
					Object obj = null;
					String[] user = null;
					while (it.hasNext()) {
						obj = it.next();
						if (obj instanceof String[]) {
							user = (String[]) obj;
							eStr += user[0] + "\t" + user[1] + "\t" + user[2] + "\n";
						}
					}
					MessageDialog.openInformation(getParentShell(), "你所选择的行的内容是", eStr);

				}
			});
			table = tableViewer.getTable();
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			{
				TableColumn tableColumnName = new TableColumn(table, SWT.NONE);
				tableColumnName.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sorter.setCol(1);
						tableViewer.setSorter(sorter);
						tableViewer.refresh();
						Sorter.setOrderName(-Sorter.getOrderName());

					}
				});
				tableColumnName.setWidth(100);
				tableColumnName.setText("用户名");
			}
			{
				TableColumn tableColumnPassword = new TableColumn(table, SWT.NONE);
				tableColumnPassword.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sorter.setCol(2);
						tableViewer.setSorter(sorter);
						tableViewer.refresh();
						Sorter.setOrderPass(-Sorter.getOrderPass());
					}
				});
				tableColumnPassword.setWidth(100);
				tableColumnPassword.setText("密码");
			}
			{
				TableColumn tableColumnJob = new TableColumn(table, SWT.NONE);
				tableColumnJob.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						sorter.setCol(3);
						tableViewer.setSorter(sorter);
						tableViewer.refresh();
						Sorter.setOrderJob(-Sorter.getOrderJob());
					}
				});
				tableColumnJob.setWidth(100);
				tableColumnJob.setText("身份");
			}
			tableViewer.setLabelProvider(new UserLabelProvider());
			tableViewer.setContentProvider(new ArrayContentProvider());
			// tableViewer.addFilter(new FilterJob());
			tableViewer.setInput(users);

		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			action = new Action("查找用户") {
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog id = new InputDialog(getParentShell(), "输入用户名", "请输入要查找的用户名。", "", null);
					int btn = id.open();
					String name = id.getValue();
					if (btn == 0 && !"".equals(name.trim())) {
						ViewerFilter[] filters = tableViewer.getFilters();
						for (ViewerFilter vft : filters) {
							tableViewer.removeFilter(vft);
						}
						tableViewer.addFilter(new FilterName(name));
					}
				}

			};
		}
		{
			action_1 = new Action("显示全部") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					ViewerFilter[] filters = tableViewer.getFilters();
					for (ViewerFilter vft : filters) {
						tableViewer.removeFilter(vft);
					}
				}

			};
		}
		{
			action_2 = new Action("筛选教师") {
				FilterJob filterJob = new FilterJob(1);

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();

					Object[] userSels = filterJob.filter(tableViewer, users, users.toArray());
					for (Object usr : userSels) {
						String[] strs = (String[]) usr;
						System.out.println("用户名：" + strs[0] + ",密码：" + strs[1] + ",身份：" + strs[2]);
					}

				}

			};
		}
		{
			actionPlus = new Action("+") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog input = new InputDialog(getParentShell(),"输入用户", "输入用户名：", "", null);
					InputDialog input1 = new InputDialog(getParentShell(),"输入用户", "输入密码：", "", null);
					InputDialog input2 = new InputDialog(getParentShell(), "输入用户", "输入用户身份(数字：0-学生、1-教师、2-管理员 )：", "", null);
					String name=null,pass=null,job=null;
					if(input.open()==InputDialog.OK)
						name = input.getValue();
					if(input1.open()==InputDialog.OK)
						pass = input1.getValue();
					if(input2.open()==InputDialog.OK)
						job = input2.getValue();
					if(name==null || pass==null || job==null ||	"".equals(name)||"".equals(pass) || "".equals(job)) {
						MessageDialog.openError(getParentShell(), "数据不能为空","用户名和密码不能为空，身份必须是数字：0-学生、1-教师、2-管理员");
					} else {
						String[] user = new String[]{name,pass,job};
						users.add(user) ;
						tableViewer.add((Object)user);
					}

				}
			};
		}
		{
			actionSub = new Action("-") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					Iterator it ;
					IStructuredSelection seleElement = (IStructuredSelection)tableViewer.getSelection() ;
					if(seleElement.isEmpty()) {
						MessageDialog.openError(getParentShell(), "没有选择要删除的行", "请首先选择要删除的行，然后点击。");
						return ;
					} else {
						tableViewer.remove(seleElement.toArray());
						it = seleElement.iterator() ;
						while(it.hasNext())
							users.remove(it.next());
					}
				}
			};
		}
		{
			actionNextline = new Action("->") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					Object[] usersArr = users.toArray() ;
					CheckboxTableViewer cv = new CheckboxTableViewer(table);
					Object[] objs = cv.getCheckedElements() ;
					if(objs.length==usersArr.length)
						MessageDialog.openConfirm(getParentShell(), "已全部选择","所有行都被选择，无须再选。");
					else {
						if(objs.length==0)
							index=0;
						else {
							for(Object obj : objs) {
								if(obj instanceof String[]){
									for(int i = 0;i<usersArr.length;i++)
										if(usersArr[i].equals(obj)) {
											index = i;
											break;
										}
								}
							}
						}
						while(true) {
							if(cv.getChecked(usersArr[index])) {
								if(index<usersArr.length-1)
									index++;
								else
									index=0;
							}else
								break;
						}
						cv.setChecked(usersArr[index], true);
					}

				}
			};
		}
		{
			actionPrevLine = new Action("<-") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					int[] arr = table.getSelectionIndices();
					if(arr!=null && arr.length>0) {
						Arrays.sort(arr);
						seleRow = arr[0] ;
					} else {
						return;
					}
					if(seleRow>0)
						seleRow--;
					else
						seleRow = table.getItemCount()-1;
					table.setSelection(seleRow) ;
					String[] userStr = (String[])table.getSelection()[0].getData() ;
					System.out.println(userStr[0]+","+userStr[1]+","+userStr[2]);
				}
			};
		}
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * 
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		toolBarManager.add(action);
		toolBarManager.add(action_1);
		toolBarManager.add(action_2);
		toolBarManager.add(actionPlus);
		toolBarManager.add(actionSub);
		toolBarManager.add(actionPrevLine);
		toolBarManager.add(actionNextline);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Display display = Display.getDefault();
		Realm.runWithDefault(SWTObservables.getRealm(display), new Runnable() {
			public void run() {
				try {
					TableViewerDemo1 window = new TableViewerDemo1();
					window.setBlockOnOpen(true);
					window.open();
					Display.getCurrent().dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Configure the shell.
	 * 
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

	class FilterJob extends ViewerFilter { // 过滤身份字段
		private int job;

		public FilterJob(int job) {
			super();
			this.job = job;
		}

		public boolean select(Viewer arg0, Object arg1, Object arg2) {
			String[] item = (String[]) arg2;
			if (item[2].equals(job + ""))
				return true;
			return false;
		}

	}

	class FilterName extends ViewerFilter { // 过滤用户名，显示的是name用户
		String name;

		public FilterName(String name) {
			super();
			this.name = name;
		}

		public boolean select(Viewer arg0, Object arg1, Object arg2) {
			String[] item = (String[]) arg2;
			if (((String[]) arg2)[0].equals(name))
				return true;

			return false;
		}

	}

}
