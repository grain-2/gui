package book.demo;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class TaFontItalicAction extends Action  {

	private StyleRange sr;
	private FontData fontData;
//	private StyledText styledText;
	JFaceAppDemo1 window;
	Display display;

		public TaFontItalicAction(JFaceAppDemo1 window,Display display) {
			super();
			this.window = window;
			this.sr = new StyleRange();
			fontData =  display.getSystemFont().getFontData()[0];
			this.fontData = fontData;
			this.setText("斜体(&I) @Ctrl+I");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/italic.jpg"));
			this.setToolTipText("设置斜体字显示");		
			this.setChecked(false);
		}

		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			this.setChecked(this.isChecked());
			if (this.isChecked()) {
				if (fontData.getStyle() == SWT.BOLD) {
					fontData.setStyle(SWT.ITALIC | SWT.BOLD);
				} else {
					fontData.setStyle(SWT.ITALIC);
				}
			} else {
				if (fontData.getStyle() == SWT.BOLD || fontData.getStyle() == (SWT.ITALIC | SWT.BOLD)) {
					fontData.setStyle(SWT.BOLD);
				} else {
					fontData.setStyle(SWT.NORMAL);
				}
			}		
			sr.font = new Font(null, fontData);
			this.window.getStyledText().setStyleRange(sr);	
		}
}
