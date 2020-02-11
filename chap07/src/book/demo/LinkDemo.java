package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Link;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class LinkDemo {

	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LinkDemo window = new LinkDemo();
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
		
		Link link = new Link(shell, SWT.NONE);
		link.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String url = pareseLinkUrl(link.getText());
					Runtime.getRuntime().exec("C:\\Program Files (x86)\\Internet Explorer\\iexplore.exe " + url);
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		link.setBounds(61, 61, 79, 24);
		link.setText("<a href=\"http://www.sohu.com/\">搜狐网站</a>");

	}
	
	public String pareseLinkUrl(String str) {
		String url=null;
		if(str.startsWith("<a")) {
			char pre = str.charAt(str.indexOf("href=")+5);
			int startp = -1 ;
			int endp = -1;
			if(pre=='"' || pre=='\'') {
				startp = str.indexOf("href=")+6;
				endp = str.indexOf(pre, startp+1);
			} else {
				startp = str.indexOf("href=")+5;
				endp = str.indexOf(" ",startp+1);
				if(endp==-1)
					endp = str.indexOf(">",startp+1);
			}
			url = str.substring(startp, endp);
		}
		return url;
	}
}
