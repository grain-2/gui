package book.stdscore.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import book.stdscore.data.Department;

public class DepartmentAdd extends Dialog {

	protected Object result;
	protected Shell shell;
	private Text textDetpName;
	private Label labelMessage;
//	private LinkedList<Department> deptList;
//	private boolean newDept;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DepartmentAdd(Shell parent, int style) {
		super(parent, style);
		setText("添加专业");
/*
		File deptFile = new File("deptList.obj");
		if(deptFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(deptFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				deptList = (LinkedList<Department>) ois.readObject();
				if(ois!=null)
					ois.close();
				if(fis!=null)
					fis.close();
			} catch (ClassNotFoundException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			deptList = new LinkedList<Department>();
		}
*/
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.PRIMARY_MODAL);
		shell.addShellListener(new ShellAdapter() {
			@Override
			public void shellClosed(ShellEvent e) {
				saveDeptList();
			}
		});
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(null);
		
		Label labelDeptName = new Label(shell, SWT.NONE);
		labelDeptName.setBounds(53, 69, 72, 24);
		labelDeptName.setText("专业名：");
		
		textDetpName = new Text(shell, SWT.BORDER);
		textDetpName.setBounds(131, 63, 220, 30);
		
		Button buttonDeptSave = new Button(shell, SWT.NONE);
		buttonDeptSave.setBounds(113, 163, 48, 34);
		buttonDeptSave.setText("保存");
		
		Button buttonDeptNext = new Button(shell, SWT.NONE);
		buttonDeptNext.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textDetpName.setText("");
			}
		});
		buttonDeptNext.setBounds(188, 163, 66, 34);
		buttonDeptNext.setText("下一个");
		
		Button buttonDeptClose = new Button(shell, SWT.NONE);
		buttonDeptClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveDeptList();
				shell.close();
			}
		});
		buttonDeptClose.setBounds(283, 163, 48, 34);
		buttonDeptClose.setText("关闭");
		
		labelMessage = new Label(shell, SWT.NONE);
		labelMessage.setBounds(65, 117, 286, 24);
		buttonDeptSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveDeptList();
	/*
				String deptName = textDetpName.getText().trim();
				if(deptName==null || "".equals(deptName)) {
					labelMessage.setText("必须输入专业名才能保存！");
					buttonDeptSave.setFocus();
					return;
				}
				Department dept = new Department(deptName);

				boolean hasDept = false;
				for(Department adept : deptList) {
					if(adept.getName().equals(dept)) {
						hasDept = true;
						break;
					}
				}
				if(!hasDept) {
					deptList.add(dept);
					labelMessage.setText("专业 "+ deptName +" 添加成功。");
					newDept = true;
				} else {
					labelMessage.setText("专业 "+ deptName +" 已经存在。");
				}
*/
			}
		});

	}
	
	private void saveDeptList() {
		String deptName = textDetpName.getText().trim();
		if(deptName==null || "".equals(deptName)) {
			labelMessage.setText("必须输入专业名才能保存！");
			return;
		}
		if(Department.getFromDB(deptName)!=null) {
			labelMessage.setText("专业 "+ deptName +" 已经存在。");
			return;
		}
		Department dept = new Department(deptName);
		int deptid = dept.insertToDB();

/*
		if(newDept) {
			File deptFile = new File("deptList.obj");
			try {
				FileOutputStream fos = new FileOutputStream(deptFile);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(deptList);
				newDept = false;
				if(oos!=null)
					oos.close();
				if(fos!=null)
					fos.close();
			} catch (FileNotFoundException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
		}
*/
	}
	
}
