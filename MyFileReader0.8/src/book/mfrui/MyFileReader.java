package book.mfrui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
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
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.custom.StyledText;

public class MyFileReader {

	protected Shell shell;
	private StyledText textArea;
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
		shell.setMinimumSize(new Point(300, 200));
		shell.setSize(500, 305);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		final StyleRange sr = new StyleRange();
		final FontData fontData = shell.getDisplay().getSystemFont().getFontData()[0];

		ViewForm viewForm = new ViewForm(shell, SWT.NONE);

		ToolBar toolBar = new ToolBar(viewForm, SWT.FLAT | SWT.LEFT);
		viewForm.setTopRight(toolBar);

		ToolItem toolItemRecentFiles = new ToolItem(toolBar, SWT.DROP_DOWN);
		toolItemRecentFiles.setWidth(80);
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
								sr.start = 0;
								sr.length = textArea.getText().length();
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
						sr.start = 0;
						sr.length = textArea.getText().length();
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

		textArea = new StyledText(compositeText,
				SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		sashForm.setWeights(new int[] { 3, 7 });
		textArea.setStyleRange(sr);

		Composite compositeStyle = new Composite(viewForm, SWT.NONE);
		viewForm.setTopLeft(compositeStyle);
		compositeStyle.setLayout(new RowLayout(SWT.HORIZONTAL));

		Combo comboFontName = new Combo(compositeStyle, SWT.NONE);
		comboFontName.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				fontData.setName(comboFontName.getText());
				sr.font = new Font(null, fontData);
				textArea.setStyleRange(sr);
			}
		});
		comboFontName.setLayoutData(new RowData(100, SWT.DEFAULT));
		comboFontName.setToolTipText("选择一种字体");
		comboFontName.setText("字体");
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts = env.getAvailableFontFamilyNames();
		comboFontName.setItems(fonts);

		Combo comboFontSize = new Combo(compositeStyle, SWT.NONE);
		comboFontSize.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int height = Integer.parseInt(comboFontSize.getText());
				fontData.setHeight(height);
				sr.font = new Font(null, fontData);
				textArea.setStyleRange(sr);
			}
		});
		comboFontSize.setItems(
				new String[] { "8", "9", "10", "11", "12", "14", "16", "18", "20", "24", "28", "36", "48", "72" });
		comboFontSize.setToolTipText("选择一种字号");
		comboFontSize.setText("字号");

		Button buttonBold = new Button(compositeStyle, SWT.CHECK);
		buttonBold.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (buttonBold.getSelection()) {
					if (fontData.getStyle() == SWT.ITALIC) {
						fontData.setStyle(SWT.ITALIC | SWT.BOLD);
					} else {
						fontData.setStyle(SWT.BOLD);
					}
				} else {
					if (fontData.getStyle() == SWT.ITALIC || fontData.getStyle() == (SWT.ITALIC | SWT.BOLD)) {
						fontData.setStyle(SWT.ITALIC);
					} else {
						fontData.setStyle(SWT.NORMAL);
					}
				}
				sr.font = new Font(null, fontData);
				textArea.setStyleRange(sr);
			}
		});
		buttonBold.setText("粗体");

		Button buttonItalic = new Button(compositeStyle, SWT.CHECK);
		buttonItalic.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (buttonItalic.getSelection()) {
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
				textArea.setStyleRange(sr);
			}
		});
		buttonItalic.setText("斜体");

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
