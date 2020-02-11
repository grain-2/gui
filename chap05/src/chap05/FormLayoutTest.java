package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class FormLayoutTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FormLayoutTest window = new FormLayoutTest();
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
		FormLayout fl_shell = new FormLayout();
		fl_shell.spacing = 10;
		fl_shell.marginHeight = 10;
		fl_shell.marginWidth = 15;
		shell.setLayout(fl_shell);
		
		Button button1 = new Button(shell, SWT.NONE);
		FormData fd_button1 = new FormData();
		fd_button1.top = new FormAttachment(0);
		fd_button1.left = new FormAttachment(0);
		button1.setLayoutData(fd_button1);
		button1.setText("New Button");
		
		Button button2 = new Button(shell, SWT.NONE);
		FormData fd_button2 = new FormData();
		fd_button2.top = new FormAttachment(button1, 6);
		fd_button2.left = new FormAttachment(button1, 0, SWT.LEFT);
		button2.setLayoutData(fd_button2);
		button2.setText("New Button");
		
		Button button3 = new Button(shell, SWT.NONE);
		FormData fd_button3 = new FormData();
		fd_button3.top = new FormAttachment(button2, 0, SWT.TOP);
		fd_button3.left = new FormAttachment(button2, 61);
		button3.setLayoutData(fd_button3);
		button3.setText("New Button");

	}
}
