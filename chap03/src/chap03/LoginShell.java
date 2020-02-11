package chap03;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class LoginShell extends Shell {
	private Text textUser;
	private Text textPass;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			LoginShell shell = new LoginShell(display);
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
	public LoginShell(Display display) {
		super(display, SWT.SHELL_TRIM);
		setImage(SWTResourceManager.getImage(LoginShell.class, "/images/logo.jpg"));
		
		Label labelCaption = new Label(this, SWT.NONE);
		labelCaption.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelCaption.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		labelCaption.setBounds(48, 10, 348, 28);
		labelCaption.setText("学生成绩管理系统用户登录");
		
		Label labelT = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelT.setBounds(48, 46, 348, 24);
		labelT.setText("New Label");
		
		Label labelUser = new Label(this, SWT.NONE);
		labelUser.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelUser.setBounds(82, 76, 90, 24);
		labelUser.setText("用户名：");
		
		textUser = new Text(this, SWT.BORDER);
		textUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textUser.setBounds(178, 76, 167, 30);
		
		Label labelPass = new Label(this, SWT.NONE);
		labelPass.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelPass.setBounds(82, 118, 90, 24);
		labelPass.setText("密  码：");
		
		textPass = new Text(this, SWT.BORDER);
		textPass.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textPass.setBounds(178, 112, 167, 30);
		
		Label labelActorImg = new Label(this, SWT.NONE);
		labelActorImg.setImage(SWTResourceManager.getImage(LoginShell.class, "/images/actor.jpg"));
		labelActorImg.setBounds(106, 157, 21, 24);
		
		Label labelActor = new Label(this, SWT.NONE);
		labelActor.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelActor.setBounds(133, 157, 100, 24);
		labelActor.setText("我是一个");
		
		Combo comboActor = new Combo(this, SWT.NONE);
		comboActor.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		comboActor.setTextLimit(10);
		comboActor.setVisibleItemCount(2);
		comboActor.setItems(new String[] {"学生", "教师", "管理员"});
		comboActor.setBounds(239, 149, 97, 32);
		comboActor.select(0);
		comboActor.setText("?");
		
		Button buttonOK = new Button(this, SWT.NONE);
		buttonOK.setImage(SWTResourceManager.getImage(LoginShell.class, "/images/ok.JPG"));
		buttonOK.setBounds(94, 201, 114, 34);
		buttonOK.setText("登录");
		
		Button buttonCancel = new Button(this, SWT.NONE);
		buttonCancel.setImage(SWTResourceManager.getImage(LoginShell.class, "/images/cancel.JPG"));
		buttonCancel.setBounds(249, 201, 114, 34);
		buttonCancel.setText("取消");
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
