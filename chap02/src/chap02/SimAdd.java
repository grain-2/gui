package chap02;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SimAdd {

	private Shell shell;
	private Text textNum1;
	private Text textNum2;
	private Button btnClear;
	private Button btnAdd;
	private Button btnExit;
	private Label labelResult;
	private Text textResult;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SimAdd window = new SimAdd();
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
		
		Label labelNum1 = new Label(shell, SWT.NONE);
		labelNum1.setBounds(85, 30, 90, 24);
		labelNum1.setText("第一个数：");
		
		Label labelNum2 = new Label(shell, SWT.NONE);
		labelNum2.setBounds(85, 70, 90, 24);
		labelNum2.setText("第二个数：");
		
		textNum1 = new Text(shell, SWT.BORDER);
		textNum1.setBounds(181, 27, 130, 30);
		
		textNum2 = new Text(shell, SWT.BORDER);
		textNum2.setBounds(181, 67, 130, 30);
		
		btnAdd = new Button(shell, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int int1 = Integer.parseInt(textNum1.getText().trim());
				int int2 = Integer.parseInt(textNum2.getText().trim());
				textResult.setText((int1+int2)+"");				
			}
		});
		btnAdd.setBounds(55, 178, 80, 34);
		btnAdd.setText("相加");
		
		btnClear = new Button(shell, SWT.NONE);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				textNum1.setText("");
				textNum2.setText("");
				textResult.setText("");
			}
		});
		btnClear.setBounds(165, 178, 80, 34);
		btnClear.setText("清除");
		
		btnExit = new Button(shell, SWT.NONE);
		btnExit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBounds(275, 178, 80, 34);
		btnExit.setText("退出");
		
		labelResult = new Label(shell, SWT.NONE);
		labelResult.setText("计算结果：");
		labelResult.setBounds(85, 118, 90, 24);
		
		textResult = new Text(shell, SWT.BORDER | SWT.READ_ONLY);
		textResult.setBounds(181, 115, 130, 30);
		
	}
}
