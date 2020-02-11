package chap04;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;

public class ExControlEvent {

	protected Shell shell;
	private Button button;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExControlEvent window = new ExControlEvent();
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
		shell.setLocation(new Point(0, 0));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		button = new Button(shell, SWT.NONE);
		button.setBounds(0, 0, 200, 30);
		button.setText("居中按钮");

		shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlMoved(ControlEvent e) {
				button.setText("窗口移到 (" + shell.getBounds().x + "," + 	shell.getBounds().y +") ") ;
			}
			@Override
			public void controlResized(ControlEvent e) {
				int widthShell = shell.getBounds().width ;
				int heightShell = shell.getBounds().height ;
				int widthButton = button.getBounds().width ;
				int heightButton = button.getBounds().height ;
				int buttonX = widthShell/2-widthButton/2 ;
				int buttonY = heightShell/2-heightButton ;
				button.setLocation(buttonX, buttonY) ;
				button.setVisible(true);
			}
		});

	}
}
