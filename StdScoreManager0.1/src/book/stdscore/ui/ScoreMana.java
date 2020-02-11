package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import book.stdscore.data.User;

public class ScoreMana {

	protected Shell shell;
	private User user;

	public Shell getShell() {
		return shell;
	}

	public ScoreMana(User user) {
		super();
		this.user = user;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		try {
			ScoreMana window = new ScoreMana();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
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

	}
}
