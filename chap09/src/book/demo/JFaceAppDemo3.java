package book.demo;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StyledText;

public class JFaceAppDemo3 extends ApplicationWindow {
	private StyleRange sr;
	private FontData fontData;
	private StyledText styledText;

	/**
	 * Create the application window,
	 */
	public JFaceAppDemo3() {
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
			styledText.setBounds(10, 10, 330, 125);
		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		{
			MenuManager menuManager_1 = new MenuManager("New MenuManager");
			menuManager_1.setMenuText("格式");
			menuManager.add(menuManager_1);
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
			JFaceAppDemo3 window = new JFaceAppDemo3();
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
	public class TaFontBoldAction extends Action {

		public TaFontBoldAction() {
			super();
			this.setText("粗体(&B) @Ctrl+B");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/bold.jpg"));
			this.setToolTipText("设置粗体字显示");
			this.setChecked(false);
		}

		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			this.setChecked(this.isChecked());
			if (this.isChecked()) {
				if (fontData.getStyle() == SWT.ITALIC) {
					fontData.setStyle(SWT.ITALIC | SWT.BOLD);
				} else {
					fontData.setStyle(SWT.BOLD);
				}
			} else {
				if (fontData.getStyle() == SWT.ITALIC || fontData.getStyle() == (SWT.ITALIC | SWT.BOLD)) {
					fontData.setStyle(SWT.ITALIC);
				} else {
					fontData.setStyle(SWT.NORMAL);
				}
			}		
			sr.font = new Font(null, fontData);
			styledText.setStyleRange(sr);
		}
	}
	
	public class TaFontItalicAction extends Action {

		public TaFontItalicAction() {
			super();
			this.setText("斜体(&I) @Ctrl+I");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/italic.jpg"));
			this.setToolTipText("设置斜体字显示");		
			this.setChecked(false);
		}

		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			this.setChecked(this.isChecked());
			if (this.isChecked()) {
				if (fontData.getStyle() == SWT.BOLD) {
					fontData.setStyle(SWT.ITALIC | SWT.BOLD);
				} else {
					fontData.setStyle(SWT.ITALIC);
				}
			} else {
				if (fontData.getStyle() == SWT.BOLD || fontData.getStyle() == (SWT.ITALIC | SWT.BOLD)) {
					fontData.setStyle(SWT.BOLD);
				} else {
					fontData.setStyle(SWT.NORMAL);
				}
			}		
			sr.font = new Font(null, fontData);
			styledText.setStyleRange(sr);	
		}
	}
}
