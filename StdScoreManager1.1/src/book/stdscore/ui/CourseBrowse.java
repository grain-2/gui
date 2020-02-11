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
import org.eclipse.swt.widgets.Table;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
public class CourseBrowse extends Dialog {
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
	private Table tableCourse;
	private static ArrayList<Department> deptList;

	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public CourseBrowse(Shell parent, int style) {
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
		shell = new Shell(getParent(), getStyle());
		shell.setSize(450, 300);
		shell.setText("课程浏览");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		ListViewer listViewer = new ListViewer(sashForm, SWT.BORDER | SWT.V_SCROLL);
		List listDept = listViewer.getList();
		listViewer.setLabelProvider(new ViewerLabelProvider());
		listViewer.setContentProvider(new ContentProvider());
		listViewer.setInput(listDept);
		
		TableViewer tableViewer = new TableViewer(sashForm, SWT.BORDER | SWT.FULL_SELECTION);
		tableCourse = tableViewer.getTable();
		sashForm.setWeights(new int[] {4, 6});

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
