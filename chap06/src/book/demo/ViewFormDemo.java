package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.Button;

public class ViewFormDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ViewFormDemo window = new ViewFormDemo();
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
		
		ViewForm viewForm = new ViewForm(shell, SWT.NONE);
		viewForm.setBorderVisible(true);
		viewForm.marginWidth = 3;
		viewForm.marginHeight = 8;
		viewForm.verticalSpacing = 6;
		viewForm.horizontalSpacing = 10;
		
		Button btnTopLeft = new Button(viewForm, SWT.NONE);
		viewForm.setTopLeft(btnTopLeft);
		btnTopLeft.setText("TOP Left");
		
		Button btnNewButton = new Button(viewForm, SWT.NONE);
		viewForm.setTopRight(btnNewButton);
		btnNewButton.setText("New Button");

	}

}
