package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

public class TreeColumnDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TreeColumnDemo window = new TreeColumnDemo();
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
		
		Tree tree = new Tree(shell, SWT.BORDER);
		tree.setLinesVisible(true);
		tree.setHeaderVisible(true);
		tree.setBounds(27, 10, 380, 235);
		
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(200);
		treeColumn.setText("班级");
		
		TreeColumn treeColumn_1 = new TreeColumn(tree, SWT.NONE);
		treeColumn_1.setWidth(60);
		treeColumn_1.setText("人数");
		
		TreeColumn treeColumn_2 = new TreeColumn(tree, SWT.NONE);
		treeColumn_2.setWidth(100);
		treeColumn_2.setText("班主任");
		
		TreeItem treeItem = new TreeItem(tree, SWT.NONE);
		treeItem.setText("计算机科学与技术");
		
		TreeItem treeItem_1 = new TreeItem(treeItem, SWT.NONE);
		treeItem_1.setText(new String[] {"2013级", "80"});
		treeItem_1.setText("2013级");
		
		TreeItem treeItem_4 = new TreeItem(treeItem_1, SWT.NONE);
		treeItem_4.setText(new String[] {"1班", "38", "李志"});
		treeItem_4.setText("1班");
		
		TreeItem treeItem_5 = new TreeItem(treeItem_1, SWT.NONE);
		treeItem_5.setText(new String[] {"2班", "42", "王玲"});
		treeItem_5.setText("2班");
		treeItem_1.setExpanded(true);
		
		TreeItem treeItem_2 = new TreeItem(treeItem, SWT.NONE);
		treeItem_2.setText(new String[] {"2014级", "85"});
		treeItem_2.setText("2014级");
		
		TreeItem treeItem_6 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_6.setText(new String[] {"1班", "45", "张宁"});
		treeItem_6.setText("1班");
		
		TreeItem treeItem_7 = new TreeItem(treeItem_2, SWT.NONE);
		treeItem_7.setText(new String[] {"2班", "40", "薛燕萍"});
		treeItem_7.setText("2班");
		treeItem_2.setExpanded(true);
		
		TreeItem treeItem_3 = new TreeItem(treeItem, SWT.NONE);
		treeItem_3.setText("2015级");
		
		TreeItem treeItem_8 = new TreeItem(treeItem_3, SWT.NONE);
		treeItem_8.setText("1班");
		
		TreeItem treeItem_9 = new TreeItem(treeItem_3, SWT.NONE);
		treeItem_9.setText("2班");
		treeItem_3.setExpanded(true);
		treeItem.setExpanded(true);

	}
}
