package book.stdscore.ui;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.widgets.Table;

public class UserJobEditor extends EditingSupport {
	private TableViewer tv;
	
	public UserJobEditor(ColumnViewer viewer) {
		super(viewer);
		// TODO 自动生成的构造函数存根
		this.tv = (TableViewer)viewer ;
	}

	@Override
	protected boolean canEdit(Object arg0) {
		// TODO 自动生成的方法存根
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object arg0) {
		// TODO 自动生成的方法存根
		Table table = tv.getTable();
		CellEditor cellEditor = new ComboBoxCellEditor(table, new String[]{"学生","教师","管理员"}) ;	
		return  cellEditor;
	}

	@Override
	protected Object getValue(Object arg0) {
		// TODO 自动生成的方法存根
		String jstr = ((String[])arg0)[2];
		return Integer.parseInt(jstr);
	}

	@Override
	protected void setValue(Object arg0, Object arg1) {
		// TODO 自动生成的方法存根
		((String[])arg0)[2]=arg1.toString();
		tv.update(arg0, (String[])tv.getColumnProperties());
	}

}
