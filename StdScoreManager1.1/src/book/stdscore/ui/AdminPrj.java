package book.stdscore.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.action.Action;
import book.stdscore.data.*;

public class AdminPrj extends ApplicationWindow {
	private Shell shell;
	private User user;
	private Action actionDeptMana;
	private Action actionGradeClassMana;
	private Action actionCourseMana;
	private Action actionUserRegister;
	private Action actionGradeClassCourse;
	private Action actionExit;
	private Action actionStdRegister;
	private Action actionTchRegister;
	private Action actionStdQueryID;
	private Action actionStdQueryClass;
	private Action actionTchQueryID;
	private Action actionTchQueryName;
	private Action actionTchQueryDept;
	private Action actionStdBrowse;
	private Action actionStdSelCourse;
	private Action actionModifyPassword;
	private Action actionStdDel;
	private Action actionTchDel;
	private Action actionDataBackup;
	private Action actionDataRestore;
	private Action actionHelpContent;
	private Action actionUsageManual;
	private Action actionAbout;

	/**
	 * Create the application window.
	 */
	public AdminPrj(User user) {
		super(null);
		this.user = user;
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

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			actionDeptMana = new Action("专业管理") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new DepartmentManager(shell,SWT.None).open();
				}				
			};
		}
		{
			actionGradeClassMana = new Action("班级设置") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new GradeClassAdd(shell).open();
				}
			};
		}
		{
			actionCourseMana = new Action("课程管理") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new CourseManager(shell, SWT.None).open();
				}
			};
		}
		{
			actionUserRegister = new Action("用户注册") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new RegisterTabFolder().open();
				}				
			};
		}
		{
			actionGradeClassCourse = new Action("班级排课") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new AssigntCourses().open();
				}				
			};
		}
		{
			actionExit = new Action("退出\tAlt+X") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					shell.dispose();
					System.exit(0);
				}
			};
			actionExit.setAccelerator(SWT.ALT | 'X');
		}
		{
			actionStdRegister = new Action("学生注册") {
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new StdRegister().open();
					//rtf.open();
					//rtf.getTabFolder().setSelection(rtf.getTabItemStd());					
				}

			};
		}
		{
			actionTchRegister = new Action("教师注册") {
				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchRegister().open();
				}

			};
		}
		{
			actionStdQueryID = new Action("按学号查询") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog idl = new InputDialog(shell, "输入学号", "请输入要查找学生的学号", "", null);
					if(idl.open()==InputDialog.OK) {
						String idStr = idl.getValue();
						if(idStr!=null && !"".equals(idStr)) {
							int id = Integer.parseInt(idStr);
							Student std = Student.getFromDB(id);
							if(std!=null) {
								new StdRegister(std).open();
							} else {
								MessageDialog.openWarning(shell, "查不到该学号的学生", "无此学号的学生信息，或者学号输入不正确。");
							}
						} else {
							MessageDialog.openError(shell, "没有输入学号", "必须输入正确的学号。学号由数字组成！");
						}
					}
				}
			};
		}
		{
			actionStdQueryClass = new Action("按班级查询") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new StdQueryClass().open();
				}
			};
		}
		{
			actionTchQueryID = new Action("按工号") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog idl = new InputDialog(shell, "输入工号", "请输入要查找教师的工号", "", null);
					if(idl.open()==0) {
						String idStr = idl.getValue();
						if(idStr!=null && !"".equals(idStr)) {
							int id = Integer.parseInt(idStr);
							Teacher tch = Teacher.getFromDB(id);
							if(tch!=null) {
								new TchRegister(tch).open();
							} else {
								MessageDialog.openWarning(shell, "查不到该工号的教师", "无此工号的教师信息，或者工号输入不正确。");
							}
						} else {
							MessageDialog.openError(shell, "没有输入工号", "必须输入正确的工号。工号由数字组成！");
						}
					}
				}
			};
		}
		{
			actionTchQueryName = new Action("按姓名") {				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog idl = new InputDialog(shell, "输入教师姓名", "请输入要查找教师的姓名", "", null);
					if(idl.open()==0) {
						String nameStr = idl.getValue();
						if(nameStr!=null && !"".equals(nameStr)) {
							ResultSet rs = new InitDB().getRs("select * from teacher where name='" + nameStr +"'") ;
							try {
								if(rs.next()) {
									Department department = Department.getFromDB(rs.getInt(5));
									Teacher tch = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),department,rs.getString(6),rs.getString(7),rs.getString(8)) ;
									if(tch!=null) {
										new TchRegister(tch).open();
									} else {
										MessageDialog.openWarning(shell, "查不到该教师", "无此教师信息，或者姓名输入不正确。");
									}
								} else 
									MessageDialog.openWarning(shell, "查不到该教师", "无此教师信息，或者姓名输入不正确。");
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								MessageDialog.openWarning(shell, "查询操作失败", "查询操作失败。");
							}
						} else {
							MessageDialog.openError(shell, "没有输入姓名", "必须输入教师姓名。");
						}
					}
				}
			};
		}
		{
			actionTchQueryDept = new Action("按专业") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchQueryDept(shell, SWT.None).open();
				}
			};
		}
		{
			actionStdBrowse = new Action("学生浏览") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new StdQueryClass().open();
				}				
			};
		}
		{
			new Action("班级排课") {
			};
		}
		{
			actionStdSelCourse = new Action("学生选课") {
			};
			actionStdSelCourse.setToolTipText("此功能尚未实现");
			actionStdSelCourse.setEnabled(false);
		}
		{
			actionModifyPassword = new Action("修改密码") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new ModifyPassword(user).open();
				}
			};
		}
		{
			actionStdDel = new Action("学生销户") {
			};
		}
		{
			actionTchDel = new Action("教师销户") {
			};
		}
		{
			actionDataBackup = new Action("数据备份") {
			};
		}
		{
			actionDataRestore = new Action("数据恢复") {
			};
		}
		{
			actionHelpContent = new Action("帮助内容") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new BrowserHelp().open();
				}				
			};
		}
		{
			actionUsageManual = new Action("使用手册") {
			};
			actionUsageManual.setEnabled(false);
		}
		{
			actionAbout = new Action("关于(…)") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					MessageDialog.openInformation(shell, "关于简易学生成绩管理系统", "简易学生成绩管理系统是一个示例Java SWT GUI程序，演示了SWT组件的使用方法及一个较为完整的Java GUI程序开发过程。");
				}
			};
		}
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		{
			MenuManager menuManagerSysSet = new MenuManager("New MenuManager");
			menuManagerSysSet.setMenuText("系统设置");
			menuManager.add(menuManagerSysSet);
			menuManagerSysSet.add(actionDeptMana);
			menuManagerSysSet.add(actionGradeClassMana);
			menuManagerSysSet.add(actionCourseMana);
			menuManagerSysSet.add(actionExit);
		}
		{
			MenuManager menuManagerUserRegister = new MenuManager("New MenuManager");
			menuManagerUserRegister.setMenuText("用户注册");
			menuManager.add(menuManagerUserRegister);
			menuManagerUserRegister.add(actionStdRegister);
			{
				MenuManager menuManagerStdQuery = new MenuManager("New MenuManager");
				menuManagerStdQuery.setMenuText("学生查询");
				menuManagerUserRegister.add(menuManagerStdQuery);
				menuManagerStdQuery.add(actionStdQueryID);
				menuManagerStdQuery.add(actionStdQueryClass);
			}
			menuManagerUserRegister.add(actionStdBrowse);
			menuManagerUserRegister.add(actionTchRegister);
			{
				MenuManager menuManagerTchQuery = new MenuManager("New MenuManager");
				menuManagerTchQuery.setMenuText("教师查询");
				menuManagerUserRegister.add(menuManagerTchQuery);
				menuManagerTchQuery.add(actionTchQueryID);
				menuManagerTchQuery.add(actionTchQueryName);
				menuManagerTchQuery.add(actionTchQueryDept);
			}
		}
		{
			MenuManager menuManagerSelCourse = new MenuManager("New MenuManager");
			menuManagerSelCourse.setMenuText("排课选课");
			menuManager.add(menuManagerSelCourse);
			menuManagerSelCourse.add(actionGradeClassCourse);
			menuManagerSelCourse.add(actionStdSelCourse);
		}
		{
			MenuManager menuManagerSysMana = new MenuManager("New MenuManager");
			menuManagerSysMana.setMenuText("系统管理");
			menuManager.add(menuManagerSysMana);
			menuManagerSysMana.add(actionModifyPassword);
			menuManagerSysMana.add(actionStdDel);
			menuManagerSysMana.add(actionTchDel);
			menuManagerSysMana.add(actionDataBackup);
			menuManagerSysMana.add(actionDataRestore);
		}
		{
			MenuManager menuManagerHelp = new MenuManager("New MenuManager");
			menuManagerHelp.setMenuText("帮助");
			menuManager.add(menuManagerHelp);
			menuManagerHelp.add(actionHelpContent);
			menuManagerHelp.add(actionUsageManual);
			menuManagerHelp.add(actionAbout);
		}
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		toolBarManager.add(actionDeptMana);
		toolBarManager.add(actionGradeClassMana);
		toolBarManager.add(actionCourseMana);
		toolBarManager.add(actionUserRegister);
		toolBarManager.add(actionGradeClassCourse);
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
			AdminPrj window = new AdminPrj(null);
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
		newShell.setText("管理员子系统");
		this.shell = newShell;
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(500, 300);
	}

}
