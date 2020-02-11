package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;

import book.stdscore.data.SelCourseTreeRoot;

import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.ITreeViewerListener;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.wb.swt.SWTResourceManager;

public class CheckboxTreeViewerDemo1 {
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return element.toString();
		}
	}
	private static class TreeContentProvider implements ITreeContentProvider {
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
		public void dispose() {
		}
		public Object[] getElements(Object inputElement) {
			//return getChildren(inputElement);
			return ((TreeNode) inputElement).getChildren();
		}
		public Object[] getChildren(Object parentElement) {
			//return new Object[] { "item_0", "item_1", "item_2" };
			return ((TreeNode) parentElement).getChildren();
		}
		public Object getParent(Object element) {
			//return null;
			return ((TreeNode) element).getParent();
		}
		public boolean hasChildren(Object element) {
			//return getChildren(element).length > 0;
			return ((TreeNode) element).hasChildren();
		}
	}

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			CheckboxTreeViewerDemo1 window = new CheckboxTreeViewerDemo1();
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
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		CheckboxTreeViewer checkboxTreeViewer = new CheckboxTreeViewer(shell, SWT.BORDER);
		checkboxTreeViewer.addCheckStateListener(new ICheckStateListener() {
			public void checkStateChanged(CheckStateChangedEvent arg0) {
				TreeNode item = (TreeNode) arg0.getElement();
				if(arg0.getChecked()) {
					checkboxTreeViewer.setSubtreeChecked(item, true);
					if(item.hasChildren())
						expandSubtree(item);
					
				} else {
					checkboxTreeViewer.setSubtreeChecked(item, false);
					if(item.hasChildren())
						collapseSubtree(item);
				}
				
			}
			
			void expandSubtree(TreeNode item) {
				checkboxTreeViewer.setExpandedState(item, true);
				TreeNode[] nodes = item.getChildren();
				for(TreeNode node : nodes) {
					if(node.hasChildren())
						expandSubtree(node);
				}
			}
			
			void collapseSubtree(TreeNode item) {
				checkboxTreeViewer.setExpandedState(item, false);
				TreeNode[] nodes = item.getChildren();
				for(TreeNode node : nodes) {
					if(node.hasChildren())
						collapseSubtree(node);
				}				
			}
			
		});
		Tree tree = checkboxTreeViewer.getTree();
		checkboxTreeViewer.setLabelProvider(new ViewerLabelProvider());
		checkboxTreeViewer.setContentProvider(new TreeContentProvider());
		checkboxTreeViewer.setInput(new SelCourseTreeRoot());

	}

}
