package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AdminScoreMana {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AdminScoreMana window = new AdminScoreMana();
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
		shell.setText("系统管理");
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(10, 10, 371, 112);
		
		ToolItem toolItemDeptSet = new ToolItem(toolBar, SWT.NONE);
		toolItemDeptSet.setEnabled(false);
		toolItemDeptSet.setToolTipText("专业与课程设置");
		toolItemDeptSet.setImage(SWTResourceManager.getImage(AdminScoreMana.class, "/images/dept.JPG"));
		
		ToolItem toolItemUsersReg = new ToolItem(toolBar, SWT.NONE);
		toolItemUsersReg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new RegisterTabFolder().getShell().open();
			}
		});
		toolItemUsersReg.setToolTipText("用户注册");
		toolItemUsersReg.setImage(SWTResourceManager.getImage(AdminScoreMana.class, "/images/register.JPG"));
		
		ToolItem toolItemStdSet = new ToolItem(toolBar, SWT.NONE);
		toolItemStdSet.setToolTipText("分派学生");
		toolItemStdSet.setImage(SWTResourceManager.getImage(AdminScoreMana.class, "/images/asignstd.JPG"));
		
		Label label = new Label(shell, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_DARK_GREEN));
		label.setAlignment(SWT.CENTER);
		label.setFont(SWTResourceManager.getFont("楷体", 12, SWT.BOLD));
		label.setBounds(10, 150, 408, 24);
		label.setText("欢迎使用简易学生成绩管理系统");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label_1.setFont(SWTResourceManager.getFont("黑体", 14, SWT.BOLD));
		label_1.setBounds(156, 196, 123, 38);
		label_1.setText("系统管理");

	}
}
