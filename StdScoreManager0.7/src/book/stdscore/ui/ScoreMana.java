package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import book.stdscore.data.Student;
import book.stdscore.data.User;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ScoreMana {

	protected Shell shell;
	private User user;
	private Text textStdDataNum;
	private Text textStdDataName;
	private Text textCourseName;
	private Text textScore;

	public Shell getShell() {
		return shell;
	}

	public ScoreMana(User user) {
		super();
		this.user = user;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	/*
	public static void main(String[] args) {
		try {
			ScoreMana window = new ScoreMana();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	 */
	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
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
		shell.setSize(new Point(500, 360));
		shell.setMinimumSize(new Point(600, 460));
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(2, false));
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("欢迎 "+user.getName()+" 同学使用学生成绩管理系统");
		new Label(shell, SWT.NONE);
		
		Composite compositeStdData = new Composite(shell, SWT.NONE);
		compositeStdData.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		GridLayout gl_compositeStdData = new GridLayout(2, false);
		gl_compositeStdData.verticalSpacing = 15;
		compositeStdData.setLayout(gl_compositeStdData);
		
		Label labelStdData = new Label(compositeStdData, SWT.NONE);
		labelStdData.setAlignment(SWT.CENTER);
		labelStdData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		labelStdData.setText("成绩数据");
		
		Label labelStdDataNum = new Label(compositeStdData, SWT.NONE);
		labelStdDataNum.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelStdDataNum.setText("学号：");
		
		textStdDataNum = new Text(compositeStdData, SWT.BORDER);
		textStdDataNum.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label labelStdDataName = new Label(compositeStdData, SWT.NONE);
		labelStdDataName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelStdDataName.setText("姓名：");
		
		textStdDataName = new Text(compositeStdData, SWT.BORDER);
		textStdDataName.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label labelStdDataLine = new Label(compositeStdData, SWT.SEPARATOR | SWT.HORIZONTAL);
		labelStdDataLine.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 2, 1));
		labelStdDataLine.setText("New Label");
		
		Label labelCourseName = new Label(compositeStdData, SWT.NONE);
		labelCourseName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelCourseName.setText("课程名");
		
		Label labelScore = new Label(compositeStdData, SWT.NONE);
		labelScore.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		labelScore.setText("成绩");
		
		textCourseName = new Text(compositeStdData, SWT.BORDER);
		textCourseName.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		textScore = new Text(compositeStdData, SWT.BORDER);
		textScore.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		
		Label labelStdDataFill = new Label(compositeStdData, SWT.NONE);
		labelStdDataFill.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeStdData, SWT.NONE);
		
		Composite compositeStdOp = new Composite(shell, SWT.NONE);
		compositeStdOp.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		GridLayout gl_compositeStdOp = new GridLayout(1, false);
		gl_compositeStdOp.verticalSpacing = 15;
		compositeStdOp.setLayout(gl_compositeStdOp);
		
		Label labelStdOP = new Label(compositeStdOp, SWT.NONE);
		labelStdOP.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		labelStdOP.setAlignment(SWT.CENTER);
		labelStdOP.setText("操作菜单");
		
		Button buttonStdOPFirst = new Button(compositeStdOp, SWT.NONE);
		buttonStdOPFirst.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonStdOPFirst.setText("第一门课程");
		
		Button buttonStdOPNext = new Button(compositeStdOp, SWT.NONE);
		buttonStdOPNext.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonStdOPNext.setText("下一门课程");
		
		Button buttonStdOPPrev = new Button(compositeStdOp, SWT.NONE);
		buttonStdOPPrev.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonStdOPPrev.setText("上一门课程");
		
		Button buttonStdOPLast = new Button(compositeStdOp, SWT.NONE);
		buttonStdOPLast.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonStdOPLast.setText("最后一门课程");
		
		Combo comboStdOPSele = new Combo(compositeStdOp, SWT.NONE);
		comboStdOPSele.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		comboStdOPSele.setText("课程名？");
		
		Button buttonChart = new Button(compositeStdOp, SWT.NONE);
		buttonChart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// 通过user字段获取Student的id。此处准备了一个成绩文件1201001.csv，直接传递学号1201001进行测试。
				new ScoreChart(new Student(1201001));
			}
		});
		buttonChart.setImage(SWTResourceManager.getImage(ScoreMana.class, "/images/logo.jpg"));
		buttonChart.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonChart.setText("直方图");
		
		Label labelStdOPFill = new Label(compositeStdOp, SWT.NONE);
		labelStdOPFill.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

	}
}
