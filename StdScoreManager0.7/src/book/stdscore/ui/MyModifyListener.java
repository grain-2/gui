package book.stdscore.ui;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

class MyModifyListener implements ModifyListener {
	Shell shell = null ;
	String regex = null ;
	String guide = null ;
	MyModifyListener(Shell shell,String regex,String guide) {
		this.shell = shell ;
		this.regex = regex ;
		this.guide = guide ;
	}
	public void modifyText(ModifyEvent e) {
		String[] cn = e.getSource().toString().split(" ") ;
		if(cn[0].equals("Text")) {
			String txt = ((Text)e.getSource()).getText()==null?"":((Text)e.getSource()).getText().trim();
			if(!"".equals(txt) && !isValidChar(txt)) {
				((Text)e.getSource()).setText("") ;
			}
		} else if(cn[0].equals("Combo")) {
			String txt = ((Combo)e.getSource()).getText()==null?"":((Combo)e.getSource()).getText().trim();
			if(!"?".equals(txt) && !isValidChar(txt)) {
				((Combo)e.getSource()).setText("?") ;
			}
		}
	}
	boolean isValidChar(String cont) {
		boolean isV = true ;
		if(!cont.matches(this.regex)) {
			MessageBox msgBox = new MessageBox(shell);
			msgBox.setMessage(this.guide);
			msgBox.open();
			isV = false ;
		}
		return isV ;
	}
}