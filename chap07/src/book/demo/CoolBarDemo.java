package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

public class CoolBarDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CoolBarDemo window = new CoolBarDemo();
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
		shell = new Shell();
		shell.setSize(660, 300);
		shell.setText("SWT Application");
		
		CoolBar coolBar = new CoolBar(shell, SWT.FLAT);
		coolBar.setBounds(10, 10, 620, 100);
		
		CoolItem coolItem1 = new CoolItem(coolBar, SWT.NONE);
		coolItem1.setImage(null);
		coolItem1.setPreferredSize(new Point(170, 39));
		coolItem1.setMinimumSize(new Point(100, 30));
		coolItem1.setSize(new Point(160, 38));
		
		ToolBar toolBar1 = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem1.setControl(toolBar1);
		
		ToolItem toolItem111 = new ToolItem(toolBar1, SWT.NONE);
		toolItem111.setText("按钮11");
		
		ToolItem toolItem112 = new ToolItem(toolBar1, SWT.NONE);
		toolItem112.setText("按钮12");
		
		CoolItem coolItem2 = new CoolItem(coolBar, SWT.NONE);
		coolItem2.setPreferredSize(new Point(170, 39));
		coolItem2.setSize(new Point(100, 38));
		coolItem2.setMinimumSize(new Point(100, 30));
		
		ToolBar toolBar2 = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem2.setControl(toolBar2);
		
		ToolItem toolItem221 = new ToolItem(toolBar2, SWT.NONE);
		toolItem221.setText("按钮21");
		
		ToolItem toolItem222 = new ToolItem(toolBar2, SWT.NONE);
		toolItem222.setText("按钮22");
		
		CoolItem coolItem3 = new CoolItem(coolBar, SWT.NONE);
		coolItem3.setSize(new Point(160, 38));
		coolItem3.setMinimumSize(new Point(100, 30));
		
		ToolBar toolBar3 = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem3.setControl(toolBar3);
		
		ToolItem toolItem331 = new ToolItem(toolBar3, SWT.NONE);
		toolItem331.setText("按钮31");
		
		ToolItem toolItem332 = new ToolItem(toolBar3, SWT.NONE);
		toolItem332.setText("按钮32");
		
		CoolItem coolItem4 = new CoolItem(coolBar, SWT.NONE);
		coolItem4.setSize(new Point(100, 38));
		coolItem4.setMinimumSize(new Point(100, 30));
		
		ToolBar toolBar4 = new ToolBar(coolBar, SWT.FLAT | SWT.RIGHT);
		coolItem4.setControl(toolBar4);
		
		ToolItem toolItem441 = new ToolItem(toolBar4, SWT.NONE);
		toolItem441.setText("按钮41");
		
		ToolItem toolItem442 = new ToolItem(toolBar4, SWT.NONE);
		toolItem442.setText("按钮42");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(261, 116, 114, 34);
		btnNewButton.setText("中央按钮");
		
	}
}
