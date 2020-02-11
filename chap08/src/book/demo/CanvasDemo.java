package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;

public class CanvasDemo {

	protected Shell shell;
	private Button btnRadioButton;
	private Button btnRadioButton_1;
	private Button btnRadioButton_2;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CanvasDemo window = new CanvasDemo();
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
		
		Canvas canvas = new Canvas(shell, SWT.BORDER | SWT.NO_FOCUS | SWT.NO_MERGE_PAINTS | SWT.NO_RADIO_GROUP);
		canvas.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		canvas.setBounds(40, 35, 292, 168);
		
		btnRadioButton = new Button(canvas, SWT.RADIO);
		btnRadioButton.setBounds(38, 25, 141, 24);
		btnRadioButton.setText("Radio Button");
		
		btnRadioButton_1 = new Button(canvas, SWT.RADIO);
		btnRadioButton_1.setBounds(38, 84, 141, 24);
		btnRadioButton_1.setText("Radio Button");
		
		btnRadioButton_2 = new Button(canvas, SWT.RADIO);
		btnRadioButton_2.setBounds(38, 130, 141, 24);
		btnRadioButton_2.setText("Radio Button");

	}
}
