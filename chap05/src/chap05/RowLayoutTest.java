package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.RowData;

public class RowLayoutTest {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RowLayoutTest window = new RowLayoutTest();
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
		RowLayout rl_shell = new RowLayout(SWT.HORIZONTAL);
		rl_shell.fill = true;
		shell.setLayout(rl_shell);
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.setText("B1");
		
		Button button2 = new Button(shell, SWT.NONE);
		button2.setLayoutData(new RowData(80, 50));
		button2.setText("B2");
		
		Button button3 = new Button(shell, SWT.NONE);
		button3.setText("Button3");
		
		Text text4 = new Text(shell, SWT.WRAP | SWT.MULTI);
		text4.setLayoutData(new RowData(150, 85));
		text4.setTouchEnabled(true);
		text4.setText("一个测试文本框。有3行文字，占据空间较大。");
		
		Button button5 = new Button(shell, SWT.NONE);
		button5.setText("btn5");

	}

}
