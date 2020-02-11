package book.stdscore.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import book.stdscore.data.Course;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.dialogs.IPageChangedListener;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.PageChangedEvent;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;

public class CourseManager extends Dialog {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			if(columnIndex==0) {
				return ((Course)element).getType();
			} else if(columnIndex==1) {
				return ((Course)element).getName();
			}
			return element.toString();
		}
	}
	private static class ContentProvider_1 implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof ArrayList) {
				return ((ArrayList)inputElement).toArray();
			}
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Department)element).getName();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			//return new Object[0];
			return deptList.toArray(new Department[deptList.size()]);
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Object result;
	protected Shell shell;
	private static ArrayList<Department> deptList;
	private ListViewer listViewer;
	private TableViewer tableViewer;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CourseManager(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
		setDeptList();
	}

	/**
	 * Open the dialog.
	 * @return the result
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
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), SWT.DIALOG_TRIM | SWT.RESIZE);
		shell.setSize(450, 300);
		shell.setText("课程设置与管理");
		shell.setLayout(new GridLayout(2, false));
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		toolBar.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		
		ToolItem tltmAdd = new ToolItem(toolBar, SWT.NONE);
		tltmAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CourseSetWizard csw = new CourseSetWizard();
				WizardDialog wd = new WizardDialog(shell,csw);
				wd.addPageChangedListener(new IPageChangedListener(){

					@Override
					public void pageChanged(PageChangedEvent arg0) {
						// TODO 自动生成的方法存根
						if(wd.getCurrentPage() instanceof GradeClassWizardPage) {
							GradeClassWizardPage gcp = (GradeClassWizardPage) wd.getCurrentPage();
							DepartmentWizardPage dwp = (DepartmentWizardPage) gcp.getPreviousPage();
							Department currDept = dwp.getCurrDept();
							gcp.setCurrDept(currDept);
							String sql = "select grade from department_grade_class where departmentID="+currDept.getId() +
									" group by grade";
							InitDB db = new InitDB();
							ResultSet rs = db.getRs(sql);
							gcp.getListGrade().removeAll();
							try {
								while(rs.next()) {
									gcp.getListGrade().add(rs.getInt("grade")+"");
								}
								if(rs!=null) {
									rs.close();
									db.closeDB();
								}
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						}
						if(wd.getCurrentPage() instanceof CourseWizardPage) {
							CourseWizardPage cwp = (CourseWizardPage)wd.getCurrentPage();
							List lhc = cwp.getListHasCourse();
							DepartmentWizardPage dwp = (DepartmentWizardPage) cwp.getPreviousPage().getPreviousPage();
							dwp.getCurrDept().setCoursesListFromDB();
							ArrayList<Course> coursesList = dwp.getCurrDept().getCoursesList();
							dwp.getCurrDept().setCoursesList(coursesList);
							for(Course c : coursesList) {
								lhc.add(c.getType()+":"+c.getName());
							}
						}
					}					
				});
				wd.open();
				tableViewer.resetFilters();
			}
		});
		tltmAdd.setText("添加");
		
		ToolItem tltmBrowse = new ToolItem(toolBar, SWT.NONE);
		tltmBrowse.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tableViewer.resetFilters();
				listViewer.refresh();
			}
		});
		tltmBrowse.setToolTipText("在左边列表中双击专业名，右边列表显示课程名称");
		tltmBrowse.setText("浏览");
		
		ToolItem tltmQuery = new ToolItem(toolBar, SWT.NONE);
		tltmQuery.setToolTipText("双击左边列表中的专业名称，然后输入查询的课程名称或课程名称部分文字");
		tltmQuery.setText("查询");
		
		ToolItem tltmQuit = new ToolItem(toolBar, SWT.NONE);
		tltmQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		tltmQuit.setText("退出");
		
		listViewer = new ListViewer(shell, SWT.BORDER | SWT.V_SCROLL);
		List listDepartment = listViewer.getList();
		GridData gd_listDepartment = new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1);
		gd_listDepartment.widthHint = 120;
		listDepartment.setLayoutData(gd_listDepartment);
		listViewer.setLabelProvider(new ViewerLabelProvider());
		listViewer.setContentProvider(new ContentProvider());
		listViewer.setInput(deptList);
		
		tableViewer = new TableViewer(shell, SWT.BORDER | SWT.FULL_SELECTION);
		Table tableCourse = tableViewer.getTable();
		tableCourse.setLinesVisible(true);
		tableCourse.setHeaderVisible(true);
		tableCourse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tableColumnType = new TableColumn(tableCourse, SWT.NONE);
		tableColumnType.setWidth(100);
		tableColumnType.setText("课程类型");
		
		TableColumn tableColumnName = new TableColumn(tableCourse, SWT.NONE);
		tableColumnName.setWidth(130);
		tableColumnName.setText("课程名称");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ContentProvider_1());
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection) arg0.getSelection();
				Department dept = (Department) ss.getFirstElement();
				dept.setCoursesListFromDB();
				ArrayList<Course> coursesList = dept.getCoursesList();
				tableViewer.resetFilters();
				tableViewer.setInput(coursesList);
				tableViewer.refresh();
			}
		});

		tltmQuery.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				InputDialog idl = new InputDialog(shell, "课程名", "请输入要查询的课程名或课程名的部分文字。", "", null);
				if(idl.open()==0) {
					tableViewer.resetFilters();
					tableViewer.addFilter(new FilterName(idl.getValue()));
					tableViewer.refresh();
				}
			}
		});
	}

	ArrayList<Department> setDeptList() {
		deptList = new ArrayList<Department>();
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
		return deptList;
	}
	
	class FilterName extends ViewerFilter {
		String name;
		public FilterName(String name) {
			super();
			this.name = name;
		}
		public boolean select(Viewer arg0, Object arg1, Object arg2) {
			Course item = (Course)arg2;
			if(item.getName().indexOf(name)>=0)
				return true;			
			return false;
		}
	}

}
