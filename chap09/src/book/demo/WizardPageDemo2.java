package book.demo;

import java.util.Scanner;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class WizardPageDemo2 extends WizardPage {
	
	WizardPageDemo2 wd2;
	private Button btnCheckButton;
	
	public Button getBtnCheckButton() {
		return btnCheckButton;
	}

	/**
	 * Create the wizard.
	 */
	public WizardPageDemo2() {
		super("wizardPage");
		setPageComplete(false);
		setTitle("Wizard Page 2");
		setDescription("Wizard Page description");
		wd2=this;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		btnCheckButton = new Button(container, SWT.CHECK);
		btnCheckButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				wd2.setPageComplete(btnCheckButton.getSelection());
			}
		});
		btnCheckButton.setBounds(132, 84, 143, 24);
		btnCheckButton.setText("Check Button");
	}
}
