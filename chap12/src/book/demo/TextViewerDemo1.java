package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.jface.fieldassist.ControlDecoration;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.layout.GridLayout;

public class TextViewerDemo1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TextViewerDemo1 window = new TextViewerDemo1();
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
		shell.setLayout(new GridLayout(1, false));
		
		TextViewer textViewer = new TextViewer(shell, SWT.BORDER);
		StyledText styledText = textViewer.getTextWidget();
		
		ControlDecoration controlDecoration = new ControlDecoration(styledText, SWT.LEFT | SWT.TOP);
		controlDecoration.setMarginWidth(30);
		controlDecoration.setImage(SWTResourceManager.getImage(TextViewerDemo1.class, "/icons/close.JPG"));
		controlDecoration.setDescriptionText("Some description");

	}

}
