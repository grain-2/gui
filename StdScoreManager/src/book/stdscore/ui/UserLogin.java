package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

public class UserLogin {

	protected Shell shell;
	private Text textUser;
	private Text textPass;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UserLogin window = new UserLogin();
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
/*
		Control chls[] = shell.getChildren();
		for(Control chl:chls) {
			if(chl instanceof Combo) {
				Combo comp = (Combo)chl;
				textUser.setText(comp.getText());
			}
		}
*/		
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
		shell.setToolTipText("请输入用户名和密码");
		shell.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/logo.jpg"));
		shell.setSize(new Point(450, 350));
		shell.setLocation(new Point(400, 300));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		shell.setMinimumSize(new Point(450, 350));
		shell.setSize(450, 300);
		shell.setText("用户登录");
		
		Label labelUser = new Label(shell, SWT.HORIZONTAL);
		labelUser.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelUser.setBounds(69, 96, 101, 26);
		labelUser.setText("用户名：");
		
		Label labelCaption = new Label(shell, SWT.NONE);
		labelCaption.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		labelCaption.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelCaption.setBounds(36, 22, 348, 33);
		labelCaption.setText("学生成绩管理系统用户登录");
		
		Label labelT = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelT.setBounds(36, 61, 348, 24);
		labelT.setText("New Label");
		
		Label labelPass = new Label(shell, SWT.NONE);
		labelPass.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelPass.setBounds(70, 139, 100, 24);
		labelPass.setText("密  码：");
		
		Label labelActor = new Label(shell, SWT.NONE);
		labelActor.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelActor.setBounds(123, 184, 100, 24);
		labelActor.setText("我是一个");
		
		Label labelActorImg = new Label(shell, SWT.NONE);
		labelActorImg.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/actor.jpg"));
		labelActorImg.setBounds(96, 186, 21, 24);
		
		Button buttonOK = new Button(shell, SWT.CENTER);
		buttonOK.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/ok.JPG"));
		buttonOK.setBounds(84, 240, 114, 34);
		buttonOK.setText("登录");
		
		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/cancel.JPG"));
		buttonCancel.setBounds(253, 240, 114, 34);
		buttonCancel.setText("取消");
		
		textUser = new Text(shell, SWT.BORDER);
		textUser.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textUser.setTextLimit(14);
		textUser.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textUser.setBounds(176, 95, 152, 30);
		
		textPass = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textPass.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textPass.setTextLimit(14);
		textPass.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textPass.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textPass.setBounds(176, 133, 152, 30);
		
		Combo comboActor = new Combo(shell, SWT.NONE);
		comboActor.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		comboActor.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		comboActor.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		comboActor.setTextLimit(10);
		comboActor.setVisibleItemCount(2);
		comboActor.setItems(new String[] {"学生", "教师", "管理员"});
		comboActor.setBounds(231, 185, 97, 32);
		comboActor.setText("？");
		comboActor.select(0);

	}
}