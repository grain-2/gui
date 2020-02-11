package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

public class ComboViewer {
	private static class Sorter extends ViewerSorter {
		public int compare(Viewer viewer, Object e1, Object e2) {
			Department item1 = (Department)e1;
			Department item2 = (Department)e2;
			return item1.getName().compareTo(item2.getName());
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			if(element instanceof Department)
				return ((Department)element).getName();
			return element.toString();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			//Department dept = (Department)inputElement;
			//return new Object[]{inputElement};
			return getDepartments();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ComboViewer window = new ComboViewer();
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
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		org.eclipse.jface.viewers.ComboViewer comboViewer = new org.eclipse.jface.viewers.ComboViewer(shell, SWT.NONE);
		comboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				StructuredSelection ss = (StructuredSelection)arg0.getSelection();
				ss.getFirstElement();
			}
		});
		comboViewer.setSorter(new Sorter());
		Combo combo = comboViewer.getCombo();
		comboViewer.setLabelProvider(new ViewerLabelProvider());
		comboViewer.setContentProvider(new ContentProvider());
		comboViewer.setInput(getDepartments());
	}
	
	static Department[] getDepartments() {
		String sql = "select * from department";
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		ArrayList<Department> depts = new ArrayList<Department>();
		try {
			while(rs.next()) {
				depts.add(new Department(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return depts.toArray(new Department[depts.size()]);
	}

}
