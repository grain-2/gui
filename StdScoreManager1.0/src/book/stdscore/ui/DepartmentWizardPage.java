package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.ResourceManager;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;

public class DepartmentWizardPage extends WizardPage {
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			Department dept = (Department) element;
			return dept.getName();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			return deptList.toArray();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	
	private static ArrayList<Department> deptList;
	private Department currDept;

	public ArrayList<Department> getDeptList() {
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
		
		ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		List listDepartment = listViewer.getList();
		
		//List listDepartment = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_listDepartment = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listDepartment.heightHint = 160;
		gd_listDepartment.widthHint = 300;
		listDepartment.setLayoutData(gd_listDepartment);
		listViewer.setLabelProvider(new ViewerLabelProvider());
		listViewer.setContentProvider(new ContentProvider());
		listViewer.setInput(deptList.toArray());
		
		listDepartment.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(listDepartment.getSelection().length==1) {
					currDept = Department.getFromDB(listDepartment.getSelection()[0]);
					setPageComplete(true);
				}
			}
		});
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
/*
		for(Department dept : deptList)
			listDepartment.add(dept.getName());
*/
	}
	
	ArrayList<Department> getDeptListFromFile() {
		ArrayList<Department> departList = new ArrayList<Department>();
		InitDB db = new InitDB();
		ResultSet rs = db.getRs("select * from department");
		try {
			while(rs.next()) {
				departList.add(new Department(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
/*
		File deptFile = new File("deptList.obj");
		if(deptFile.exists()) {
			try {
				FileInputStream fis = new FileInputStream(deptFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				departList = (ArrayList<Department>) ois.readObject();
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
*/ 
		return departList;
	}
}
