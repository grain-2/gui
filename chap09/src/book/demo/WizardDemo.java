package book.demo;

import org.eclipse.jface.wizard.Wizard;

public class WizardDemo extends Wizard {

	private WizardPageDemo1 wpd1;
	private WizardPageDemo2 wpd2;
	private WizardPageDemo3 wpd3;

	public WizardDemo() {
		setWindowTitle("New Wizard");
		this.setNeedsProgressMonitor(true);
	}

	@Override
	public void addPages() {
		wpd1 = new WizardPageDemo1();
		addPage(wpd1);
		wpd2 = new WizardPageDemo2();
		addPage(wpd2);
		wpd3 = new WizardPageDemo3();
		addPage(wpd3);
	}

	@Override
	public boolean performFinish() {
		boolean finish = true;
		if(wpd1.getText()!=null && !"第一页".equals(wpd1.getText()))
			finish = false;
		if(!wpd2.getBtnCheckButton().getSelection())
			finish = false;
		if(Integer.parseInt(wpd3.getSpinner().getText())>10)
			finish = true;
		return finish;
	}

}
