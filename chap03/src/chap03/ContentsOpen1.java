package chap03;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;

public class ContentsOpen1 {
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ContentsOpen1 window = new ContentsOpen1();
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
		Shell shell = new Shell();
		shell.setMinimumSize(new Point(400, 300));
		shell.setSize(450, 300);
		shell.setText("测试-Contents in open");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setBounds(78, 43, 90, 24);
		lblNewLabel.setText("New Label");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(222, 43, 73, 30);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setBounds(78, 110, 114, 34);
		btnNewButton.setText("New Button");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(222, 112, 97, 32);
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(78, 175, 143, 24);
		btnCheckButton.setText("Check Button");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		btnRadioButton.setBounds(238, 175, 141, 24);
		btnRadioButton.setText("Radio Button");

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
