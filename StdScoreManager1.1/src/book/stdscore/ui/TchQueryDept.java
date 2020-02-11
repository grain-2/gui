package book.stdscore.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.List;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.TableViewer;
import book.stdscore.data.*;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.graphics.Point;

public class TchQueryDept extends Dialog {
	private class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}
		public String getColumnText(Object element, int columnIndex) {
			Teacher tch = (Teacher) element;
			if(columnIndex==0) {
				return tch.getId()+"" ;
			} else if(columnIndex==1) {
				return tch.getName();
			}else if(columnIndex==2) {
				return tch.getSex();
			} else if(columnIndex==3) {
				return tch.getAge()+"";
			}else if(columnIndex==4) {
				return tch.getDepartment().getName();
			} else if(columnIndex==5) {
				return tch.getAddress();
			} else if(columnIndex==6) {
				return tch.getIntro();
			}

			return element.toString();
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

	protected Object result;
	protected Shell shell;
	private Table table;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public TchQueryDept(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
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
		shell = new Shell(getParent(), SWT.SHELL_TRIM | SWT.BORDER);
		shell.setMinimumSize(new Point(1024, 300));
		shell.setSize(450, 300);
		shell.setText(getText());
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		ListViewer listViewer = new ListViewer(sashForm, SWT.BORDER | SWT.V_SCROLL);
		List list = listViewer.getList();
		listViewer.setLabelProvider(new ViewerLabelProvider());
		listViewer.setContentProvider(new ArrayContentProvider());
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("工号");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.NONE);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("姓名");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("性别");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.NONE);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("年龄");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.NONE);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("部门");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.NONE);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("地址");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("简介");
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new ArrayContentProvider());
		listViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection)arg0.getSelection();
				Department dept = (Department) ss.getFirstElement();
				tableViewer.setInput(getTeacherList(dept));
				tableViewer.refresh();
			}
		});
		listViewer.setInput(getDeptList());
		sashForm.setWeights(new int[] {1, 2});

	}

	ArrayList<Department> getDeptList() {
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
		return departList;
	}
	
	ArrayList<Teacher> getTeacherList(Department dept) {
		ArrayList<Teacher> tchList = new ArrayList<Teacher>();
		InitDB db = new InitDB();
		ResultSet rs = db.getRs("select * from teacher where departmentID="+dept.getId());
		try {
			while(rs.next()) {
				tchList.add(new Teacher(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4), dept, 
						rs.getString(6), rs.getString(7), rs.getString(8)));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return tchList;
	}
}
