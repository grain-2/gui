package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class CTabFolderDemo {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CTabFolderDemo window = new CTabFolderDemo();
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
		
		CTabFolder tabFolder = new CTabFolder(shell, SWT.BORDER | SWT.SINGLE);
		tabFolder.setMRUVisible(true);
		tabFolder.marginWidth = 8;
		tabFolder.marginHeight = 10;
		tabFolder.setMaximized(true);
		tabFolder.setMinimizeVisible(true);
		tabFolder.setSelectionForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		tabFolder.setBorderVisible(false);
		tabFolder.setSingle(false);
		tabFolder.setMaximizeVisible(true);
		tabFolder.setSelectionBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		CTabItem tab1 = new CTabItem(tabFolder, SWT.NONE);
		tab1.setFont(SWTResourceManager.getFont("PMingLiU-ExtB", 14, SWT.NORMAL));
		tab1.setShowClose(true);
		tab1.setText("Tab 1");
		
		Composite composite = new Composite(tabFolder, SWT.NONE);
		tab1.setControl(composite);
		
		CTabItem tab2 = new CTabItem(tabFolder, SWT.CLOSE);
		tab2.setImage(null);
		tab2.setShowClose(false);
		tab2.setText("Tab 2");
		
		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tab2.setControl(composite_1);
		
		CTabItem tab3 = new CTabItem(tabFolder, SWT.NONE);
		tab3.setText("Tab 3");
		
		text = new Text(tabFolder, SWT.BORDER);
		tab3.setControl(text);
		
		CTabItem tab4 = new CTabItem(tabFolder, SWT.NONE);
		tab4.setText("Tab 4");

	}

}
