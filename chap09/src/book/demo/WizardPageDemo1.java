package book.demo;

import java.util.Scanner;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.wb.swt.ResourceManager;

public class WizardPageDemo1 extends WizardPage {
	private Text text;
	WizardPageDemo1 wd1;

	public Text getText() {
		return text;
	}

	/**
	 * Create the wizard.
	 */
	public WizardPageDemo1() {
		super("wizardPage");
		setImageDescriptor(ResourceManager.getImageDescriptor(WizardPageDemo1.class, "/org/eclipse/jface/dialogs/images/message_info.gif"));
		setPageComplete(false);
		setTitle("Wizard Page 1");
		setDescription("Wizard Page description");
		wd1 = this;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		text = new Text(container, SWT.BORDER);
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				if(text.getText()!=null && !"".equals(text.getText().trim()))
					wd1.setPageComplete(true);	
			}
		});
		text.setBounds(143, 105, 73, 30);
	}
}
