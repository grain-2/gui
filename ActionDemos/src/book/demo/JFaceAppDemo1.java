package book.demo;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.action.ToolBarManager;

public class JFaceAppDemo1 extends ApplicationWindow {
	private StyleRange sr;
	private FontData fontData;
	private StyledText styledText;
	private TaFontItalicAction taFontItalicAction;
	private static JFaceAppDemo1 window;

	public StyledText getStyledText() {
		return styledText;
	}

	/**
	 * Create the application window,
	 */
	public JFaceAppDemo1() {
		super(null);
		createActions();
		addCoolBar(SWT.FLAT);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		{
			styledText = new StyledText(container, SWT.BORDER);
			styledText.setBounds(41, 10, 238, 114);
			sr = new StyleRange();
			fontData = Display.getCurrent().getSystemFont().getFontData()[0];

		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			taFontItalicAction = new TaFontItalicAction(window,Display.getCurrent());
		}
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		{
			MenuManager menuManagerFormat = new MenuManager("格式(&F)");
			menuManagerFormat.setVisible(true);
			menuManager.add(menuManagerFormat);
			menuManagerFormat.add(taFontItalicAction);
		}
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * @return the coolbar manager
	 */
	@Override
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		{
			ToolBarManager toolBarManager = new ToolBarManager();
			coolBarManager.add(toolBarManager);
		}
		return coolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			window = new JFaceAppDemo1();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}


	
}
