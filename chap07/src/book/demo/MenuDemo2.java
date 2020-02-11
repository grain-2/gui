package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class MenuDemo2 {

	protected Shell shell;
	private Text textArea;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MenuDemo2 window = new MenuDemo2();
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
		shell.setLayout(new GridLayout(1, false));
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("留言：");
		
		textArea = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textArea.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Menu menuPopup = new Menu(textArea);
		textArea.setMenu(menuPopup);
		
		MenuItem pushSelAll = new MenuItem(menuPopup, SWT.CHECK);
		pushSelAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(pushSelAll.getSelection()) { // 该复选菜单项被选择
					textArea.selectAll();
				} else {  // 该复选菜单项未被选择
					textArea.clearSelection();
				}
			}
		});
		pushSelAll.setAccelerator(SWT.CTRL+'A');
		pushSelAll.setText("全选\tCtrl+A");
		
		new MenuItem(menuPopup, SWT.SEPARATOR);
		
		MenuItem menuItemEdit = new MenuItem(menuPopup, SWT.CASCADE);
		menuItemEdit.setText("编辑");
		
		Menu menuEdit = new Menu(menuItemEdit);
		menuItemEdit.setMenu(menuEdit);
		
		MenuItem pushCopy = new MenuItem(menuEdit, SWT.NONE);
		pushCopy.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textArea.copy();
			}
		});
		pushCopy.setText("复制");
		
		MenuItem pushPaste = new MenuItem(menuEdit, SWT.NONE);
		pushPaste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textArea.paste();
			}
		});
		pushPaste.setText("粘贴");
		
		MenuItem pushCut = new MenuItem(menuEdit, SWT.NONE);
		pushCut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textArea.cut();
			}
		});
		pushCut.setText("剪切");
		
		Button button = new Button(shell, SWT.NONE);
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		button.setText("提交");

	}
}
