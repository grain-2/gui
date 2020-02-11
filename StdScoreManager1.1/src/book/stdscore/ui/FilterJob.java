package book.stdscore.ui;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

class FilterJob extends ViewerFilter { // 过滤身份字段
	private static FilterJob filterJob;
	private FilterJob() {
		if(filterJob==null)
			filterJob = new FilterJob();
	}
	
	public boolean select(Viewer arg0, Object arg1, Object arg2) {
		String[] item = (String[])arg2;
		if(item[2].equals("教师"))
			return true;
		return false;
	}

	public static FilterJob getFilterJob() {
		return filterJob;
	}
	
}

