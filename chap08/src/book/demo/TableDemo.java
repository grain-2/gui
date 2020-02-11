package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.wb.swt.SWTResourceManager;

public class TableDemo {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableDemo window = new TableDemo();
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
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("学号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setImage(SWTResourceManager.getImage(TableDemo.class, "/book/demo/bug.jpg"));
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("姓名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("性别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("年龄");
		
		TableItem tableItem = new TableItem(table, SWT.NONE);
		tableItem.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
		tableItem.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		tableItem.setFont(SWTResourceManager.getFont("楷体", 9, SWT.NORMAL));
		tableItem.setImage(SWTResourceManager.getImage(TableDemo.class, "/book/demo/bug.jpg"));
		tableItem.setChecked(true);
		tableItem.setText(new String[] {"10001", "张三", "男", "21"});
		tableItem.setText("10001");
		
		TableItem tableItem_1 = new TableItem(table, SWT.NONE);
		tableItem_1.setText(new String[] {"10002", "李四", "女", "20"});
		tableItem_1.setText("10002");
		
		TableCursor tableCursor = new TableCursor(table, SWT.BORDER);
		tableCursor.setFont(SWTResourceManager.getFont("华文琥珀", 9, SWT.NORMAL));
		tableCursor.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		tableCursor.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));

	}

}
