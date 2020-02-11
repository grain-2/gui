package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;

public class ScaleDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScaleDemo window = new ScaleDemo();
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
		
		Scale scale = new Scale(shell, SWT.NONE);
		scale.setIncrement(2);
		scale.setSelection(30);
		scale.setBounds(27, 42, 260, 54);
		
		Slider slider = new Slider(shell, SWT.NONE);
		slider.setBounds(27, 135, 260, 26);
		
		Spinner spinner = new Spinner(shell, SWT.BORDER);
		spinner.setBounds(335, 135, 68, 31);

	}
}
