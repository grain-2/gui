package book.demo;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class InputDialogDemo extends ApplicationWindow {

	/**
	 * Create the application window.
	 */
	public InputDialogDemo() {
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
		
		Label lblip = new Label(container, SWT.NONE);
		lblip.setBounds(65, 97, 315, 24);
		lblip.setText("你输入的IP地址是：");
		
		Button btnIpv = new Button(container, SWT.NONE);
		btnIpv.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog idl = new InputDialog(getParentShell(), "输入IPv4地址", "请输入一个IPv4地址，四组十进制整数，每组在 0-255 之间。", "127.0.0.1", new MyInputDialogVerify());
				idl.open();
				String str1 = idl.getValue();
				lblip.setText("你输入的IP地址是："+str1);
			}
		});
		btnIpv.setBounds(167, 38, 114, 34);
		btnIpv.setText("IPv4地址");

		return container;
	}

	public class MyInputDialogVerify implements IInputValidator {

		@Override
		public String isValid(String arg0) {
			// TODO 自动生成的方法存根
			String msg=null;
			int gn=-1;
			String[] ips = arg0.split("[.]");
			if(ips.length!=4) {
				msg = "数据组数错误！\n正确的IPv4地址是四组十进制整数，每组在 0-255 之间。";
			} else {
				for(int i=0;i<4;i++) {
					if(!ips[i].matches("[0-9]{1,3}")) {
						msg = "包含非法字符或数据位数错误！\n正确的IPv4地址是四组十进制整数，每组在 0-255 之间。";
						break;
					}
					gn = Integer.parseInt(ips[i]);
					if(gn<0 || gn>255) {
						msg = "数据范围错误！\n正确的IPv4地址是四组十进制整数，每组在 0-255 之间。";
						break;						
					}
				}
			}
			return msg;
		}
		
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
			InputDialogDemo window = new InputDialogDemo();
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
