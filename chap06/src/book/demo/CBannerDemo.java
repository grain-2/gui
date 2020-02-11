package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CBanner;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;

public class CBannerDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CBannerDemo window = new CBannerDemo();
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
		shell.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		CBanner banner = new CBanner(shell, SWT.NONE);
		banner.setLayoutData(new RowData(422, 81));
		
		ToolBar toolBar = new ToolBar(banner, SWT.FLAT | SWT.RIGHT);
		banner.setLeft(toolBar);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("New Item");
		
		ToolItem tltmNewItem_1 = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem_1.setText("New Item");
		
		ToolBar toolBar_1 = new ToolBar(banner, SWT.FLAT | SWT.RIGHT);
		banner.setRight(toolBar_1);
		
		ToolItem tltmNewItem_2 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_2.setText("New Item");
		
		ToolItem tltmNewItem_3 = new ToolItem(toolBar_1, SWT.NONE);
		tltmNewItem_3.setText("New Item");
		
		ToolBar toolBar_2 = new ToolBar(banner, SWT.FLAT | SWT.RIGHT);
		banner.setBottom(toolBar_2);
		
		ToolItem tltmNewItem_4 = new ToolItem(toolBar_2, SWT.NONE);
		tltmNewItem_4.setText("New Item");
		
		ToolItem tltmNewItem_5 = new ToolItem(toolBar_2, SWT.NONE);
		tltmNewItem_5.setText("New Item");
		
		ToolItem tltmNewItem_6 = new ToolItem(toolBar_2, SWT.NONE);
		tltmNewItem_6.setText("New Item");

	}

}
