package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TrayItem;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.MenuDetectEvent;

public class TaryDemo {

	protected Shell shell;
	/**
	 * @wbp.nonvisual location=160,160
	 */
	private final TrayItem trtmTrayBug = new TrayItem(Display.getDefault().getSystemTray(), SWT.NONE);
	private Menu menu;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TaryDemo window = new TaryDemo();
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
		trtmTrayBug.setHighlightImage(SWTResourceManager.getImage(TaryDemo.class, "/book/demo/wj.JPG"));
		trtmTrayBug.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent arg0) {
				menu.setVisible(true);
			}
		});
		trtmTrayBug.setToolTipText("Tray Bug 演示");
		trtmTrayBug.setText("演示TrayIcon");
		trtmTrayBug.setImage(SWTResourceManager.getImage(TaryDemo.class, "/book/demo/bug.jpg"));
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		menu = new Menu(shell);
		//shell.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item");

	}
}
