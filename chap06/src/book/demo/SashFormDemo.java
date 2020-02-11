package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SashFormDemo {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SashFormDemo window = new SashFormDemo();
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
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setSashWidth(5);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		List listFileTree = new List(composite, SWT.BORDER);
		listFileTree.setItems(new String[] {"1", "2", "3", "4", "5"});
		listFileTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				System.out.println("widgetDefaultSelected： " + listFileTree.getSelection()[0]);
			}
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("widgetSelected： " + listFileTree.getSelection()[0]);
			}
		});
		
		text = new Text(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {1, 2});
		sashForm.setMaximizedControl(null);
	}
}
