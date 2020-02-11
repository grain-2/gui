package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.SWTResourceManager;
import book.stdscore.data.*;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class AdminScoreMana {

	protected Shell shell;
	private User user;

	public AdminScoreMana(User user) {
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
			AdminScoreMana window = new AdminScoreMana(null);
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
		shell.setSize(532, 300);
		shell.setText("系统管理");
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setBounds(10, 10, 490, 112);
		
		ToolItem toolItemDeptSet = new ToolItem(toolBar, SWT.NONE);
		toolItemDeptSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new DepartmentAdd(shell,SWT.PRIMARY_MODAL).open();
			}
		});
		toolItemDeptSet.setEnabled(true);
		toolItemDeptSet.setToolTipText("专业设置");
		toolItemDeptSet.setImage(SWTResourceManager.getImage(AdminScoreMana.class, "/images/dept.JPG"));
		
		ToolItem toolItemCourseSet = new ToolItem(toolBar, SWT.NONE);
		toolItemCourseSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CourseSetWizard csw = new CourseSetWizard();
				WizardDialog wd = new WizardDialog(shell,csw);
				wd.addPageChangedListener(new IPageChangedListener(){

					@Override
					public void pageChanged(PageChangedEvent arg0) {
						// TODO 自动生成的方法存根
						if(wd.getCurrentPage() instanceof CourseWizardPage) {
							CourseWizardPage cwp = (CourseWizardPage)wd.getCurrentPage();
							List lhc = cwp.getListHasCourse();
							DepartmentWizardPage dwp = (DepartmentWizardPage) cwp.getPreviousPage().getPreviousPage();
							LinkedList<Course> coursesList = getDeptCourses(dwp.getCurrDept());
							dwp.getCurrDept().setCoursesList(coursesList);
							for(Course c : coursesList) {
								lhc.add(c.getType()+"`"+c.getName());
							}
						}
					}					
				});
				wd.open();
			}
		});
		toolItemCourseSet.setToolTipText("课程设置");
		toolItemCourseSet.setImage(SWTResourceManager.getImage(AdminScoreMana.class, "/images/tree.jpg"));
		
		ToolItem toolItemUsersReg = new ToolItem(toolBar, SWT.NONE);
		toolItemUsersReg.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new RegisterTabFolder().getShell();
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
	
	LinkedList<Course> getDeptCourses(Department deptCurrt) {
		// TODO 自动生成的方法存根
		LinkedList<Course> coursesList=new LinkedList<Course>();
		File deptFile = new File("deptList.obj");
		try {
			FileInputStream fis = new FileInputStream(deptFile);
			ObjectInputStream ois = new ObjectInputStream(fis);
			LinkedList<Department> deptList = (LinkedList<Department>) ois.readObject();
			if (ois != null)
				ois.close();
			if (fis != null)
				fis.close();
			
			for (int i = 0; i < deptList.size(); i++) {
				if( deptList.get(i).getName().equals(deptCurrt.getName())) {
					coursesList = deptList.get(i).getCoursesList();
					break;
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return coursesList;
	}
}
