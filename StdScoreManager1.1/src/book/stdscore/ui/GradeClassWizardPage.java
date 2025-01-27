package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;

public class GradeClassWizardPage extends WizardPage {

	private int grade;
	private int aclass;
	private List listGrade;
	private Department currDept;
	
	public Department getCurrDept() {
		return currDept;
	}
	public void setCurrDept(Department currDept) {
		this.currDept = currDept;
	}
	public List getListGrade() {
		return listGrade;
	}
	public int getGrade() {
		return grade;
	}

	public int getAclass() {
		return aclass;
	}

	/**
	 * Create the wizard.
	 */
	public GradeClassWizardPage() {
		super("wizardPage");
		setPageComplete(false);
		setTitle("年级和班级");
		setDescription("选择年级，并输入班级。");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(6, false));
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("年级：");
		
		Label label_1 = new Label(container, SWT.SEPARATOR | SWT.VERTICAL);
		GridData gd_label_1 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 2);
		gd_label_1.horizontalIndent = 40;
		label_1.setLayoutData(gd_label_1);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		listGrade = new List(container, SWT.BORDER);
		GridData gd_listGrade = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listGrade.widthHint = 140;
		gd_listGrade.heightHint = 160;
		listGrade.setLayoutData(gd_listGrade);
/*
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); 
		for(int i=year-3;i<year+4;i++)
			listGrade.add(i+"");
*/
		Label label_2 = new Label(container, SWT.NONE);
		GridData gd_label_2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_label_2.horizontalIndent = 40;
		label_2.setLayoutData(gd_label_2);
		label_2.setText("班级");
		
		List listClass = new List(container, SWT.BORDER);
		listClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aclass = Integer.parseInt(listClass.getSelection()[0]);
				if(grade>0 && aclass>0 && aclass<10)
					setPageComplete(true);				
			}
		});
		GridData gd_listClass = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_listClass.heightHint = 100;
		listClass.setLayoutData(gd_listClass);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		listGrade.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(listGrade.getSelection().length==1) {
					grade = Integer.parseInt(listGrade.getSelection()[0]);
					String sql =  "select class from department_grade_class where departmentID="+currDept.getId() +
							" and grade=" + grade + " group by class";
					InitDB db = new InitDB();
					ResultSet rs = db.getRs(sql);
					listClass.removeAll();
					try {
						while(rs.next()) {
							listClass.add(rs.getInt("class")+"");
						}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					if(grade>0 && aclass>0)
						setPageComplete(true);
				}
			}
		});
		
	}
}
