package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.layout.FillLayout;

public class MenuDemo1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MenuDemo1 window = new MenuDemo1();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);
		
		MenuItem submenuItemFile = new MenuItem(menu, SWT.CASCADE);
		submenuItemFile.setText("文件(&F)");
		
		Menu submenuFile = new Menu(submenuItemFile);
		submenuItemFile.setMenu(submenuFile);
		
		MenuItem submenuItemNew = new MenuItem(submenuFile, SWT.CASCADE);
		submenuItemNew.setText("新建(&N)");
		
		Menu submenuNew = new Menu(submenuItemNew);
		submenuItemNew.setMenu(submenuNew);
		
		MenuItem prjItem = new MenuItem(submenuNew, SWT.NONE);
		prjItem.setAccelerator(SWT.CTRL+'N');
		prjItem.setText("项目\tCTRL+N");
		
		new MenuItem(submenuNew, SWT.SEPARATOR);
		
		MenuItem packageItem = new MenuItem(submenuNew, SWT.NONE);
		packageItem.setText("包");
		
		MenuItem classItem = new MenuItem(submenuNew, SWT.NONE);
		classItem.setText("类");
		
		MenuItem interfItem = new MenuItem(submenuNew, SWT.NONE);
		interfItem.setText("接口");
		
		new MenuItem(submenuNew, SWT.SEPARATOR);
		
		MenuItem otherItem = new MenuItem(submenuNew, SWT.NONE);
		otherItem.setText("其他(&O)");
		
		MenuItem mntmNewItem = new MenuItem(submenuFile, SWT.NONE);
		mntmNewItem.setText("退出(&E)");
		
		MenuItem submenuItemHelp = new MenuItem(menu, SWT.CASCADE);
		submenuItemHelp.setText("帮助(&H)");
		
		Menu submenuHelp = new Menu(submenuItemHelp);
		submenuItemHelp.setMenu(submenuHelp);
		
		MenuItem aboutItem = new MenuItem(submenuHelp, SWT.NONE);
		aboutItem.setText("关于…");

	}
}
