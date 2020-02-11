package book.stdscore.ui;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

class UserLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void dispose() {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO 自动生成的方法存根
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
		// TODO 自动生成的方法存根
		return null;
	}

	@Override
	public String getColumnText(Object arg0, int arg1) {
		// TODO 自动生成的方法存根
		String[] user = (String[]) arg0;
		if (arg1 == 0)
			return user[0];
		if (arg1 == 1)
			return user[1];
		if (arg1 == 2) {
			String jobStr = null;
			switch (user[2]) {
				case "0":
					jobStr = "学生";
					break;
				case "1":
					jobStr = "教师";
					break;
				case "2":
					jobStr = "管理员";
					break;
			}
			return jobStr;
		}

		return null;
	}

}