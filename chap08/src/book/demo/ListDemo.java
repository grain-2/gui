package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

public class ListDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ListDemo window = new ListDemo();
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
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(30, 27, 89, 189);
		
		ListViewer listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		List list_1 = listViewer.getList();
		list_1.setBounds(189, 27, 112, 157);
		listViewer.setLabelProvider(new LabelProvider(){

			@Override
			public String getText(Object element) {
				// TODO 自动生成的方法存根
				return (String)element;
			}
			
		});
		listViewer.setContentProvider(new ArrayContentProvider(){

			@Override
			public Object[] getElements(Object inputElement) {
				// TODO 自动生成的方法存根
				String[] objs = (String[])inputElement;
				return objs;
			}
			
		});

		listViewer.setInput(new String[]{"1","2","3","4","5"});
	}
}
