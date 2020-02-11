package chap04;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ExSeleEvent {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExSeleEvent window = new ExSeleEvent();
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
		
		text = new Text(shell, SWT.BORDER);
		text.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				MessageDialog.openInformation(shell, "文本框内容", text.getText()) ;
				text.selectAll() ;
			}
		});
		text.setBounds(43, 60, 243, 30);

		MyListener listener = new MyListener();
		text.addListener(SWT.MouseDoubleClick, listener);
		text.addListener(SWT.KeyUp, listener);

	}
	
	class MyListener implements Listener {
		public void handleEvent(Event event) {
			if (event.type == SWT.KeyUp) {
				switch (event.keyCode) {
					case SWT.F6:
						text.append("姓名：");
						break;
					case SWT.F7:
						text.append("年龄：");
						break;
					case SWT.F8:
						text.append("家庭地址：");
						break;
				}
			} else if(event.type==SWT.MouseDoubleClick) {
				text.setText("") ;
			}
		}
	}

}
