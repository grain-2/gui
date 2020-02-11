package book.stdscore.ui;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.action.Action;
import book.stdscore.data.*;
import book.stdscore.ui.TchScoreQuery.Filter2Range;
import book.stdscore.ui.TchScoreQuery.FilterEquals;
import book.stdscore.ui.TchScoreQuery.FilterHigh;
import book.stdscore.ui.TchScoreQuery.FilterLower;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.TableColumn;

public class StudentMain extends ApplicationWindow {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			String[] score = (String[]) element;
			if(columnIndex==0) {
				return score[0];
			} else if(columnIndex==1) {
				return score[1];
			} if(columnIndex==2) {
				return score[2];
			} if(columnIndex==3) {
				return score[3];
			} 
			return element.toString();
		}
	}
	private User user;
	private Shell shell;
	private Action actionBrowse;
	private Action actionQueryBYCourse;
	private Action actionQueryBYScore;
	private Action actionHelp;
	private Action actionExit;
	private Table table;
	private TableViewer tableViewer;
	private Composite composite;

	/**
	 * Create the application window.
	 */
	public StudentMain(User user) {
		super(null);
		setShellStyle(SWT.SHELL_TRIM);
		this.user = user;
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		setStatus("欢迎 " + Student.getFromDB(Long.parseLong(user.getName())).getName() + " 同学使用简易学生成绩管理系统!");
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(1, false));
		{
			composite = new Composite(container, SWT.NONE);
			composite.setLayout(new GridLayout(14, false));
			composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	
			Label lblNewLabel_1 = new Label(composite, SWT.NONE);
			lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
			
			Label lblNewLabel = new Label(composite, SWT.NONE);
			lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
			lblNewLabel.setText("成绩：");
			
			Combo comboCondition1 = new Combo(composite, SWT.NONE);
			comboCondition1.setItems(new String[] {"", ">", "<", "="});
			GridData gd_comboCondition1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
			gd_comboCondition1.widthHint = 20;
			comboCondition1.setLayoutData(gd_comboCondition1);
			
			Text textCondition1 = new Text(composite, SWT.BORDER);
			GridData gd_textCondition1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
			gd_textCondition1.widthHint = 40;
			textCondition1.setLayoutData(gd_textCondition1);
			
			Label lblNewLabel_3 = new Label(composite, SWT.NONE);
			GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblNewLabel_3.widthHint = 10;
			lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
			
			Button radioButtonAnd = new Button(composite, SWT.RADIO);
			radioButtonAnd.setText("与");
			
			Label lblNewLabel_5 = new Label(composite, SWT.NONE);
			GridData gd_lblNewLabel_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblNewLabel_5.widthHint = 10;
			lblNewLabel_5.setLayoutData(gd_lblNewLabel_5);
			
			Button radioButtonOr = new Button(composite, SWT.RADIO);
			radioButtonOr.setSize(46, 24);
			radioButtonOr.setText("或");
			
			Label lblNewLabel_4 = new Label(composite, SWT.NONE);
			GridData gd_lblNewLabel_4 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
			gd_lblNewLabel_4.widthHint = 10;
			lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);
			
			Combo comboCondition2 = new Combo(composite, SWT.NONE);
			GridData gd_comboCondition2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_comboCondition2.widthHint = 20;
			comboCondition2.setLayoutData(gd_comboCondition2);
			comboCondition2.setItems(new String[] {"", ">", "<", "="});
			comboCondition2.setSize(81, 32);
			
			Text textCondition2 = new Text(composite, SWT.BORDER);
			GridData gd_textCondition2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_textCondition2.widthHint = 40;
			textCondition2.setLayoutData(gd_textCondition2);
			textCondition2.setBounds(0, 0, 73, 30);
			
			Label lblNewLabel_6 = new Label(composite, SWT.NONE);
			GridData gd_lblNewLabel_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
			gd_lblNewLabel_6.widthHint = 20;
			lblNewLabel_6.setLayoutData(gd_lblNewLabel_6);
			
			Button buttonOK = new Button(composite, SWT.NONE);
			buttonOK.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					FilterLower flr = new FilterLower() ;
					FilterHigh fhi = new FilterHigh() ;
					FilterEquals feq = new  FilterEquals() ;
					Filter2Range range2 = new Filter2Range();
					
					float condi1 = -1 ;
					float condi2 = -1 ;
					String opr1 = null ;
					String opr2 = null ;
					
					try {
						if(textCondition1.getText()!=null && !"".equals(textCondition1.getText())) {
							condi1 = Float.parseFloat(textCondition1.getText());
						}
						if(textCondition2.getText()!=null && !"".equals(textCondition2.getText())) {
							condi2 = Float.parseFloat(textCondition2.getText());
						}
					} catch(NumberFormatException e1) {
						MessageDialog.openError(shell, "必须输入数值", "必须在该比较符旁边的文本框中输入一个数值。");
					}
					if(comboCondition1.getText()!=null && !"".equals(comboCondition1.getText())) {
						opr1 = comboCondition1.getText().trim();
					}
					if(comboCondition2.getText()!=null && !"".equals(comboCondition2.getText())) {
						opr2 = comboCondition2.getText().trim();
					}
					if(condi1!=-1 || condi2!=-1) {
						if(condi1>condi2) {
							float tmp =condi1 ;
							condi1 = condi2 ;
							condi2 = tmp ;
							String str = opr1;
							opr1 = opr2 ;
							opr2 = str ;
						}
					}
					if(("".equals(opr2) || opr2==null) && ("".equals(opr1) || opr1==null)) {
						clearFilters();
					} else if((!"".equals(opr2) && opr2!=null) && ("".equals(opr1) || opr1==null )) {
						if(opr2.equals("=")) {
							feq.setLimit(condi2) ;
							clearFilters();
							tableViewer.addFilter(feq);
							tableViewer.refresh();
						} else if(opr2.equals(">")) {
							flr.setLimit(condi2) ;
							clearFilters();
							tableViewer.addFilter(flr);
							tableViewer.refresh();
						}else if(opr2.equals("<")) {
							System.out.println("and < :"+condi2+","+fhi);
							fhi.setLimit(condi2) ;
							clearFilters();
							tableViewer.addFilter(fhi);
							tableViewer.refresh();
						}
					} else if(radioButtonAnd.getSelection()) {
						if(">".equals(opr1)&&">".equals(opr2)) {
							clearFilters();
							flr.setLimit(condi2);
							tableViewer.addFilter(flr);
						} else if("<".equals(opr1)&&"<".equals(opr2)) {
							clearFilters();
							fhi.setLimit(condi1);
							tableViewer.addFilter(fhi);
						} if(">".equals(opr1)&&"<".equals(opr2)) {
							clearFilters();
							flr.setLimit(condi1);
							tableViewer.addFilter(flr);
							fhi.setLimit(condi2);
							tableViewer.addFilter(fhi);
						} if("<".equals(opr1)&&">".equals(opr2)) {
							clearFilters();
							flr.setLimit(1000);
							tableViewer.addFilter(flr);
							fhi.setLimit(-1000);
							tableViewer.addFilter(fhi);
						}
						tableViewer.refresh();
					} else if(radioButtonOr.getSelection()) {
						if(">".equals(opr1)&&">".equals(opr2)) {
							clearFilters();
							flr.setLimit(condi1);
							tableViewer.addFilter(flr);
						} else if("<".equals(opr1)&&"<".equals(opr2)) {
							clearFilters();
							fhi.setLimit(condi2);
							tableViewer.addFilter(fhi);
						} if(">".equals(opr1)&&"<".equals(opr2)) {
							clearFilters();
						} if("<".equals(opr1)&&">".equals(opr2)) {
							clearFilters();
							range2.setLimitLow(condi1);
							range2.setLimitHigh(condi2);
							tableViewer.addFilter(range2);
						}
						tableViewer.refresh();
					}
				}
			});
			buttonOK.setText("查找");
			
			Label lblNewLabel_2 = new Label(composite, SWT.NONE);
			lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		}
		{
			tableViewer = new TableViewer(container, SWT.BORDER | SWT.FULL_SELECTION);
			table = tableViewer.getTable();
			table.setLinesVisible(true);
			table.setHeaderVisible(true);
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			TableColumn tableColumn = new TableColumn(table, SWT.NONE);
			tableColumn.setWidth(100);
			tableColumn.setText("课程名");
			
			TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
			tableColumn_1.setWidth(100);
			tableColumn_1.setText("课程类型");
			
			TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
			tableColumn_2.setWidth(100);
			tableColumn_2.setText("成绩");
			
			TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
			tableColumn_3.setWidth(100);
			tableColumn_3.setText("登分时间");
			tableViewer.setLabelProvider(new TableLabelProvider());
			tableViewer.setContentProvider(new ArrayContentProvider());
			tableViewer.setInput(getScoreList());
		}

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		{
			actionBrowse = new Action("成绩浏览") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					composite.setVisible(false);
					tableViewer.setInput(getScoreList());
					clearFilters();
					tableViewer.refresh();
					setStatus("显示 "+Student.getFromDB(Long.parseLong(user.getName())).getName()+" 同学的全部课程成绩");
				}
			};
		}
		{
			actionQueryBYCourse = new Action("按课程查询") {				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					composite.setVisible(false);
					tableViewer.setInput(getScoreList());
					clearFilters();
					InputDialog idl = new InputDialog(shell, "输入课程名", "请输入要查找课程的名称！", "", null);
					idl.open();
					String cname = idl.getValue();
					tableViewer.addFilter(new FilterCourseName(cname));
					tableViewer.refresh();
					setStatus("显示 "+Student.getFromDB(Long.parseLong(user.getName())).getName()+" 同学的 " + cname + " 成绩");
				}
			};
		}
		{
			actionQueryBYScore = new Action("按分数查询") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					composite.setVisible(true);
					tableViewer.setInput(getScoreList());
					clearFilters();
					tableViewer.refresh();
					setStatus("显示 "+Student.getFromDB(Long.parseLong(user.getName())).getName()+" 同学的指定分数段成绩");					
				}
			};
		}
		{
			actionHelp = new Action("帮助") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					composite.setVisible(true);
					new BrowserHelp().open();;
				}
			};
		}
		{
			actionExit = new Action("退出") {

				@Override
				public void run() {
					// TODO 自动生成的方法存根
					super.run();
					shell.dispose();
				}
			};
		}
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		toolBarManager.add(actionBrowse);
		toolBarManager.add(actionQueryBYCourse);
		toolBarManager.add(actionQueryBYScore);
		toolBarManager.add(actionHelp);
		toolBarManager.add(actionExit);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			StudentMain window = new StudentMain(null);
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setMinimumSize(new Point(600, 400));
		super.configureShell(newShell);
		newShell.setText("学生成绩管理");
		this.shell = newShell;
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	class FilterLower extends ViewerFilter {
		float limit;
		@Override
		public boolean select(Viewer arg0, Object arg1,Object arg2){
			String[] ss = (String[])arg2 ;
			if(Float.parseFloat(ss[2])>limit)
				return true ;
			else
				return false;
		}
		public float getLimit() {
			return limit;
		}
		public void setLimit(float limit) {
			this.limit = limit;
		}

	}

	class FilterHigh extends ViewerFilter {
		float limit;
		@Override
		public boolean select(Viewer arg0,Object arg1, Object arg2){
			String[] ss = (String[])arg2 ;
			if(Float.parseFloat(ss[2])<limit)
				return true ;
			else
				return false;
		}
		public float getLimit() {
			return limit;
		}
		public void setLimit(float limit) {
			this.limit = limit;
		}
	}

	class FilterEquals extends ViewerFilter {
		float limit;
		
		@Override
		public boolean select(Viewer arg0,Object arg1, Object arg2){
			if(arg2 instanceof String[]) {
				String[] ss = (String[])arg2 ;
				if(Math.abs(Float.parseFloat(ss[2])-limit)<0.01)
					return true ;
				else
					return false;
			} else
				return false ;
		}
		public float getLimit() {
			return limit;
		}
		public void setLimit(float limit) {
			this.limit = limit;
		}
	}

	class Filter2Range extends ViewerFilter {
		float limitLow;
		float limitHigh;
		@Override
		public boolean select(Viewer arg0, Object arg1, Object arg2){
			String[] ss = (String[])arg2 ;
			if(Float.parseFloat(ss[2])<limitLow)
				return true ;
			else if(Float.parseFloat(ss[2])>limitHigh)
				return true;
			else
				return false;
		}
		public float getLimitLow() {
			return limitLow;
		}
		public void setLimitLow(float limitLow) {
			this.limitLow = limitLow;
		}
		public float getLimitHigh() {
			return limitHigh;
		}

		public void setLimitHigh(float limitHigh) {
			this.limitHigh = limitHigh;
		}
	}
	
	class FilterCourseName extends ViewerFilter {
		String findName = null ;
		
		public FilterCourseName(String findName) {
			super();
			this.findName = findName;
		}

		@Override
		public boolean select(Viewer arg0, Object arg1, Object arg2) {
			// TODO 自动生成的方法存根
			String courseName = null ;
			if(arg2!=null)
				courseName =((String[])arg2)[0] ;
			else
				findName = "";
			if(courseName!=null && !"".equals(courseName) && !"".equals(findName)) {
				return courseName.equals(findName) ;
			}
			
			return false;
		}
	
	}
	
	void clearFilters() {
		ViewerFilter[] fls = tableViewer.getFilters() ;
		for(int i=0 ;i<fls.length; i++) {
			tableViewer.removeFilter(fls[i]) ;
		}
	}

	public ArrayList<String[]> getScoreList() {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		String sql = "select C.name,C.type,SC.score,SC.updatetime from student_course AS SC,course AS C where SC.studentID="
				 + Long.parseLong(user.getName()) + " and SC.courseID=C.id";
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				scoreList.add(new String[]{rs.getString(1),rs.getString(2),rs.getFloat(3)+"",rs.getDate(4)+""});
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return scoreList;
	}

}
