package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableScore {

	protected Shell shell;
	private Table table;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TableScore window = new TableScore();
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
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(120);
		tableColumn.setText("学号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(80);
		tableColumn_1.setText("姓名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setMoveable(true);
		tableColumn_2.setWidth(140);
		tableColumn_2.setText("课程名");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.RIGHT);
		tableColumn_3.setWidth(60);
		tableColumn_3.setText("成绩");
		ArrayList<String[]> scoreList = getScoreList();
		TableItem item;
		for(String[] strArr : scoreList) {
			item = new TableItem(table, SWT.NONE);
			item.setText(strArr);			
		}
	}
	
	public ArrayList getScoreList() {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		try {
			FileReader fr = new FileReader("score.csv");
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			String[] strArr;
			while(str!=null) {
				strArr = str.split(",");
				scoreList.add(strArr);
				str = br.readLine();
			}
			if(br!=null)
				br.close();
			if(fr!=null)
				fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 		
		return scoreList;
	}

}
