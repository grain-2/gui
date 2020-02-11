package book.demo;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.TreeItem;

public class JFaceAppDemo1 extends ApplicationWindow {
	private StyledText styledText;

	/**
	 * Create the application window.
	 */
	public JFaceAppDemo1() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		setStatus("状态栏普通信息");
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		
		Tree tree = new Tree(sashForm, SWT.BORDER);
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_3 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_3.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_4.setText("New TreeItem");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("New TreeItem");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_2.setText("New TreeItem");
		
		styledText = new StyledText(sashForm, SWT.BORDER);
		styledText.setText("一些文字信息。");
		sashForm.setWeights(new int[] {1, 1});

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
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.WRAP);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		statusLineManager.setErrorMessage("状态栏错误信息");
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			JFaceAppDemo1 window = new JFaceAppDemo1();
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
		newShell.setText("第一个JFace程序");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
