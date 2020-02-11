package book.stdscore.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.ResourceManager;

import book.stdscore.data.Department;

import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class DepartmentWizardPage extends WizardPage {
	
	private LinkedList<Department> deptList;
	private Department currDept;

	public LinkedList<Department> getDeptList() {
		return deptList;
	}

	public Department getCurrDept() {
		return currDept;
	}

	/**
	 * Create the wizard.
	 */
	public DepartmentWizardPage() {
		super("wizardPage");
		setImageDescriptor(ResourceManager.getImageDescriptor(DepartmentWizardPage.class, "/images/dept.JPG"));
		setPageComplete(false);
		setTitle("课程设置");
		setDescription("在列表中选择课程所属的专业名称。");
		deptList = getDeptListFromFile();
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		List listDepartment = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_listDepartment = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listDepartment.heightHint = 160;
		gd_listDepartment.widthHint = 300;
		listDepartment.setLayoutData(gd_listDepartment);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		listDepartment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(listDepartment.getSelection().length==1) {
					currDept = new Department(listDepartment.getSelection()[0]);
					setPageComplete(true);
				}
			}
		});

		for(Department dept : deptList)
			listDepartment.add(dept.getName());
	}
	
	LinkedList<Department> getDeptListFromFile() {
		LinkedList<Department> departList = new LinkedList<Department>();;
		File deptFile = new File("deptList.obj");
		if(deptFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(deptFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				departList = (LinkedList<Department>) ois.readObject();
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
		} 
		return departList;
	}
}
