package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ToolBarDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ToolBarDemo window = new ToolBarDemo();
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
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(10, 10, 408, 58);
		
		ToolItem tltmNewItem = new ToolItem(toolBar, SWT.NONE);
		tltmNewItem.setText("New Item1");
		
		ToolItem tltmCheckItem = new ToolItem(toolBar, SWT.CHECK);
		tltmCheckItem.setText("Check Item");
		
		ToolItem dropdownItem = new ToolItem(toolBar, SWT.DROP_DOWN);
		dropdownItem.setText("DropDown Item");
		
		Menu menu = new Menu(toolBar);
		//toolBar.setMenu(menu);
		
		MenuItem mntmNewItem = new MenuItem(menu, SWT.NONE);
		mntmNewItem.setText("New Item1");
		
		MenuItem mntmNewItem_1 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_1.setText("New Item2");
		
		MenuItem mntmNewItem_2 = new MenuItem(menu, SWT.NONE);
		mntmNewItem_2.setText("New Item3");
		dropdownItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Rectangle bound = dropdownItem.getBounds();
				Point point = toolBar.toDisplay(bound.x, bound.y+bound.height);
				menu.setLocation(point);
				menu.setVisible(true);
				
			}
		});
	}
}
