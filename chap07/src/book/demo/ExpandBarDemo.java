package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.RowLayout;

public class ExpandBarDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExpandBarDemo window = new ExpandBarDemo();
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
		
		ExpandBar expandBar = new ExpandBar(shell, SWT.V_SCROLL);
		expandBar.setBounds(38, 51, 200, 150);
		
		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("New ExpandItem1");
		
		Composite composite = new Composite(expandBar, SWT.NONE);
		composite.setSize(new Point(80, 110));
		xpndtmNewExpanditem.setControl(composite);
		xpndtmNewExpanditem.setHeight(110);
		composite.setLayout(new RowLayout(SWT.VERTICAL));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setText("New Label1");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setText("New Label2");
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("New Label3");
		
		Label lblNewLabel_3 = new Label(composite, SWT.NONE);
		lblNewLabel_3.setText("New Label4");
		
		ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setText("New ExpandItem2");
		
		ExpandItem xpndtmNewExpanditem_2 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_2.setText("New ExpandItem3");
		
		ExpandItem xpndtmNewExpanditem_3 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_3.setText("New ExpandItem4");
		
		ExpandItem xpndtmNewExpanditem_4 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_4.setText("New ExpandItem5");

	}
}
