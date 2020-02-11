package book.stdscore.ui;

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
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;

import book.stdscore.data.SelCourseTreeRoot;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;

public class TreeViewerDemo1 extends ApplicationWindow {
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	/**
	 * Create the application window.
	 */
	public TreeViewerDemo1() {
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
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			TreeViewer treeViewer = new TreeViewer(container, SWT.BORDER);
			treeViewer.setUseHashlookup(true);
			Tree tree = treeViewer.getTree();
			treeViewer.setLabelProvider(new ViewerLabelProvider());

			treeViewer.setContentProvider(new TreeNodeContentProvider(){
/*
				@Override
				public Object[] getChildren(Object parentElement) {
					return ((TreeNode) parentElement).getChildren();
				}
*/
				@Override
				public Object[] getElements(Object inputElement) {
					return ((TreeNode) inputElement).getChildren();
				}
/*
				@Override
				public Object getParent(Object element) {
					return ((TreeNode) element).getParent();
				}
				@Override
				public boolean hasChildren(Object element) {
					return ((TreeNode) element).hasChildren();
				}
*/
			});
			
/*			
			treeViewer.setLabelProvider(new ILabelProvider(){

				@Override
				public void addListener(ILabelProviderListener arg0) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public void dispose() {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public boolean isLabelProperty(Object arg0, String arg1) {
					// TODO 自动生成的方法存根
					return false;
				}

				@Override
				public void removeListener(ILabelProviderListener arg0) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public Image getImage(Object arg0) {
					// TODO 自动生成的方法存根
					return null;
				}

				@Override
				public String getText(Object arg0) {
					// TODO 自动生成的方法存根
				//	if (arg0 instanceof TreeNode) {
						((TreeNode) arg0).getValue().toString();
				//	}
					return arg0.toString();
				}
				
			});
*/
			treeViewer.setInput(new SelCourseTreeRoot());
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
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
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
			TreeViewerDemo1 window = new TreeViewerDemo1();
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
