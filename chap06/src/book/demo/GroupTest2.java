package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

public class GroupTest2 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			GroupTest2 window = new GroupTest2();
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
		
		Group group = new Group(shell, SWT.NONE);
		group.setText("用户身份");
		group.setBounds(27, 97, 354, 91);
		
		Button button = new Button(group, SWT.RADIO);
		button.setBounds(10, 45, 79, 24);
		button.setText("学生");
		
		Button button_1 = new Button(group, SWT.RADIO);
		button_1.setBounds(97, 45, 73, 24);
		button_1.setText("教师");
		
		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setBounds(176, 45, 82, 24);
		button_2.setText("管理员");
		
		Button button_3 = new Button(group, SWT.RADIO);
		button_3.setBounds(264, 45, 65, 24);
		button_3.setText("其他");

	}
}
