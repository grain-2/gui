package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;

public class SeperatorDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SeperatorDemo window = new SeperatorDemo();
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
		
		Label lblGfd = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		lblGfd.setBounds(81, 53, 64, 2);
		
		Label lblDsa = new Label(shell, SWT.SEPARATOR | SWT.VERTICAL);
		lblDsa.setText("dsa");
		lblDsa.setBounds(172, 130, 2, 64);

	}
}
