package chap03;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class ContentsMain1 {
	private static Text text;
	private static Text text_1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("学生成绩管理系统用户登录");
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		label.setBounds(40, 10, 348, 33);
		
		Label label_1 = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setText("New Label");
		label_1.setBounds(40, 38, 348, 24);
		
		Label label_2 = new Label(shell, SWT.HORIZONTAL);
		label_2.setText("用户名：");
		label_2.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_2.setBounds(84, 69, 101, 26);
		
		text = new Text(shell, SWT.BORDER);
		text.setTextLimit(14);
		text.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		text.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text.setBounds(191, 68, 152, 30);
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setText("密  码：");
		label_3.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_3.setBounds(85, 112, 100, 24);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setTextLimit(14);
		text_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		text_1.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		text_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		text_1.setBounds(191, 106, 152, 30);
		
		Label label_4 = new Label(shell, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(ContentsMain1.class, "/images/actor.jpg"));
		label_4.setBounds(102, 158, 21, 24);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setText("我是一个");
		label_5.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		label_5.setBounds(129, 156, 100, 24);
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setVisibleItemCount(2);
		combo.setTextLimit(10);
		combo.setItems(new String[] {"学生", "教师", "管理员"});
		combo.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		combo.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		combo.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		combo.setBounds(237, 157, 97, 28);
		combo.setText("？");
		combo.select(0);
		
		Button button = new Button(shell, SWT.CENTER);
		button.setText("登录");
		button.setImage(SWTResourceManager.getImage(ContentsMain1.class, "/images/ok.JPG"));
		button.setBounds(70, 201, 114, 34);
		
		Button button_1 = new Button(shell, SWT.NONE);
		button_1.setText("取消");
		button_1.setImage(SWTResourceManager.getImage(ContentsMain1.class, "/images/cancel.JPG"));
		button_1.setBounds(239, 201, 114, 34);

		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

}
