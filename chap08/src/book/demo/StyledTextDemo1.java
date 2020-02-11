package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.wb.swt.SWTResourceManager;

public class StyledTextDemo1 {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StyledTextDemo1 window = new StyledTextDemo1();
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
		
		StyledText styledText = new StyledText(shell, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		styledText.setTabStops(new int[] {60, 200, 250});
		styledText.setBlockSelection(true);
		styledText.setWrapIndent(40);
		styledText.setIndent(20);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_YELLOW));
		styledText.setLeftMargin(10);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setText("abcde\r\nfghij");
//		System.out.println(styledText.getTextRange(2, 2));
//		System.out.println(styledText.getText(2, 3));
//		styledText.setSelection(8, 10);
//		styledText.setSelectionRange(8, 2);
		StyleRange sr = new StyleRange(1,3,new Color(null, 255,0,0),new Color(null, 0,255,0),SWT.BOLD);
		styledText.setStyleRange(sr);
		sr.strikeout = true;
		sr.underline = true ;
		//sr.metrics = new GlyphMetrics(2,2,2);
		sr.font = new Font(null,"Broadway",16,SWT.ITALIC);
	}

}
