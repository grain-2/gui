package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.layout.GridData;

public class ScrolledCompositeTest {

	protected Shell shell;
	private Text textTitle;
	private Text textContent;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScrolledCompositeTest window = new ScrolledCompositeTest();
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
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		Composite compositeText = new Composite(scrolledComposite, SWT.NONE);
		compositeText.setLayout(new GridLayout(2, false));
		
		Label labelTitle = new Label(compositeText, SWT.NONE);
		labelTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		labelTitle.setText("主题：");
		
		textTitle = new Text(compositeText, SWT.BORDER);
		textTitle.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label labelText = new Label(compositeText, SWT.NONE);
		labelText.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		labelText.setText("正文：");
		
		textContent = new Text(compositeText, SWT.BORDER | SWT.WRAP | SWT.MULTI);
		GridData gd_textContent = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_textContent.widthHint = 360;
		textContent.setLayoutData(gd_textContent);
		scrolledComposite.setContent(compositeText);
		scrolledComposite.setMinSize(compositeText.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		scrolledComposite.setMinSize(new Point(400, 300));

	}
}
