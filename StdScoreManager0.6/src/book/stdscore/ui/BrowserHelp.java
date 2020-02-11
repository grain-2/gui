package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolTip;
import org.eclipse.swt.layout.FillLayout;

import java.io.File;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;

public class BrowserHelp {

	protected Shell shell;
	private Browser browser;
	/**
	 * @wbp.nonvisual location=160,210
	 */
	private final TrayItem trayItemHelp = new TrayItem(Display.getDefault().getSystemTray(), SWT.NONE);

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BrowserHelp window = new BrowserHelp();
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
		trayItemHelp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				shell.setVisible(!shell.isVisible());
			}
		});
		trayItemHelp.setToolTipText("学生成绩管理系统在线帮助");
		trayItemHelp.setImage(SWTResourceManager.getImage(BrowserHelp.class, "/images/star.jpg"));		
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		final ToolTip toolTip = new ToolTip(shell, SWT.BALLOON|SWT.ICON_INFORMATION);
		trayItemHelp.setToolTip(toolTip);
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		ExpandBar expandBar = new ExpandBar(sashForm, SWT.NONE);
		
		ExpandItem expandItem1 = new ExpandItem(expandBar, SWT.NONE);
		expandItem1.setExpanded(true);
		expandItem1.setText("系统概述");
		
		Composite composite1 = new Composite(expandBar, SWT.NONE);
		expandItem1.setControl(composite1);
		expandItem1.setHeight(expandItem1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		Link link11 = new Link(composite1, SWT.NONE);
		link11.setBounds(10, 0, 79, 24);
		link11.setText("<a href=\"/helppages/intro1.html\">系统功能</a>");
		
		Link link12 = new Link(composite1, SWT.NONE);
		link12.setBounds(10, 30, 79, 24);
		link12.setText("<a>运行环境</a>");
		
		ExpandItem expandItem2 = new ExpandItem(expandBar, SWT.NONE);
		expandItem2.setText("系统设置");
		
		ExpandItem expandItem3 = new ExpandItem(expandBar, SWT.NONE);
		expandItem3.setText("用户注册");
		
		ExpandItem expandItem4 = new ExpandItem(expandBar, SWT.NONE);
		expandItem4.setText("课程设置");
		
		ExpandItem expandItem5 = new ExpandItem(expandBar, SWT.NONE);
		expandItem5.setText("教师帮助");
		
		ExpandItem expandItem6 = new ExpandItem(expandBar, SWT.NONE);
		expandItem6.setText("学生帮助");
		
		browser = new Browser(sashForm, SWT.NONE);
		
		Menu menuHelpTrayItem = new Menu(browser);
		//browser.setMenu(menuHelpTrayItem);
		
		MenuItem showMenuItem = new MenuItem(menuHelpTrayItem, SWT.NONE);
		showMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean showFlag = shell.isVisible();
				shell.setVisible(!showFlag);
				showMenuItem.setText(showFlag ? "显示&W" : "隐藏&H");
				toolTip.setText("学生成绩管理系统帮助的托盘图标");
				toolTip.setMessage("右键单击图标，可以选择菜单");
				toolTip.setVisible(true);
			}
		});
		showMenuItem.setText("隐藏（&h）");
		
		MenuItem exitMenuItem = new MenuItem(menuHelpTrayItem, SWT.NONE);
		exitMenuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		exitMenuItem.setText("退出（&x）");
		trayItemHelp.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent arg0) {
				menuHelpTrayItem.setVisible(true);
			}
		});

		sashForm.setWeights(new int[] {3, 7});
		link11.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Link link = (Link)e.getSource();
				String url = pareseLinkUrl(link.getText());
				browser.setUrl(url);
			}
		});
		
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				super.shellClosed(e);
				e.doit = false; // 使窗口的关闭按钮无效
				shell.setVisible(false);
				showMenuItem.setText("显示&W");
				toolTip.setText("学生成绩管理系统帮助的托盘图标");
				toolTip.setMessage("右键单击图标，可以选择菜单");
				toolTip.setVisible(true);
			}
			@Override
			public void shellIconified(ShellEvent e) {
				super.shellIconified(e);
				shellClosed(e);
			}
		});

	}
	
	public String pareseLinkUrl(String str) {
		String url=null;
		if(str.startsWith("<a")) {
			char pre = str.charAt(str.indexOf("href=")+5);
			int startp = -1 ;
			int endp = -1;
			if(pre=='"' || pre=='\'') {
				startp = str.indexOf("href=")+6;
				endp = str.indexOf(pre, startp+1);
			} else {
				startp = str.indexOf("href=")+5;
				endp = str.indexOf(" ",startp+1);
				if(endp==-1)
					endp = str.indexOf(">",startp+1);
			}
			url = str.substring(startp, endp);
		}

		return new File(this.getClass().getResource("/").getPath())+ url;
	}
}
