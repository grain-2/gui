package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

import book.stdscore.data.User;
import book.stdscore.data.UsersSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class UserLogin {

	protected Shell shell;
	private Text textUser;
	private Text textPass;
	private Combo comboActor;

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
		shell.setLayout(new GridLayout(5, false));
		
		Label lt = new Label(shell, SWT.NONE);
		lt.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		
		Label labelCaption = new Label(shell, SWT.NONE);
		labelCaption.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		labelCaption.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		labelCaption.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelCaption.setText("学生成绩管理系统用户登录");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelT = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelT.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		labelT.setText("New Label");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelUser = new Label(shell, SWT.HORIZONTAL);
		labelUser.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelUser.setText("用户名：");
		
		textUser = new Text(shell, SWT.BORDER);
		textUser.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		textUser.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textUser.setTextLimit(14);
		textUser.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelPass = new Label(shell, SWT.NONE);
		labelPass.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelPass.setText("密  码：");
		
		textPass = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textPass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 2, 1));
		textPass.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textPass.setTextLimit(14);
		textPass.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textPass.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelActorImg = new Label(shell, SWT.NONE);
		labelActorImg.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelActorImg.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/actor.jpg"));
		
		Label labelActor = new Label(shell, SWT.NONE);
		labelActor.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelActor.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelActor.setText("我是一个");
		
		comboActor = new Combo(shell, SWT.NONE);
		comboActor.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		comboActor.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		comboActor.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		comboActor.setTextLimit(10);
		comboActor.setVisibleItemCount(2);
		comboActor.setItems(new String[] {"学生", "教师", "管理员"});
		comboActor.setText("？");
		comboActor.select(0);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button buttonOK = new Button(shell, SWT.CENTER);
		buttonOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				User user = new User(textUser.getText().trim(),
						textPass.getText().trim(), comboActor.getSelectionIndex());
						Shell oldShell = null ;
						if(new UsersSet().isValid(user)) {
							shell.dispose() ;
							ScoreMana sm = new ScoreMana(user) ;
							sm.open();
							shell = sm.getShell() ;							
						} else {
							textUser.setText("") ;
							textPass.setText("") ;
						}
				
			}
		});
		buttonOK.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/ok.JPG"));
		buttonOK.setText("登录");
		
		Button buttonCancel = new Button(shell, SWT.NONE);
		buttonCancel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		buttonCancel.setImage(SWTResourceManager.getImage(UserLogin.class, "/images/cancel.JPG"));
		buttonCancel.setText("取消");
		
		Label rb = new Label(shell, SWT.NONE);
		rb.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		/**
		 * 匿名内部监听器类，实现ModifyListener接口
		 */
		/*
		textUser.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String txt=textUser.getText() == null ? "":textUser.getText().trim();
				if(!"".equals(txt) && !txt.matches("[\\w]+")) {
					MessageBox msgBox = new MessageBox(shell);
					msgBox.setMessage("有非法字符！\n用户名只能由字母、数字和下划线构成。");
					msgBox.open();
					textUser.setText("") ;
				}
			}
		});
		*/
		/**
		 * 使用实名内部监听器类MyModifyListener
		 */
		/*
		String usrR = "[\\w]+" ;
		String usrG = "有非法字符！\n用户名只能由字母、数字和下划线构成。" ;
		MyModifyListener textUserListener = new MyModifyListener(usrR, usrG) ;
		textUser.addModifyListener(textUserListener) ;
		 */
		/**
		 * 使用外部独立监听器类MyModifyListener
		 */
		String usrR = "[\\w]+" ;
		String usrG = "有非法字符！\n用户名只能由字母、数字和下划线构成。" ;
		MyModifyListener textUserListener = new MyModifyListener(shell, usrR, usrG) ;
		textUser.addModifyListener(textUserListener) ;
		/**
		 * 使用实名内部监听器类MyModifyListener
		 */
		/*
		String actR = "[学生教师管理员]*+" ;
		String actG  = "有非法字符！\n只能输入【学生教师管理员】中的字或词。" ;
		MyModifyListener myModifyListener = new MyModifyListener(actR, actG) ;
		comboActor.addModifyListener(myModifyListener) ;
		*/
		/**
		 * 使用外部独立监听器类MyModifyListener
		 */
		String actR = "[学生教师管理员]*+" ;
		String actG  = "有非法字符！\n只能输入【学生教师管理员】中的字或词。" ;
		MyModifyListener myModifyListener = new MyModifyListener(shell, actR, actG) ;
		comboActor.addModifyListener(myModifyListener) ;

	}
	/**
	 * 定义实名内部监听器类MyModifyListener
	 */
	/*
	class MyModifyListener implements ModifyListener {
		String regex = null ;
		String guide = null ;
		MyModifyListener(String regex,String guide) {
			this.regex = regex ;
			this.guide = guide ;
		}
		public void modifyText(ModifyEvent e) {
			String[] cn = e.getSource().toString().split(" ") ;
			if(cn[0].equals("Text")) {
				String txt = ((Text)e.getSource()).getText()==null?"":((Text)e.getSource()).getText().trim();
				if(!"".equals(txt) && !isValidChar(txt)) {
					((Text)e.getSource()).setText("") ;
				}
			} else if(cn[0].equals("Combo")) {
				String txt = ((Combo)e.getSource()).getText()==null?"":((Combo)e.getSource()).getText().trim();
				if(!"?".equals(txt) && !isValidChar(txt)) {
					((Combo)e.getSource()).setText("?") ;
				}
			}
		}
		boolean isValidChar(String cont) {
			boolean isV = true ;
			if(!cont.matches(this.regex)) {
				MessageBox msgBox = new MessageBox(shell);
				msgBox.setMessage(this.guide);
				msgBox.open();
				isV = false ;
			}
			return isV ;
		}
	}
	 */
}
