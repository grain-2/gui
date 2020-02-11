package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

public class RegisterTabFolder {

	protected Shell shell;
	private Text textStdID;
	private Text textStdName;
	private Text textStdGrade;
	private Text textStdClass;
	private Text textStdItr;
	private Text textTchID;
	private Text textTchName;
	private Text textTchAge;
	private Text textTchAddr;
	private Text textTchIntro;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterTabFolder window = new RegisterTabFolder();
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
		shell.setMinimumSize(new Point(650, 600));
		shell.setSize(450, 300);
		shell.setText("用户注册");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		
		TabItem tabItemStd = new TabItem(tabFolder, SWT.NONE);
		tabItemStd.setText("学生注册");
		
		Composite compositeStd = new Composite(tabFolder, SWT.NONE);
		tabItemStd.setControl(compositeStd);
		GridLayout gl_compositeStd = new GridLayout(7, false);
		gl_compositeStd.verticalSpacing = 15;
		gl_compositeStd.horizontalSpacing = 15;
		compositeStd.setLayout(gl_compositeStd);
		
		Label lblSA = new Label(compositeStd, SWT.NONE);
		lblSA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdID = new Label(compositeStd, SWT.NONE);
		lblStdID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdID.setText("学号");
		
		textStdID = new Text(compositeStd, SWT.BORDER);
		GridData gd_textStdID = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textStdID.widthHint = 99;
		textStdID.setLayoutData(gd_textStdID);
		
		Label lblSB = new Label(compositeStd, SWT.NONE);
		GridData gd_lblSB = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSB.widthHint = 50;
		lblSB.setLayoutData(gd_lblSB);
		
		Label lblStdPicL = new Label(compositeStd, SWT.NONE);
		lblStdPicL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdPicL.setText("照片");
		
		Label lblStdPic = new Label(compositeStd, SWT.BORDER | SWT.SHADOW_NONE);
		GridData gd_lblStdPic = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
		gd_lblStdPic.widthHint = 100;
		lblStdPic.setLayoutData(gd_lblStdPic);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdName = new Label(compositeStd, SWT.NONE);
		lblStdName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdName.setText("姓名");
		
		textStdName = new Text(compositeStd, SWT.BORDER);
		textStdName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdDept = new Label(compositeStd, SWT.NONE);
		lblStdDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdDept.setText("专业");
		
		Combo comboStdDept = new Combo(compositeStd, SWT.NONE);
		comboStdDept.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdGrade = new Label(compositeStd, SWT.NONE);
		lblStdGrade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdGrade.setText("年级");
		
		textStdGrade = new Text(compositeStd, SWT.BORDER);
		textStdGrade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdItr = new Label(compositeStd, SWT.NONE);
		lblStdItr.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdItr.setText("学习兴趣");
		
		textStdItr = new Text(compositeStd, SWT.BORDER);
		textStdItr.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdClass = new Label(compositeStd, SWT.NONE);
		lblStdClass.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdClass.setText("班级");
		
		textStdClass = new Text(compositeStd, SWT.BORDER);
		textStdClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblSC = new Label(compositeStd, SWT.NONE);
		lblSC.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Button buttonSelCourseS = new Button(compositeStd, SWT.NONE);
		buttonSelCourseS.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		buttonSelCourseS.setText("选择课程");
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblSD = new Label(compositeStd, SWT.NONE);
		lblSD.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		
		TabItem tabItemTch = new TabItem(tabFolder, SWT.NONE);
		tabItemTch.setText("教师注册");
		
		Composite compositeTch = new Composite(tabFolder, SWT.NONE);
		tabItemTch.setControl(compositeTch);
		GridLayout gl_compositeTch = new GridLayout(7, false);
		gl_compositeTch.verticalSpacing = 15;
		gl_compositeTch.horizontalSpacing = 15;
		compositeTch.setLayout(gl_compositeTch);
		
		Label lblTA = new Label(compositeTch, SWT.NONE);
		lblTA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchID = new Label(compositeTch, SWT.NONE);
		lblTchID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchID.setText("工号");
		
		textTchID = new Text(compositeTch, SWT.BORDER);
		textTchID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchPicL = new Label(compositeTch, SWT.NONE);
		lblTchPicL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchPicL.setText("照片");
		
		Label lblTchPic = new Label(compositeTch, SWT.BORDER);
		GridData gd_lblTchPic = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
		gd_lblTchPic.widthHint = 100;
		lblTchPic.setLayoutData(gd_lblTchPic);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchName = new Label(compositeTch, SWT.NONE);
		lblTchName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchName.setText("姓名");
		
		textTchName = new Text(compositeTch, SWT.BORDER);
		textTchName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchSex = new Label(compositeTch, SWT.NONE);
		lblTchSex.setText("性别");
		
		Button buttonTchMale = new Button(compositeTch, SWT.RADIO);
		buttonTchMale.setText("男");
		
		Button buttonTchFemale = new Button(compositeTch, SWT.RADIO);
		buttonTchFemale.setText("女");
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchAge = new Label(compositeTch, SWT.NONE);
		lblTchAge.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchAge.setText("年龄");
		
		textTchAge = new Text(compositeTch, SWT.BORDER);
		textTchAge.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchIntro = new Label(compositeTch, SWT.NONE);
		lblTchIntro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchIntro.setText("简介");
		
		textTchIntro = new Text(compositeTch, SWT.BORDER);
		textTchIntro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchDept = new Label(compositeTch, SWT.NONE);
		lblTchDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchDept.setText("部门");
		
		Combo comboTchDept = new Combo(compositeTch, SWT.NONE);
		comboTchDept.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchAddr = new Label(compositeTch, SWT.NONE);
		lblTchAddr.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblTchAddr.setText("住址");
		
		textTchAddr = new Text(compositeTch, SWT.BORDER);
		GridData gd_textTchAddr = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_textTchAddr.heightHint = 80;
		textTchAddr.setLayoutData(gd_textTchAddr);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTB = new Label(compositeTch, SWT.NONE);
		lblTB.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Button buttonSelcourseT = new Button(compositeTch, SWT.NONE);
		buttonSelcourseT.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 2, 1));
		buttonSelcourseT.setText("选择课程");
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTC = new Label(compositeTch, SWT.NONE);
		lblTC.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		
		TabItem tabItemCourse = new TabItem(tabFolder, SWT.NONE);
		tabItemCourse.setText("课程选择");
		
		Composite compositeCourse = new Composite(tabFolder, SWT.NONE);
		tabItemCourse.setControl(compositeCourse);
		GridLayout gl_compositeCourse = new GridLayout(4, false);
		gl_compositeCourse.horizontalSpacing = 25;
		compositeCourse.setLayout(gl_compositeCourse);
		
		Label lblCA = new Label(compositeCourse, SWT.NONE);
		lblCA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		
		Label lblCourseSel = new Label(compositeCourse, SWT.NONE);
		lblCourseSel.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 2, 1));
		lblCourseSel.setText("选择课程");
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		
		Label lblCourseDept = new Label(compositeCourse, SWT.NONE);
		lblCourseDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCourseDept.setText("专业");
		
		Combo comboCourseDept = new Combo(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		
		Composite compositeCourseList = new Composite(compositeCourse, SWT.NONE);
		GridData gd_compositeCourseList = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_compositeCourseList.heightHint = 240;
		compositeCourseList.setLayoutData(gd_compositeCourseList);
		GridLayout gl_compositeCourseList = new GridLayout(3, false);
		gl_compositeCourseList.horizontalSpacing = 25;
		compositeCourseList.setLayout(gl_compositeCourseList);
		
		Label lblCourseAllBase = new Label(compositeCourseList, SWT.NONE);
		lblCourseAllBase.setText("公共基础课");
		
		Label lblCourseDeptBase = new Label(compositeCourseList, SWT.NONE);
		lblCourseDeptBase.setText("专业基础课");
		
		Label lblCourseDeptSel = new Label(compositeCourseList, SWT.NONE);
		lblCourseDeptSel.setText("专业选修课");
		
		Group groupCourseAllBase = new Group(compositeCourseList, SWT.NONE);
		GridData gd_groupCourseAllBase = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_groupCourseAllBase.widthHint = 130;
		gd_groupCourseAllBase.heightHint = 150;
		groupCourseAllBase.setLayoutData(gd_groupCourseAllBase);
		
		Button button = new Button(groupCourseAllBase, SWT.CHECK);
		button.setBounds(10, 23, 100, 24);
		button.setText("大学英语");
		
		Button button_1 = new Button(groupCourseAllBase, SWT.CHECK);
		button_1.setBounds(10, 53, 100, 24);
		button_1.setText("大学语文");
		
		Button button_2 = new Button(groupCourseAllBase, SWT.CHECK);
		button_2.setBounds(10, 83, 106, 24);
		button_2.setText("哲学原理");
		
		Button btnCheckButton = new Button(groupCourseAllBase, SWT.CHECK);
		btnCheckButton.setBounds(10, 113, 100, 24);
		btnCheckButton.setText("法律基础");
		
		Button button_3 = new Button(groupCourseAllBase, SWT.CHECK);
		button_3.setBounds(10, 143, 136, 24);
		button_3.setText("大学信息技术");
		
		ScrolledComposite scrolledCompositeScoresDeptBases = new ScrolledComposite(compositeCourseList, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GridData gd_scrolledCompositeScoresDeptBases = new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 1);
		gd_scrolledCompositeScoresDeptBases.widthHint = 130;
		gd_scrolledCompositeScoresDeptBases.heightHint = 150;
		scrolledCompositeScoresDeptBases.setLayoutData(gd_scrolledCompositeScoresDeptBases);
		scrolledCompositeScoresDeptBases.setExpandHorizontal(true);
		scrolledCompositeScoresDeptBases.setExpandVertical(true);
		
		Composite compositeCourseDeptBasesList = new Composite(scrolledCompositeScoresDeptBases, SWT.NONE);
		
		Button button_5 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_5.setBounds(10, 10, 143, 24);
		button_5.setText("高等数学");
		
		Button button_6 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_6.setBounds(10, 40, 143, 24);
		button_6.setText("普通物理");
		
		Button button_7 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_7.setBounds(10, 70, 143, 24);
		button_7.setText("概率统计");
		
		Button button_8 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_8.setBounds(10, 100, 143, 24);
		button_8.setText("专业英语");
		
		Button button_9 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_9.setBounds(10, 130, 143, 24);
		button_9.setText("计算机概论");
		
		Button button_10 = new Button(compositeCourseDeptBasesList, SWT.CHECK);
		button_10.setBounds(10, 160, 143, 24);
		button_10.setText("数据库原理");
		scrolledCompositeScoresDeptBases.setContent(compositeCourseDeptBasesList);
		scrolledCompositeScoresDeptBases.setMinSize(new Point(120, 200));
		scrolledCompositeScoresDeptBases.setMinSize(new Point(120, 200));
		
		Group groupCourseDeptSel = new Group(compositeCourseList, SWT.NONE);
		GridData gd_groupCourseDeptSel = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_groupCourseDeptSel.heightHint = 150;
		gd_groupCourseDeptSel.widthHint = 130;
		groupCourseDeptSel.setLayoutData(gd_groupCourseDeptSel);
		
		Button button_4 = new Button(groupCourseDeptSel, SWT.CHECK);
		button_4.setBounds(10, 23, 100, 24);
		button_4.setText("网页设计");
		
		Button btnJavaGui = new Button(groupCourseDeptSel, SWT.CHECK);
		btnJavaGui.setBounds(10, 53, 136, 24);
		btnJavaGui.setText("Java GUI设计");
		
		Button btnJsp = new Button(groupCourseDeptSel, SWT.CHECK);
		btnJsp.setBounds(10, 83, 143, 24);
		btnJsp.setText("JSP程序设计");
		
		Button btnJavaee = new Button(groupCourseDeptSel, SWT.CHECK);
		btnJavaee.setBounds(10, 113, 143, 24);
		btnJavaee.setText("JavaEE开发");
		
		Button btnWeb = new Button(groupCourseDeptSel, SWT.CHECK);
		btnWeb.setBounds(10, 143, 143, 24);
		btnWeb.setText("Web项目开发");
		new Label(compositeCourse, SWT.NONE);
		
		Label lblCB = new Label(compositeCourse, SWT.NONE);
		lblCB.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1));
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		
		Composite compositeCourseCom = new Composite(compositeCourse, SWT.NONE);
		FormLayout fl_compositeCourseCom = new FormLayout();
		fl_compositeCourseCom.marginHeight = 15;
		compositeCourseCom.setLayout(fl_compositeCourseCom);
		compositeCourseCom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 2, 1));
		
		Button buttonNext = new Button(compositeCourseCom, SWT.NONE);
		FormData fd_buttonNext = new FormData();
		fd_buttonNext.right = new FormAttachment(15, 100);
		fd_buttonNext.left = new FormAttachment(15);
		buttonNext.setLayoutData(fd_buttonNext);
		buttonNext.setText("下一个");
		
		Button buttonSaveExit = new Button(compositeCourseCom, SWT.NONE);
		FormData fd_buttonSaveExit = new FormData();
		fd_buttonSaveExit.top = new FormAttachment(0);
		fd_buttonSaveExit.right = new FormAttachment(50, 50);
		fd_buttonSaveExit.bottom = new FormAttachment(100);
		fd_buttonSaveExit.left = new FormAttachment(50, -50);
		buttonSaveExit.setLayoutData(fd_buttonSaveExit);
		buttonSaveExit.setText("保存退出");
		
		Button buttonExit = new Button(compositeCourseCom, SWT.NONE);
		FormData fd_buttonExit = new FormData();
		fd_buttonExit.bottom = new FormAttachment(100);
		fd_buttonExit.right = new FormAttachment(85);
		fd_buttonExit.top = new FormAttachment(0);
		fd_buttonExit.left = new FormAttachment(85, -100);
		buttonExit.setLayoutData(fd_buttonExit);
		buttonExit.setText("不保存退出");
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		new Label(compositeCourse, SWT.NONE);
		
		Label lblCC = new Label(compositeCourse, SWT.NONE);
		lblCC.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));

	}
}
