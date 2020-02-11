package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.events.TreeAdapter;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class TreeDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TreeDemo window = new TreeDemo();
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
		
		Tree tree = new Tree(shell, SWT.BORDER | SWT.CHECK);
		tree.setHeaderVisible(true);
		tree.setBounds(25, 20, 373, 215);
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("计算机科学与技术");
		
		TreeItem treeItem_6 = new TreeItem(treeItem, SWT.NONE);
		treeItem_6.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		treeItem_6.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		treeItem_6.setFont(SWTResourceManager.getFont("华文行楷", 9, SWT.NORMAL));
		treeItem_6.setText("2013级");
		
		TreeItem treeItem_7 = new TreeItem(treeItem_6, SWT.NONE);
		treeItem_7.setGrayed(true);
		treeItem_7.setChecked(true);
		treeItem_7.setImage(SWTResourceManager.getImage(TreeDemo.class, "/book/demo/bug.jpg"));
		treeItem_7.setText(new String[] {});
		treeItem_7.setText("1班");
		
		TreeItem treeItem_8 = new TreeItem(treeItem_6, SWT.NONE);
		treeItem_8.setText(new String[] {"2班", "三校生"});
		treeItem_6.setExpanded(true);
		
		TreeItem treeItem_3 = new TreeItem(treeItem, SWT.NONE);
		treeItem_3.setText("2014级");
		
		TreeItem treeItem_9 = new TreeItem(treeItem_3, SWT.NONE);
		treeItem_9.setText("1班");
		
		TreeItem treeItem_10 = new TreeItem(treeItem_3, SWT.NONE);
		treeItem_10.setText("2班");
		treeItem_3.setExpanded(true);
		
		TreeItem treeItem_4 = new TreeItem(treeItem, SWT.NONE);
		treeItem_4.setText("2015级");
		
		TreeItem treeItem_5 = new TreeItem(treeItem, SWT.NONE);
		treeItem_5.setText("2016级");
		treeItem.setExpanded(true);
		
		TreeItem treeItem_1 = new TreeItem(tree, SWT.NONE);
		treeItem_1.setText("软件工程");
		
		TreeItem treeItem_2 = new TreeItem(tree, SWT.NONE);
		treeItem_2.setText("网络工程");
		
	}
}
