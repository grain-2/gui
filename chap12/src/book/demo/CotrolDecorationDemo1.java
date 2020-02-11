package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class CotrolDecorationDemo1 {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CotrolDecorationDemo1 window = new CotrolDecorationDemo1();
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
		GridLayout gl_shell = new GridLayout(3, false);
		shell.setLayout(gl_shell);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setText("New Button");
		
		ControlDecoration controlDecoration = new ControlDecoration(btnNewButton, SWT.LEFT | SWT.TOP);
		controlDecoration.setShowOnlyOnFocus(true);
		controlDecoration.setShowHover(false);
		controlDecoration.setMarginWidth(2);
		controlDecoration.setImage(SWTResourceManager.getImage(CotrolDecorationDemo1.class, "/icons/close.JPG"));
		controlDecoration.setDescriptionText("Some description");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		text = new Text(shell, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		ControlDecoration controlDecoration_1 = new ControlDecoration(text, SWT.LEFT | SWT.TOP);
		controlDecoration_1.setShowOnlyOnFocus(true);
		controlDecoration_1.setImage(SWTResourceManager.getImage(CotrolDecorationDemo1.class, "/icons/font.JPG"));
		controlDecoration_1.setDescriptionText("Some description");

	}
}
