package book.demo;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
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
import org.eclipse.wb.swt.ResourceManager;

public class JFaceAppDemo2 extends ApplicationWindow {
	private Action actionBold;
	private Action action_1;
	private StyleRange sr;
	private FontData fontData;

	/**
	 * Create the application window,
	 */
	public JFaceAppDemo2() {
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

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			actionBold = new Action("粗体(&B)") {
			};
			actionBold.setToolTipText("粗体显示文字");
			actionBold.setChecked(true);
			actionBold.setHoverImageDescriptor(ResourceManager.getImageDescriptor(JFaceAppDemo2.class, "/com/sun/java/swing/plaf/windows/icons/Inform.gif"));
			actionBold.setDisabledImageDescriptor(ResourceManager.getImageDescriptor(JFaceAppDemo2.class, "/com/sun/java/swing/plaf/windows/icons/Question.gif"));
			actionBold.setImageDescriptor(ResourceManager.getImageDescriptor(JFaceAppDemo2.class, "/com/sun/java/swing/plaf/windows/icons/Computer.gif"));
			actionBold.setActionDefinitionId("粗体显示文本框中的文字");
			actionBold.setId("Bold");
			actionBold.setDescription("粗体显示文字");
			actionBold.setAccelerator(SWT.CTRL | 'B');
		}
		{
			action_1 = new Action("New Action") {
			};
		}
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		
		MenuManager menuManager_1 = new MenuManager("New MenuManager");
		menuManager.add(menuManager_1);
		menuManager_1.add(actionBold);
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * @return the coolbar manager
	 */
	@Override
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		ToolBarManager toolBarManager1 = new ToolBarManager();
		coolBarManager.add(toolBarManager1);
		ToolBarManager toolBarManager2 = new ToolBarManager();
		coolBarManager.add(toolBarManager2);
		toolBarManager1.add(new ExAction("按钮1"));
		toolBarManager1.add(new ExAction("按钮2"));
		toolBarManager1.add(new ExAction("按钮3"));
		toolBarManager1.add(action_1);
		toolBarManager1.add(actionBold);
		toolBarManager2.add(new ExAction("按钮4"));
		toolBarManager2.add(new ExAction("按钮5"));
		return coolBarManager;
	}

	class ExAction extends Action {
		private String text;

		public ExAction(String text) {
			super(text);
			// TODO 自动生成的构造函数存根
			this.text = text;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			setStatus("例子按钮 " + text);
		}

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
			JFaceAppDemo2 window = new JFaceAppDemo2();
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
			//textArea.setStyleRange(sr);
		}
	}
	
}
