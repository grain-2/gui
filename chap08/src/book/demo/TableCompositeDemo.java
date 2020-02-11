package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.layout.TableColumnLayout;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.swt.widgets.TableItem;

public class TableCompositeDemo {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableCompositeDemo window = new TableCompositeDemo();
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
		
		Composite composite = new Composite(shell, SWT.NONE);
		TableColumnLayout tcl_composite = new TableColumnLayout();
		composite.setLayout(tcl_composite);
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn, new ColumnPixelData(89, true, true));
		tableColumn.setText("学号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_1, new ColumnPixelData(103, true, true));
		tableColumn_1.setText("姓名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_2, new ColumnPixelData(48, true, true));
		tableColumn_2.setText("性别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tcl_composite.setColumnData(tableColumn_3, new ColumnPixelData(81, true, true));
		tableColumn_3.setText("年龄");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setText(new String[] {"10001", "张三", "男", "21"});
		tableItem.setText("10001");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] {"10002", "李四", "女", "19"});
		tableItem_1.setText("10002");

	}

}
