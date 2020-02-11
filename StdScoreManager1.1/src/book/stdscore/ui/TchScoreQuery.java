package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import book.stdscore.data.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

public class TchScoreQuery {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			String[] score = (String[])element;
			if(columnIndex==0) {
				return score[0];
			} else if(columnIndex==1) {
				return score[1];
			} else if(columnIndex==2) {
				return score[2];
			}
			return element.toString();
		}
	}
	private static class ViewerLabelProvider_2 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Teacher)element).getName();
		}
	}
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Department)element). getName();
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	private User user;
	protected Shell shell;
	private Table tableSelected;
	private Clipboard clipboard;
	private TreeViewer treeViewer;
	private Table table;
	private Text textCondition1;
	private Text textCondition2;
	private TableViewer tableViewer;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TchScoreQuery window = new TchScoreQuery(null);
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
		clipboard = new Clipboard(display);
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	public TchScoreQuery(User user) {
		super();
		this.user = user;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(650, 400));
		shell.setSize(450, 300);
		shell.setText("班级排课");
		GridLayout gl_shell = new GridLayout(1, false);
		gl_shell.verticalSpacing = 10;
		shell.setLayout(gl_shell);
		
		Composite composite_1 = new Composite(shell, SWT.NONE);
		composite_1.setLayout(new GridLayout(14, false));
		composite_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("成绩：");
		
		Combo comboCondition1 = new Combo(composite_1, SWT.NONE);
		comboCondition1.setItems(new String[] {"", ">", "<", "="});
		GridData gd_comboCondition1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_comboCondition1.widthHint = 30;
		comboCondition1.setLayoutData(gd_comboCondition1);
		
		textCondition1 = new Text(composite_1, SWT.BORDER);
		GridData gd_textCondition1 = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_textCondition1.widthHint = 50;
		textCondition1.setLayoutData(gd_textCondition1);
		
		Label lblNewLabel_3 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_3 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_3.widthHint = 20;
		lblNewLabel_3.setLayoutData(gd_lblNewLabel_3);
		
		Button radioButtonAnd = new Button(composite_1, SWT.RADIO);
		radioButtonAnd.setText("与");
		
		Label lblNewLabel_5 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_5 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_5.widthHint = 20;
		lblNewLabel_5.setLayoutData(gd_lblNewLabel_5);
		
		Button radioButtonOr = new Button(composite_1, SWT.RADIO);
		radioButtonOr.setSize(46, 24);
		radioButtonOr.setText("或");
		
		Label lblNewLabel_4 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_4 = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_4.widthHint = 20;
		lblNewLabel_4.setLayoutData(gd_lblNewLabel_4);
		
		Combo comboCondition2 = new Combo(composite_1, SWT.NONE);
		GridData gd_comboCondition2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_comboCondition2.widthHint = 30;
		comboCondition2.setLayoutData(gd_comboCondition2);
		comboCondition2.setItems(new String[] {"", ">", "<", "="});
		comboCondition2.setSize(81, 32);
		
		textCondition2 = new Text(composite_1, SWT.BORDER);
		GridData gd_textCondition2 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textCondition2.widthHint = 50;
		textCondition2.setLayoutData(gd_textCondition2);
		textCondition2.setBounds(0, 0, 73, 30);
		
		Label lblNewLabel_6 = new Label(composite_1, SWT.NONE);
		GridData gd_lblNewLabel_6 = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel_6.widthHint = 40;
		lblNewLabel_6.setLayoutData(gd_lblNewLabel_6);
		
		Button buttonOK = new Button(composite_1, SWT.NONE);
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
						System.out.println("and > :"+condi2+","+flr);
						flr.setLimit(condi2) ;
						clearFilters();
						tableViewer.addFilter(flr);
						tableViewer.refresh();
					}else if(opr2.equals("<")) {
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
		
		Label lblNewLabel_2 = new Label(composite_1, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false, 1, 1));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		treeViewer = new TreeViewer(sashForm, SWT.BORDER);
		treeViewer.setUseHashlookup(true);
		Tree tree = treeViewer.getTree();
		treeViewer.setLabelProvider(new ViewerLabelProvider());

		treeViewer.setContentProvider(new TreeNodeContentProvider(){
			@Override
			public Object[] getElements(Object inputElement) {
				return ((TreeNode) inputElement).getChildren();
			}
		});
		
		treeViewer.setInput(new ScoreCourseTreeRoot(Teacher.getFromDB(Integer.parseInt(user.getName()))));		

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		tableSelected = tableViewer.getTable();
		tableSelected.setHeaderVisible(true);
		tableSelected.setLinesVisible(true);
		
		TableColumn tableColumnSelID = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelID.setWidth(120);
		tableColumnSelID.setText("学号");
		
		TableColumn tableColumnSelName = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelName.setWidth(80);
		tableColumnSelName.setText("姓名");

		TableViewerColumn tableViewerColumn = new TableViewerColumn(tableViewer, SWT.NONE);
/*
		tableViewerColumn.setEditingSupport(new EditingSupport(tableViewer) {
			protected boolean canEdit(Object element) {
				// TODO Auto-generated method stub
				return true;
			}
			protected CellEditor getCellEditor(Object element) {
				// TODO Auto-generated method stub
				return new TextCellEditor(tableSelected);
			}
			protected Object getValue(Object element) {
				// TODO Auto-generated method stub
				return ((String[])element)[2];
			}
			protected void setValue(Object element, Object value) {
				// TODO Auto-generated method stub
				((String[])element)[2]=(String)value;
				tableViewer.update(element, (String[])tableViewer.getColumnProperties());
				// 更新数据库表 student_course中的score字段及updatetime字段
				updateScore(Long.parseLong(((String[])element)[0]),Float.parseFloat((String)value));
			}
		});
*/
		TableColumn tableColumnScore = tableViewerColumn.getColumn();
		tableColumnScore.setWidth(100);
		tableColumnScore.setText("成绩");

		tableViewer.setContentProvider(new ArrayContentProvider());
		tableViewer.setLabelProvider(new TableLabelProvider());
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
				TreeNode selNode = (TreeNode) ss.getFirstElement();
				if(selNode instanceof CourseTreeNode) {
					GradeClass aclass = (GradeClass) ((ClassTreeNode)(selNode.getParent())).getValue();
					ArrayList<String[]> scoreList = getStdList((Course)selNode.getValue(),aclass);
					tableViewer.setInput(scoreList);
					clearFilters();
					tableViewer.refresh();
					/*
					tableSelected.removeAll();
					TableItem item;
					for(String[] strArr : scoreList) {
						item = new TableItem(tableSelected, SWT.NONE);
						item.setText(strArr);			
					}
					*/
				}
			}
		});
				
		sashForm.setWeights(new int[] {1, 2});

	}
	
	public ArrayList<String[]> getStdList(Course course, GradeClass aclass) {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		String sql = "select S.id,S.name,SC.score from student AS S,student_course AS SC where S.id=SC.studentID " +
				"and SC.courseID=" + course.getId() + " and S.departmentId=" + aclass.getDepartmentId() +
				" and S.grade=" + aclass.getGrade() +" and S.class=" + aclass.getcClass();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				scoreList.add(new String[]{rs.getInt(1) + "",rs.getString(2),rs.getFloat(3)+""});
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return scoreList;
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

	void updateScore(long stdID,float score) {
		// Long.parseLong(((String[])element)[0]),courseID,Float.parseFloat((String)value)
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		int courseID = ((Course)courseNode.getValue()).getId();
		Date date = new Date();
		Timestamp tt = new Timestamp(date.getTime());
		String sql = "update student_course set score=" + score +",updatetime='" + tt +
				"' where studentID=" + stdID +" and courseID=" +courseID ;
		try {
			InitDB.getInitDB().getStmt().executeUpdate(sql);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	void clearFilters() {
		ViewerFilter[] fls = tableViewer.getFilters() ;
		for(int i=0 ;i<fls.length; i++) {
			tableViewer.removeFilter(fls[i]) ;
		}
	}
}
