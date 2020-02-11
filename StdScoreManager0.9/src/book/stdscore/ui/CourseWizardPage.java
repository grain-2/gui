package book.stdscore.ui;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;

public class CourseWizardPage extends WizardPage {
	private Text textCourseName;
	private List listHasCourse;

	public List getListHasCourse() {
		return listHasCourse;
	}

	/**
	 * Create the wizard.
	 */
	public CourseWizardPage() {
		super("wizardPage");
		setTitle("添加课程");
		setDescription("为指定的年级和班级添加课程。");
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(6, false);
		gl_container.verticalSpacing = 10;
		gl_container.horizontalSpacing = 15;
		container.setLayout(gl_container);

		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		label.setText("新添加课程");
		new Label(container, SWT.NONE);

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		label_1.setText("课程列表");
		new Label(container, SWT.NONE);

		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		listHasCourse = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gridData = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 4);
		gridData.heightHint = 130;
		gridData.widthHint = 140;
		listHasCourse.setLayoutData(gridData);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("课程名");

		textCourseName = new Text(container, SWT.BORDER);
		GridData gd_textCourseName = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textCourseName.widthHint = 130;
		textCourseName.setLayoutData(gd_textCourseName);

		Button buttonAdd = new Button(container, SWT.CENTER);
		buttonAdd.setToolTipText("添加新课程");
		buttonAdd.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonAdd.setImage(SWTResourceManager.getImage(CourseWizardPage.class, "/images/addCourse.jpg"));
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label label_3 = new Label(container, SWT.NONE);
		label_3.setText("课程类型");

		Combo comboCourseType = new Combo(container, SWT.READ_ONLY);
		GridData gd_comboCourseType = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_comboCourseType.widthHint = 100;
		comboCourseType.setLayoutData(gd_comboCourseType);
		comboCourseType.setItems(new String[] { "公共基础课", "专业基础课", "专业选修课" });
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cname = textCourseName.getText().trim();
				String ctype = comboCourseType.getText();
				if (cname != null && !"".equals(cname) && !"".equals(ctype)) {
					String[] items = listHasCourse.getItems();
					for (String c : items) {
						if (c.split("`")[1].equals(cname) && c.split("`")[0].equals(ctype)) {
							setMessage("类型为 " + ctype + " 课程名为 " + cname + " 的课程已存在。");
							textCourseName.selectAll();
							textCourseName.setFocus();
							return;
						}
					}
					listHasCourse.add(ctype + "`" + cname);
					textCourseName.setText("");
					setPageComplete(true);
				}
			}
		});
		
		Button buttonDel = new Button(container, SWT.CENTER);
		buttonDel.setToolTipText("删除选取的课程");
		buttonDel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selCourse = listHasCourse.getSelection();
				if (selCourse.length > 0) {
					for (int i = 0; i < selCourse.length; i++) {
						listHasCourse.remove(selCourse[i]);
						setPageComplete(true);
					}
				}
			}
		});
		buttonDel.setImage(SWTResourceManager.getImage(CourseWizardPage.class, "/images/delCourse.jpg"));
		
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);

		Label lblNewLabel_2 = new Label(container, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
	}
}
