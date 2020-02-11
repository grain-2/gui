package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class GridLayoutTest1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GridLayoutTest1 window = new GridLayoutTest1();
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
		shell.setLayout(new GridLayout(3, false));
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.setText("B1");
		
		Button button2 = new Button(shell, SWT.NONE);
		button2.setText("Wide Button 2");
		
		Button button3 = new Button(shell, SWT.NONE);
		button3.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		button3.setText("Button 3");
		
		Button button4 = new Button(shell, SWT.NONE);
		button4.setText("Btn4");
		
		Button button5 = new Button(shell, SWT.NONE);
		GridData gd_button5 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_button5.heightHint = 60;
		gd_button5.widthHint = 80;
		button5.setLayoutData(gd_button5);
		button5.setText("B5");

	}

}
