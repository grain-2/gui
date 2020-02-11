package chap05;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;

public class StackLayoutDemo {

	protected Shell shell;
	static int i = 0 ;
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StackLayoutDemo window = new StackLayoutDemo();
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
		shell.setModified(true);
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		final StackLayout stackLayout = new StackLayout();
		shell.setLayout(stackLayout);
		
		Button button1 = new Button(shell, SWT.NONE);
		button1.setSelection(true);
		button1.setText("New Button1");
		
		Button button2 = new Button(shell, SWT.NONE);
		button2.setText("New Button2");
		shell.setTabList(new Control[]{button1, button2});
		
		Button button3 = new Button(shell, SWT.NONE);
		button3.setText("New Button3");

		stackLayout.topControl = button1;

		MySelectionListener mySelectionListener = new MySelectionListener(stackLayout) ;
		button1.addSelectionListener(mySelectionListener);
		button2.addSelectionListener(mySelectionListener);
		button3.addSelectionListener(mySelectionListener);				
	}
	
	class MySelectionListener extends SelectionAdapter {
		StackLayout stackLayout = null ;
		MySelectionListener(StackLayout stackLayout) {
			super();
			this.stackLayout = stackLayout;
		}
		public void widgetSelected(SelectionEvent e) {
			Control[] child = shell.getChildren() ;
			if(i < child.length-1 ){
				i++ ;
			} else {
				i = 0 ;
			}
			stackLayout.topControl = child[i] ; //堆栈顶为下一个按钮
			shell.layout() ;				// 显示栈顶的按钮
		}
	}

}
