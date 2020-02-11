package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class FormLayoutDemo1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			FormLayoutDemo1 window = new FormLayoutDemo1();
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
		shell.setLayout(new FormLayout());
		
		Button button1 = new Button(shell, SWT.NONE);
		FormData fd_button1 = new FormData();
		fd_button1.top = new FormAttachment(0, 45);
		fd_button1.left = new FormAttachment(0, 80);
		button1.setLayoutData(fd_button1);
		button1.setText("New Button");
		
		Button button2 = new Button(shell, SWT.NONE);
		FormData fd_button2 = new FormData();
		fd_button2.bottom = new FormAttachment(100, -94);
		fd_button2.top = new FormAttachment(button1, 39);
		fd_button2.left = new FormAttachment(button1, 0, SWT.LEFT);
		fd_button2.right = new FormAttachment(89, -189);
		button2.setLayoutData(fd_button2);
		button2.setText("New Button");
		
		Button button3 = new Button(shell, SWT.NONE);
		FormData fd_button3 = new FormData();
		fd_button3.top = new FormAttachment(button2, 20, SWT.TOP);
		fd_button3.left = new FormAttachment(button2, 49);
		button3.setLayoutData(fd_button3);
		button3.setText("New Button");
		
		Button button4 = new Button(shell, SWT.NONE);
		FormData fd_button4 = new FormData();
		fd_button4.top = new FormAttachment(75);
		fd_button4.right = new FormAttachment(42);
		fd_button4.bottom = new FormAttachment(100, -25);
		fd_button4.left = new FormAttachment(0, 55);
		button4.setLayoutData(fd_button4);
		button4.setText("New Button4");

	}
}
