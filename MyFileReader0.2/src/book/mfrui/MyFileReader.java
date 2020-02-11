package book.mfrui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.ViewForm;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class MyFileReader {

	protected Shell shell;
	private Text textArea;
	private String filePath;
	private List listFiles;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MyFileReader window = new MyFileReader();
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

		ViewForm viewForm = new ViewForm(shell, SWT.NONE);

		SashForm sashForm = new SashForm(viewForm, SWT.NONE);
		viewForm.setContent(sashForm);

		Composite compositeFiles = new Composite(sashForm, SWT.BORDER);
		compositeFiles.setLayout(new FillLayout(SWT.HORIZONTAL));

		listFiles = new List(compositeFiles, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		listFiles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				String filesName = (listFiles.getSelection())[0];
				if (filesName.startsWith("[DIR]") || filesName.startsWith("[上一级]")) {
					filePath = filesName.substring(5);
					addFilesList();
				} else if (filesName.toLowerCase().endsWith(".txt")) {
					File readFile = new File(filePath + filesName);
					try {
						FileReader fr = new FileReader(readFile);
						BufferedReader bfr = new BufferedReader(fr);
						textArea.setText("");
						String content = bfr.readLine();
						while (content != null) {
							textArea.append(content + "\r\n");
							content = bfr.readLine();
						}
						// 将当前打开的文件名 filePath+filesName 写入记录文件 recent.rcd
						new RecentRecord().addMenuFile(filePath + filesName);
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				}

			}
		});

		Composite compositeText = new Composite(sashForm, SWT.NONE);
		compositeText.setLayout(new FillLayout(SWT.HORIZONTAL));

		textArea = new Text(compositeText,
				SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (sashForm.getMaximizedControl() == null) {
					sashForm.setMaximizedControl(compositeText);
				} else {
					sashForm.setMaximizedControl(null);
				}
			}
		});
		sashForm.setWeights(new int[] { 3, 7 });

		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT | SWT.LEFT);
		viewForm.setTopLeft(toolBar);

		ToolItem toolItemRecentFiles = new ToolItem(toolBar, SWT.DROP_DOWN);
		toolItemRecentFiles.setWidth(200);
		toolItemRecentFiles.setText("最近打开");

		// toolBar.setMenu(menuRecentFiles);

		toolItemRecentFiles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// menuRecentFiles
				Menu menuRecentFiles = new Menu(toolBar);
				String[] tmstr = new RecentRecord().getFiles();
				for (int i = 0; i < 5 && tmstr[i] != null; i++) {
					MenuItem mntmNewRadiobutton = new MenuItem(menuRecentFiles, SWT.RADIO);
					mntmNewRadiobutton.setText(tmstr[i]);
					mntmNewRadiobutton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String readFile = ((MenuItem) e.getSource()).getText();
							try {
								FileReader fr = new FileReader(readFile);
								BufferedReader bfr = new BufferedReader(fr);
								textArea.setText("");
								String content = bfr.readLine();
								while (content != null) {
									textArea.append(content + "\r\n");
									content = bfr.readLine();
								}
								// 将当前打开的文件名 readFile 写入记录文件 recent.rcd
								new RecentRecord().addMenuFile(readFile);
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							} catch (IOException e2) {
								// TODO Auto-generated catch block
								e2.printStackTrace();
							}
						}
					});
				}
				Rectangle bound = toolItemRecentFiles.getBounds();
				Point point = toolBar.toDisplay(bound.x, bound.y + bound.height);
				menuRecentFiles.setLocation(point);
				menuRecentFiles.setVisible(true);
			}
		});

		addFilesList();
		viewForm.layout();
	}

	public void addFilesList() {
		listFiles.removeAll();
		File[] files;
		if (filePath == null || filePath.equals("")) { // 程序初启时列出盘符
			files = File.listRoots();
		} else {
			filePath = filePath.endsWith("\\") ? filePath : filePath + "\\";
			File dirFiles = new File(filePath);
			listFiles.add("[上一级]" + (dirFiles.getParent() == null ? "" : dirFiles.getParent()));
			files = dirFiles.listFiles();
		}
		if (files != null) {
			for (File file : files) {
				if (file.isFile())
					listFiles.add(file.getName());
				else if (file.isDirectory())
					listFiles.add("[DIR]" + file.getPath());
			}
		}
	}
}
