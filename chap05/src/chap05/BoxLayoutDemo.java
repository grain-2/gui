package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BoxLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class BoxLayoutDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BoxLayoutDemo window = new BoxLayoutDemo();
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
		shell.setLayout(new BoxLayout(BoxLayout.X_AXIS));
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("New Button1");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.setText("New Button2");
		
		Button btnNewButton_2 = new Button(shell, SWT.NONE);
		btnNewButton_2.setText("New Button3");
		
		Button btnNewButton_3 = new Button(shell, SWT.NONE);
		btnNewButton_3.setText("New Button4");

	}

}
