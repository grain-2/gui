package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.RowLayout;

public class TabFolderDemo {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TabFolderDemo window = new TabFolderDemo();
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
		
		TabFolder tabFolder = new TabFolder(shell, SWT.NONE);
		tabFolder.setBounds(10, 10, 408, 224);
		
		TabItem tabItem = new TabItem(tabFolder, SWT.NONE);
		tabItem.setText("爱好");
		
		Group group = new Group(tabFolder, SWT.BORDER | SWT.SHADOW_ETCHED_IN);
		tabItem.setControl(group);
		group.setLayout(new RowLayout(SWT.HORIZONTAL));
		
		Button button = new Button(group, SWT.CHECK);
		button.setText("绘画");
		
		Button button_1 = new Button(group, SWT.CHECK);
		button_1.setText("唱歌");
		
		Button button_2 = new Button(group, SWT.CHECK);
		button_2.setText("跳舞");
		
		Button button_3 = new Button(group, SWT.CHECK);
		button_3.setText("弹钢琴");
		
		Button button_4 = new Button(group, SWT.CHECK);
		button_4.setText("打篮球");
		
		Button button_5 = new Button(group, SWT.CHECK);
		button_5.setText("踢足球");
		
		Button button_6 = new Button(group, SWT.CHECK);
		button_6.setText("羽毛球");
		
		TabItem tabItem_1 = new TabItem(tabFolder, SWT.NONE);
		tabItem_1.setText("简历");
		
		text = new Text(tabFolder, SWT.BORDER);
		text.setText("个人简历：");
		tabItem_1.setControl(text);
		tabFolder.setSelection(1);
		group.layout();
	}
}
