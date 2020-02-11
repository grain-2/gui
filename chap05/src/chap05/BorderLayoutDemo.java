package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import swing2swt.layout.BorderLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;

public class BorderLayoutDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			BorderLayoutDemo window = new BorderLayoutDemo();
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
		shell.setLayout(new BorderLayout(5, 5));
		
		Button buttonC = new Button(shell, SWT.NONE);
		buttonC.setLayoutData(BorderLayout.CENTER);
		buttonC.setText("中");
		
		Button buttonW = new Button(shell, SWT.NONE);
		buttonW.setLayoutData(BorderLayout.WEST);
		buttonW.setText("西");
		
		Button buttonE = new Button(shell, SWT.NONE);
		buttonE.setLayoutData(BorderLayout.EAST);
		buttonE.setText("东");
		
		Button buttonN = new Button(shell, SWT.NONE);
		buttonN.setLayoutData(BorderLayout.NORTH);
		buttonN.setText("北");
		
		Button buttonS = new Button(shell, SWT.NONE);
		buttonS.setLayoutData(BorderLayout.SOUTH);
		buttonS.setText("南");

	}

}
