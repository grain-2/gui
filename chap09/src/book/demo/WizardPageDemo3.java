package book.demo;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WizardPageDemo3 extends WizardPage {
	WizardPageDemo3 wd3;
	private Spinner spinner;

	public Spinner getSpinner() {
		return spinner;
	}

	/**
	 * Create the wizard.
	 */
	public WizardPageDemo3() {
		super("wizardPage");
		setPageComplete(false);
		setTitle("Wizard Page 3");
		setDescription("Wizard Page description");
		wd3 = this ;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		spinner = new Spinner(container, SWT.BORDER);
		spinner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wd3.setPageComplete(Integer.parseInt(spinner.getText())>0);
			}
		});
		spinner.setBounds(191, 112, 68, 31);
	}
}
