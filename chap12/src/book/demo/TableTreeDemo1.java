package book.demo;

import org.eclipse.jface.viewers.TableTreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class TableTreeDemo1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableTreeDemo1 window = new TableTreeDemo1();
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
		
		TableTreeViewer tableTreeViewer = new TableTreeViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		TableTree tableTree = tableTreeViewer.getTableTree();
		tableTree.getTable().setLinesVisible(true);
		tableTree.getTable().setHeaderVisible(true);
		//tableTreeViewer.setInput(new SelCourseTreeRoot());

	}

}
