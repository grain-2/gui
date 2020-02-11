package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class ProgressBarDemo {

	protected Shell shell;
	private Display display;
	private ProgressBar progressBar;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ProgressBarDemo window = new ProgressBarDemo();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
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
		
		progressBar = new ProgressBar(shell, SWT.NONE);
		progressBar.setBounds(27, 26, 260, 26);
		
		Button buttonPrbStart = new Button(shell, SWT.NONE);
		buttonPrbStart.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				progressBar.setSelection(0);
				new ProgressBarRunning().start() ;
			}
		});
		buttonPrbStart.setBounds(330, 26, 88, 34);
		buttonPrbStart.setText("开始");

	}
	
	class ProgressBarRunning extends Thread {
		public void run() {
			// 执行一个长时间的加法任务
			int startNum=0, endNum=1000, sum=0;
			for (int k=0;k<10;k++) {
				for(int i=startNum;i<endNum;i++) {
					sum+=i;
				}
				System.out.println("从 "+startNum+" 到 " + endNum+" 自然数之和为 "+sum);
				startNum = endNum ;
				endNum = endNum + 1000 ;
				sum = 0 ;
				display.asyncExec(new Runnable(){
					public void run() {
						progressBar.setSelection(progressBar.getSelection()+10);
					}
				});
			}
		}
	}
}
