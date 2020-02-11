package book.stdscore.ui;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.List;
import book.stdscore.data.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;

public class DepartmentManager extends Dialog {
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
			return deptList.toArray(new Department[deptList.size()]);
			//return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Object result;
	protected Shell shell;
	private static ArrayList<Department> deptList;
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public DepartmentManager(Shell parent, int style) {
		super(parent, style);
		setText("专业设置");
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
		shell.setSize(450, 320);
		shell.setText("专业设置");
		shell.setLayout(new GridLayout(1, false));
		
		ToolBar toolBar = new ToolBar(shell, SWT.FLAT | SWT.RIGHT);
		
		ToolItem toolItemAdd = new ToolItem(toolBar, SWT.NONE);
		toolItemAdd.setText("添加");
		
		ToolItem toolItemModify = new ToolItem(toolBar, SWT.NONE);
		toolItemModify.setText("修改");
		
		ToolItem toolItemQuit = new ToolItem(toolBar, SWT.NONE);
		toolItemQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		toolItemQuit.setText("退出");
		
		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1, 1));
		composite.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		ListViewer listViewer = new ListViewer(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		List listDepartment = listViewer.getList();
		GridData gd_listDepartment = new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1);
		gd_listDepartment.heightHint = 119;
		gd_listDepartment.widthHint = 220;
		listDepartment.setLayoutData(gd_listDepartment);
		listViewer.setLabelProvider(new ViewerLabelProvider());
		listViewer.setContentProvider(new ContentProvider());
		listViewer.setInput(setDeptList().toArray(new Department[deptList.size()]));
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		new Label(composite, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));

		toolItemModify.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				StructuredSelection ss = (StructuredSelection) listViewer.getSelection();
				Department dept = (Department) ss.getFirstElement();
				if(dept==null) {
					MessageDialog.openWarning(shell, "没有选择专业", "修改之前请选择需要修改的专业！");
					return ;
				}
				InputDialog idl = new InputDialog(shell, "修改专业名", "请修改选择的专业名。", dept.getName(), null);
				if(idl.open()==0) {
					String name = idl.getValue();
					dept.setName(name);
					if(dept.updateToDB()>0) {
						//MessageDialog.openInformation(shell, "修改成功", "专业名修改成功。");
						listViewer.setInput(setDeptList().toArray(new Department[deptList.size()]));
						listViewer.refresh();
					}
				}
			}
		});
		toolItemAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				new DepartmentAdd(shell, SWT.None).open();
				listViewer.setInput(setDeptList().toArray(new Department[deptList.size()]));
				listViewer.refresh();			
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

}
