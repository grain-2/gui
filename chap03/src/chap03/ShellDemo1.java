package chap03;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;

public class ShellDemo1 extends Shell {
	private Text text;
	private Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			ShellDemo1 shell = new ShellDemo1(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public ShellDemo1(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Label label = new Label(this, SWT.NONE);
		label.setText("学生成绩管理系统用户登录");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		label.setBounds(45, 10, 348, 33);
		
		Label label_1 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setText("New Label");
		label_1.setBounds(45, 49, 348, 24);
		
		Label label_2 = new Label(this, SWT.HORIZONTAL);
		label_2.setText("用户名：");
		label_2.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_2.setBounds(94, 80, 101, 26);
		
		text = new Text(this, SWT.BORDER);
		text.setTextLimit(14);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text.setBounds(201, 79, 152, 30);
		
		Label label_3 = new Label(this, SWT.NONE);
		label_3.setText("密  码：");
		label_3.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_3.setBounds(95, 123, 100, 24);
		
		text_1 = new Text(this, SWT.BORDER | SWT.PASSWORD);
		text_1.setTextLimit(14);
		text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_1.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text_1.setBounds(201, 117, 152, 30);
		
		Label label_4 = new Label(this, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(ShellDemo1.class, "/images/actor.jpg"));
		label_4.setBounds(109, 166, 21, 24);
		
		Label label_5 = new Label(this, SWT.NONE);
		label_5.setText("我是一个");
		label_5.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_5.setBounds(136, 164, 100, 24);
		
		Combo combo = new Combo(this, SWT.NONE);
		combo.setVisibleItemCount(2);
		combo.setTextLimit(10);
		combo.setItems(new String[] {"学生", "教师", "管理员"});
		combo.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		combo.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		combo.setBounds(244, 165, 97, 28);
		combo.setText("？");
		combo.select(0);
		
		Button button = new Button(this, SWT.CENTER);
		button.setText("登录");
		button.setImage(SWTResourceManager.getImage(ShellDemo1.class, "/images/ok.JPG"));
		button.setBounds(84, 201, 114, 34);
		
		Button button_1 = new Button(this, SWT.NONE);
		button_1.setText("取消");
		button_1.setImage(SWTResourceManager.getImage(ShellDemo1.class, "/images/cancel.JPG"));
		button_1.setBounds(253, 201, 114, 34);
		createContents();
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("用户登录");
		setSize(450, 300);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
