package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.FlowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class FlowLayoutDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FlowLayoutDemo window = new FlowLayoutDemo();
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
		shell.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("New Button");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setText("New Button");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setText("New Button");
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setText("New Button");
		
		Button btnNewButton_4 = new Button(shell, SWT.NONE);
		btnNewButton_4.setText("New Button");

	}

}
