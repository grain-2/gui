package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.wb.swt.SWTResourceManager;

import book.stdscore.data.User;
import book.stdscore.data.UsersSet;

import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class ModifyPassword {

	protected Shell shell;
	private Text textUser;
	private Text textPass;
	private Combo comboActor;
	private User user;
	private Button buttonClose;
	private Text textPass2;

	public ModifyPassword(User user) {
		super();
		this.user = user;
	}

	public Shell getShell() {
		return shell;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ModifyPassword window = new ModifyPassword(null);
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
		shell.setImage(SWTResourceManager.getImage(ModifyPassword.class, "/images/logo.jpg"));
		shell.setSize(new Point(450, 500));
		shell.setLocation(new Point(400, 300));
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND));
		shell.setMinimumSize(new Point(450, 500));
		shell.setSize(450, 300);
		shell.setText("账户密码修改");
		shell.setLayout(new FormLayout());
		
		Label lt = new Label(shell, SWT.NONE);
		FormData fd_lt = new FormData();
		fd_lt.top = new FormAttachment(0, 28);
		fd_lt.left = new FormAttachment(0, 5);
		lt.setLayoutData(fd_lt);
		
		Label labelCaption = new Label(shell, SWT.NONE);
		labelCaption.setAlignment(SWT.CENTER);
		FormData fd_labelCaption = new FormData();
		fd_labelCaption.bottom = new FormAttachment(15, 30);
		fd_labelCaption.right = new FormAttachment(100, -127);
		fd_labelCaption.top = new FormAttachment(15);
		fd_labelCaption.left = new FormAttachment(0, 127);
		labelCaption.setLayoutData(fd_labelCaption);
		labelCaption.setFont(SWTResourceManager.getFont("楷体", 14, SWT.BOLD));
		labelCaption.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		labelCaption.setText("账户密码修改");
		
		Label labelT = new Label(shell, SWT.SEPARATOR | SWT.HORIZONTAL);
		FormData fd_labelT = new FormData();
		fd_labelT.top = new FormAttachment(labelCaption, 10);
		fd_labelT.right = new FormAttachment(92);
		fd_labelT.left = new FormAttachment(8);
		labelT.setLayoutData(fd_labelT);
		labelT.setText("New Label");
		
		Label labelUser = new Label(shell, SWT.HORIZONTAL);
		FormData fd_labelUser = new FormData();
		fd_labelUser.top = new FormAttachment(labelT, 20);
		fd_labelUser.left = new FormAttachment(15);
		labelUser.setLayoutData(fd_labelUser);
		labelUser.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelUser.setText("用户名：");
		
		textUser = new Text(shell, SWT.BORDER);
		textUser.setEditable(false);
		FormData fd_textUser = new FormData();
		fd_textUser.bottom = new FormAttachment(labelUser, 0, SWT.BOTTOM);
		fd_textUser.top = new FormAttachment(labelUser, 0, SWT.TOP);
		fd_textUser.left = new FormAttachment(labelUser, 10);
		fd_textUser.right = new FormAttachment(85);
		textUser.setLayoutData(fd_textUser);
		textUser.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textUser.setTextLimit(14);
		textUser.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textUser.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		textUser.setText(user.getName());
		
		Label labelPass = new Label(shell, SWT.NONE);
		FormData fd_labelPass = new FormData();
		fd_labelPass.top = new FormAttachment(labelUser, 20);
		fd_labelPass.left = new FormAttachment(labelUser, 0, SWT.LEFT);
		labelPass.setLayoutData(fd_labelPass);
		labelPass.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelPass.setText("密  码：");
		
		textPass = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		FormData fd_textPass = new FormData();
		fd_textPass.bottom = new FormAttachment(labelPass, 0, SWT.BOTTOM);
		fd_textPass.top = new FormAttachment(labelPass, 0, SWT.TOP);
		fd_textPass.left = new FormAttachment(labelPass, 10);
		fd_textPass.right = new FormAttachment(85);
		textPass.setLayoutData(fd_textPass);
		textPass.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textPass.setTextLimit(14);
		textPass.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textPass.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		
		textPass2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		textPass2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String pass1 = textPass.getText().trim();
				String pass2 = textPass2.getText().trim();
				if(pass2==null || "".equals(pass2) || !pass2.equals(pass1)) {
					MessageDialog.openError(shell, "密码错误", "密码输入不一致，或密码为空。请重新输入！");
					textPass.setText("");
					textPass2.setText("");
					textPass.setFocus();
				} else {
					user.setPassword(pass2);
				}
			}
		});
		textPass2.setTextLimit(14);
		textPass2.setForeground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_FOREGROUND));
		textPass2.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		textPass2.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		FormData fd_textPass2 = new FormData();
		fd_textPass2.right = new FormAttachment(textPass, 0, SWT.RIGHT);
		fd_textPass2.left = new FormAttachment(textPass, 0, SWT.LEFT);
		fd_textPass2.top = new FormAttachment(textPass, 20);
		textPass2.setLayoutData(fd_textPass2);
		
		Label labelActorImg = new Label(shell, SWT.NONE);
		labelActorImg.setAlignment(SWT.CENTER);
		FormData fd_labelActorImg = new FormData();
		fd_labelActorImg.top = new FormAttachment(textPass2, 30);
		fd_labelActorImg.left = new FormAttachment(labelPass, 30, SWT.LEFT);
		fd_labelActorImg.right = new FormAttachment(labelPass, -30, SWT.RIGHT);
		labelActorImg.setLayoutData(fd_labelActorImg);
		labelActorImg.setImage(SWTResourceManager.getImage(ModifyPassword.class, "/images/actor.jpg"));
		
		Label labelActor = new Label(shell, SWT.NONE);
		FormData fd_labelActor = new FormData();
		fd_labelActor.bottom = new FormAttachment(labelActorImg, 3, SWT.BOTTOM);
		fd_labelActor.top = new FormAttachment(labelActorImg, -3, SWT.TOP);
		fd_labelActor.left = new FormAttachment(labelActorImg, 10);
		labelActor.setLayoutData(fd_labelActor);
		labelActor.setFont(SWTResourceManager.getFont("幼圆", 12, SWT.BOLD));
		labelActor.setText("我是一个");
		
		comboActor = new Combo(shell, SWT.NONE);
		comboActor.setEnabled(false);
		FormData fd_comboActor = new FormData();
		fd_comboActor.top = new FormAttachment(labelActor, 0, SWT.TOP);
		fd_comboActor.right = new FormAttachment(textPass2, -20, SWT.RIGHT);
		fd_comboActor.bottom = new FormAttachment(labelActor, 0, SWT.BOTTOM);
		fd_comboActor.left = new FormAttachment(labelActor, 10);
		comboActor.setLayoutData(fd_comboActor);
		comboActor.setBackground(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		comboActor.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_FOREGROUND));
		comboActor.setFont(SWTResourceManager.getFont("宋体", 10, SWT.NORMAL));
		comboActor.setTextLimit(10);
		comboActor.setVisibleItemCount(2);
		comboActor.setItems(new String[] {"学生", "教师", "管理员"});
		comboActor.setText("？");
		comboActor.select(user.getJob());
		
		Button buttonSave = new Button(shell, SWT.CENTER);
		FormData fd_buttonSave = new FormData();
		fd_buttonSave.right = new FormAttachment(75, -130);
		fd_buttonSave.bottom = new FormAttachment(85);
		buttonSave.setLayoutData(fd_buttonSave);
		buttonSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//user.setPassword(textPass.getText().trim());
				int num = new UsersSet().modifyUser(user,user);
				if(num==1)
					MessageDialog.openInformation(shell, "修改成功", "用户 " + user.getName() +" 密码修改成功。");
				else
					MessageDialog.openInformation(shell, "修改失败", "用户 " + user.getName() +" 密码修改失败。");
			}
		});
		buttonSave.setImage(SWTResourceManager.getImage(ModifyPassword.class, "/images/ok.JPG"));
		buttonSave.setText("保存");
		
		buttonClose = new Button(shell, SWT.NONE);
		buttonClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		FormData fd_buttonClose = new FormData();
		fd_buttonClose.bottom = new FormAttachment(85);
		fd_buttonClose.right = new FormAttachment(75);
		buttonClose.setLayoutData(fd_buttonClose);
		buttonClose.setImage(SWTResourceManager.getImage(ModifyPassword.class, "/images/cancel.JPG"));
		buttonClose.setText("关闭");
		
		Label rb = new Label(shell, SWT.NONE);
		FormData fd_rb = new FormData();
		fd_rb.top = new FormAttachment(0, 238);
		fd_rb.left = new FormAttachment(0, 393);
		rb.setLayoutData(fd_rb);
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
		textPass.addModifyListener(textUserListener);
		textPass2.addModifyListener(textUserListener);
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
