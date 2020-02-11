package book.stdscore.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.action.Action;
import book.stdscore.data.User;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import book.stdscore.data.*;

public class TchScoreMana extends ApplicationWindow {
	private User user;
	private Shell shell;
	
	private Action actionInput;
	private Action actionExit;
	private Action actionBrowse;
	private Action actionRangeQuery;
	private Action actionStdQuery;
	private Action actionBaseStat;
	private Action actionFrequency;
	private Action actionPrint;
	private Action actionFile;
	private Action actionIntro;
	private Action actionHelpContent;
	private Action actionHelpAbout;

	/**
	 * Create the application window.
	 */
	public TchScoreMana(User user) {
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
		
		Label label = new Label(container, SWT.WRAP);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("华文新魏", 16, SWT.BOLD));
		label.setBounds(66, 25, 291, 74);
		label.setText("欢迎老师使用简易学生成绩管理系统");

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			actionInput = new Action("输入成绩") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchScoreEdit(user).open();
				}
			};
		}
		{
			actionExit = new Action("退出") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					System.exit(0);
				}
			};
		}
		{
			actionBrowse = new Action("浏览") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					TchScoreBrowse tse = new TchScoreBrowse(user);
					//tse.getTableSelected().setEnabled(false);
					tse.open();
				}
			};
		}
		{
			actionRangeQuery = new Action("按分数查询") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchScoreQuery(user).open();
				}
			};
		}
		{
			actionStdQuery = new Action("按学生查询") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					InputDialog idl = new InputDialog(shell, "输入学号", "请输入要查询学生的学号！", "", null);
					idl.open();
					String stdId = idl.getValue();
					if(stdId!=null && !"".equals(stdId)) {
						long id = Long.parseLong(stdId);
						// 打开该学生所有课程成绩表格
						String sql = "select * from users where name='" + id +"'";
						ResultSet rs = new InitDB().getRs(sql);
						try {
							if(rs.next()) {
								new StudentMain(new User(rs.getString(1),rs.getString(2),rs.getInt(3))).open();
							}
						} catch (SQLException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					} else {
						MessageDialog.openWarning(shell, "学号不正确", "无此学号学生成绩信息。");
					}
				}
			};
		}
		{
			actionBaseStat = new Action("基本指标") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchScoreStat(user).open();
				}
			};
		}
		{
			actionFrequency = new Action("成绩分布") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchScoreChart(user).open();
				}
			};
		}
		{
			actionPrint = new Action("打印") {
			};
			actionPrint.setEnabled(false);
		}
		{
			actionFile = new Action("文件") {				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new TchScoreFile(user).open();
				}
			};
		}
		{
			actionIntro = new Action("系统说明") {				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					MessageDialog.openInformation(shell, "简易学生成绩管理系统功能简介", "简易学生成绩管理系统的教师工作平台教师模块包含5个子模块。（1）成绩登录……");
				}
			};
		}
		{
			actionHelpContent = new Action("帮助内容") {				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					new BrowserHelp().open();
				}
			};
		}
		{
			actionHelpAbout = new Action("关于(…)") {
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
			MenuManager menuManagerInput = new MenuManager("New MenuManager");
			menuManagerInput.setMenuText("成绩登录");
			menuManager.add(menuManagerInput);
			menuManagerInput.add(actionInput);
			menuManagerInput.add(actionExit);
		}
		{
			MenuManager menuManagerQuery = new MenuManager("New MenuManager");
			menuManagerQuery.setMenuText("成绩查询");
			menuManager.add(menuManagerQuery);
			menuManagerQuery.add(actionBrowse);
			menuManagerQuery.add(actionRangeQuery);
			menuManagerQuery.add(actionStdQuery);
		}
		{
			MenuManager menuManagerStat = new MenuManager("New MenuManager");
			menuManagerStat.setMenuText("成绩统计");
			menuManager.add(menuManagerStat);
			menuManagerStat.add(actionBaseStat);
			menuManagerStat.add(actionFrequency);
		}
		{
			MenuManager menuManagerOutput = new MenuManager("New MenuManager");
			menuManagerOutput.setMenuText("成绩输出");
			menuManager.add(menuManagerOutput);
			menuManagerOutput.add(actionPrint);
			menuManagerOutput.add(actionFile);
		}
		{
			MenuManager menuManagerHelp = new MenuManager("New MenuManager");
			menuManagerHelp.setMenuText("帮助");
			menuManager.add(menuManagerHelp);
			menuManagerHelp.add(actionIntro);
			menuManagerHelp.add(actionHelpContent);
			menuManagerHelp.add(actionHelpAbout);
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
			TchScoreMana window = new TchScoreMana(null);
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
		newShell.setText("成绩管理教师工作平台");
		this.shell = newShell;
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
