package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class FillLayoutTestH {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FillLayoutTestH window = new FillLayoutTestH();
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
		FillLayout fl_shell = new FillLayout(SWT.HORIZONTAL);
		fl_shell.spacing = 8;
		fl_shell.marginWidth = 5;
		fl_shell.marginHeight = 10;
		shell.setLayout(fl_shell);
		
		Button btnB = new Button(shell, SWT.NONE);
		btnB.setText("B1");
		
		Button btnB_1 = new Button(shell, SWT.NONE);
		btnB_1.setText("B2");
		
		Button btnButton = new Button(shell, SWT.NONE);
		btnButton.setText("Button3");

	}

}
