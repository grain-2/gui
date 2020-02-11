package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Spinner;
import book.stdscore.data.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.swt.widgets.Button;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class GradeClassAdd extends Dialog  {
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return ((Department)element).getName();
		}
	}

	protected Object result;
	protected Shell shell;
	private static ArrayList<Department> deptList;
	
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return deptList.toArray(new Department[deptList.size()]);
			//return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	public GradeClassAdd(Shell parent) {
		super(parent);
		// TODO 自动生成的构造函数存根
		deptList = new ArrayList<Department>();
		setDeptList();
	}



	/**
	 * Open the window.
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
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(450, 300);
		shell.setText("添加班级");
		GridLayout gl_shell = new GridLayout(4, false);
		gl_shell.horizontalSpacing = 10;
		gl_shell.verticalSpacing = 10;
		shell.setLayout(gl_shell);
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelDept = new Label(shell, SWT.NONE);
		labelDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		labelDept.setText("专业：");
		
		ComboViewer comboViewer = new ComboViewer(shell, SWT.NONE);
		Combo comboDept = comboViewer.getCombo();
		comboDept.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboViewer.setLabelProvider(new ViewerLabelProvider());
		comboViewer.setContentProvider(new ContentProvider());
		comboViewer.setInput(deptList.toArray(new Department[deptList.size()]));
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelGrade = new Label(shell, SWT.NONE);
		labelGrade.setText("年级：");
		
		Spinner spinnerGrade = new Spinner(shell, SWT.BORDER);
		spinnerGrade.setTextLimit(4);
		spinnerGrade.setMaximum(3000);
		spinnerGrade.setMinimum(2000);
		spinnerGrade.setSelection(2016);
		GridData gd_spinnerGrade = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinnerGrade.widthHint = 80;
		spinnerGrade.setLayoutData(gd_spinnerGrade);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("班级：");
		
		Spinner spinnerClass = new Spinner(shell, SWT.BORDER);
		spinnerClass.setTextLimit(2);
		spinnerClass.setMaximum(10);
		spinnerClass.setSelection(1);
		GridData gd_spinnerClass = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_spinnerClass.widthHint = 80;
		spinnerClass.setLayoutData(gd_spinnerClass);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Button buttonSave = new Button(shell, SWT.NONE);
		buttonSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection ss = (StructuredSelection)comboViewer.getSelection();
				Department dept = (Department) ss.getFirstElement();
				int deptId = dept.getId();
				int grade = Integer.parseInt(spinnerGrade.getText()) ;
				int aclass = Integer.parseInt(spinnerClass.getText());
				String sql = "select * from department_grade_class where departmentID="+ deptId + 
						" and grade=" + grade + " and class=" + aclass;
				ResultSet rs = InitDB.getInitDB().getRs(sql);
				try {
					if(rs.next()) {
						MessageDialog.openError(getParent(), "该班级设置已存在", 
								dept.getName() + "专业" + grade + "年级" + aclass + "班已存在。");
						return ;
					}
				} catch (SQLException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				sql = "insert into department_grade_class (departmentID,grade,class) values(" + 
						deptId + "," + grade + "," + aclass + ")";
				Statement stmt = InitDB.getInitDB().getStmt();
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		buttonSave.setText("保存");
		
		Button buttonClose = new Button(shell, SWT.NONE);
		buttonClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		buttonClose.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonClose.setText("关闭");
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));

	}
	
	void setDeptList() {
		String sql = "select * from department";
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				deptList.add(new Department(rs.getInt(1),rs.getString(2)));
			}
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
