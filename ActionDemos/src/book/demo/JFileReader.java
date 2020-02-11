package book.demo;

import java.awt.GraphicsEnvironment;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

import com.ibm.icu.text.SimpleDateFormat;
import org.eclipse.wb.swt.ResourceManager;

public class JFileReader extends ApplicationWindow {
	private StyledText textArea;
	private Tree tree;
	private TxtFileOpenAction txtFileOpenAction;
	private StyleRange sr;
	private FontData fontData;
	private MenuManager rfMenuManager;
	private static JFileReader window;
	private Action actionBold;
	private Action actionItalic;

	public FontData getFontData() {
		return fontData;
	}

	/**
	 * Create the application window.
	 */
	public JFileReader() {
		super(null);
		createActions();
		addToolBar(SWT.FLAT | SWT.WRAP);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		setStatus("欢迎使用简易文本阅读器！");
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tree = new Tree(composite, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
/*
		tree.addTreeListener(new TreeAdapter() {
			@Override
			public void treeExpanded(TreeEvent e) {
				TreeItem selItem = tree.getSelection()[0];
				if(selItem.getItemCount()==0)
					return ;
				String path;
				File file;
				File[] files;
				TreeItem item ;
				if(selItemChildren!=null && selItemChildren.length>0) {
					for(int i=0;i<selItemChildren.length;i++) {
						path = getItemPath(selItemChildren[i]);
						file = new File(path);
						System.out.println(file.getAbsolutePath());
						files = file.listFiles();
						if(files!=null) {
							for(File afile : files) {
								item = new TreeItem(selItemChildren[i],SWT.NONE);
								item.setText(afile.getName());
							}
						}						
					}
				}

			}
		});
*/
		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				TreeItem selItem = tree.getSelection()[0];
				String path = getItemPath(selItem); 
				File file = new File(path);
				if(file.isDirectory() && selItem.getItemCount()==0) {
					addChildren(selItem, file);
				} else if(file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
					//window.getStatusLineManager().setCancelEnabled(true);
					new showText(file).start();
/*
					try {
						FileReader fr = new FileReader(file);
						BufferedReader bfr = new BufferedReader(fr);
						textArea.setText("");
						String content = bfr.readLine();
						while (content != null) {
							textArea.append(content + "\r\n");
							content = bfr.readLine();
						}
						sr.start = 0 ;
						sr.length =textArea.getText().length();														
						// 将当前打开的文件名 readFile 写入记录文件 recent.rcd
						new RecentRecord().addMenuFile(file.getAbsolutePath());
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
*/					
				}
			}
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem selItem = tree.getSelection()[0];
				String path = getItemPath(selItem); 
				File file = new File(path);
				if(file.isDirectory() && selItem.getItemCount()==0) {
					addChildren(selItem, file);
				}				
			}
		});
		File[] disks = File.listRoots();
		TreeItem item;
		for(File disk : disks) {
			item = new TreeItem(tree,SWT.NONE);
			if(disk.canRead()) {
				item.setText(disk.getPath().substring(0, 2));
				addChildren(item, disk);
			}
		}
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		textArea = new StyledText(composite_1, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		sashForm.setWeights(new int[] {1, 2});
		sr = new StyleRange();
		fontData = Display.getCurrent().getSystemFont().getFontData()[0];

		//创建右键快捷菜单
		MenuManager contextMenu = new MenuManager();
		MenuManager fnMenu = new MenuManager("字体");
		createFontNameMenu(fnMenu);
		contextMenu.add(fnMenu);
		MenuManager fsMenu = new MenuManager("字号");
		createFontSizeMenu(fsMenu);
		contextMenu.add(fsMenu);
		contextMenu.add(new TaFontBoldAction());
		contextMenu.add(new TaFontItalicAction());
		textArea.setMenu(contextMenu.createContextMenu(textArea));

		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
		txtFileOpenAction = new TxtFileOpenAction();
		{
			actionBold = new Action("粗体字(&B)") {
					// TODO 自动生成的方法存根
					super.run();
					//this.setChecked(this.isChecked());
					if (this.isChecked()) {
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
			};
			actionBold.setChecked(false);
			actionBold.setImageDescriptor(ResourceManager.getImageDescriptor(JFileReader.class, "/icons/bold.jpg"));
			actionBold.setAccelerator(SWT.CTRL | 'B');
		}
		{
			actionItalic = new Action("斜体(&I)") {
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
					textArea.setStyleRange(sr);	
				}
			};
			actionItalic.setChecked(false);
			actionItalic.setImageDescriptor(ResourceManager.getImageDescriptor(JFileReader.class, "/icons/italic.jpg"));
			actionItalic.setAccelerator(SWT.CTRL | 'I');
		}
	}

	/**
	 * Create the menu manager.
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		// 创建【文件】菜单
		MenuManager fileMenuManager = new MenuManager("文件"); 
		// 向【文件】菜单添加【打开】菜单项
		fileMenuManager.add(txtFileOpenAction);		
		// 创建【最近打开】菜单的子菜单
		RecentOpenAction roa = new RecentOpenAction(fileMenuManager);
		// 向【文件】菜单添加【最近打开】菜单项
		fileMenuManager.add(rfMenuManager);
		fileMenuManager.add(new MyExitAction());
		
		menuManager.add(fileMenuManager);
		return menuManager;
	}
	
	void createFontNameMenu(MenuManager fontNameMenuManager) {
		if(fontNameMenuManager==null)
			fontNameMenuManager = new MenuManager("字体");
		if(fontNameMenuManager.getSize()==0) {
			GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
			String[] fonts = env.getAvailableFontFamilyNames();
			for (int i = fonts.length-1; i>=0; i--) {
				TaFontNameAction fnAction = new TaFontNameAction(fonts[i]);
				fnAction.setText(fonts[i]);
				fontNameMenuManager.add(fnAction);		
			}
		}
	}
	
	void createFontSizeMenu(MenuManager fontSizeMenuManager) {
		if(fontSizeMenuManager==null)
			fontSizeMenuManager = new MenuManager("字号");
		if(fontSizeMenuManager.getSize()==0) {
			int[] fontSize = new int[] { 8,9,10,11,12,14,16,18,20,24,28,36,48,72};
			for (int i = 0; i<fontSize.length; i++) {
				TaFontSizeAction fsAction = new TaFontSizeAction(fontSize[i]);
				fsAction.setText(fontSize[i]+"");
				fontSizeMenuManager.add(fsAction);		
			}
		}
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT | SWT.WRAP);
		toolBarManager.add(txtFileOpenAction);
		toolBarManager.add(new RecentOpenAction(toolBarManager));
		TaFontNameAction tfa = new TaFontNameAction("宋体");
		tfa.setToolTipText("功能尚未实现");
		tfa.setEnabled(false);
		toolBarManager.add(tfa);
		TaFontSizeAction tsa = new TaFontSizeAction(10);
		tsa.setToolTipText("功能尚未实现");
		tsa.setEnabled(false);
		toolBarManager.add(tsa);
		//toolBarManager.add(new TaFontBoldAction());
		//toolBarManager.add(new TaFontItalicAction());
		toolBarManager.add(actionBold);
		toolBarManager.add(actionItalic);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		statusLineManager.add(new SLAction("                                                             "));
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			window = new JFileReader();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		newShell.setMinimumSize(new Point(800, 600));
		super.configureShell(newShell);
		newShell.setText("第一个JFace程序");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}

	private TreeItem[] addChildren(TreeItem aitem, File file) {
		File[] files = file.listFiles();
		TreeItem[] items=null;
		if(files!=null) {
			items = new TreeItem[files.length];
			for(int i=0;i<files.length;i++) {
				items[i] = new TreeItem(aitem,SWT.NONE);
				items[i].setText(files[i].getName());
			}
		}
		return items;
	}

	private String getItemPath(TreeItem selItem) {
		TreeItem parentItem = selItem.getParentItem();
		String path = selItem.getText();
		while(parentItem != null) {
			path = parentItem.getText() + "\\" + path;
			parentItem = parentItem.getParentItem() ;
		}
		return path;
	}
	
	void statusBarLocate(int piw,char dir) {
		StatusLineManager statusLineManager = window.getStatusLineManager();
		Composite cp = (Composite) statusLineManager.getControl();
		Control[] controls = cp.getChildren();

		CLabel cl = (CLabel) controls[0];
		cl.setSize(400, 34);

		Composite cmp = (Composite) controls[2];
		int off = cl.getSize().x+20; // 靠左位置
		if(dir=='r'&&controls.length==3)
			off = window.getShell().getSize().x-70-piw;
		cmp.setLocation(off, 0);
		cmp.setSize(piw, 34);
		final ProgressIndicator pi = (ProgressIndicator) (cmp.getChildren()[0]);
		pi.setLocation(0, 4);
		pi.setSize(piw, 26);
		pi.beginTask(100);
		pi.worked(0);

		ToolBar tb = (ToolBar) controls[1];
		int tbw=0;
		if(statusLineManager.isCancelEnabled())
			tbw = 25;
		off = cmp.getLocation().x+cmp.getSize().x+20;
		if(dir=='r'&&controls.length==3)
			off = window.getShell().getSize().x-tbw-20;
		tb.setLocation(off, 6);
		tb.setSize(tbw, 24);
		ToolItem tm = tb.getItem(0);
		tm.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// TODO 自动生成的方法存根
				super.widgetSelected(e);
				statusLineManager.setCancelEnabled(false);
				pi.done();
			}

		});
		cp.setVisible(true);
	}
	
	public class TxtFileOpenAction extends Action {
	
		private String path;
	
		public TxtFileOpenAction() {
			super();
			this.setText("打开(&O) @Ctrl+O");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/open.jpg"));
			this.setToolTipText("打开选择的文本文件并显示其内容");		
		}
	
		public TxtFileOpenAction(String path) {
			super();
			this.path = path;
		}
	
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			if(this.path==null) {
				TreeItem selItem = tree.getSelection()[0];
				TreeItem parentItem = selItem.getParentItem();
				this.path = selItem.getText();
				while(parentItem != null) {
					this.path = parentItem.getText() + "\\" + this.path;
					parentItem = parentItem.getParentItem() ;
				}
			}
			
			File file = new File(this.path);
			if(file.isFile() && file.getName().toLowerCase().endsWith(".txt")) {
				window.getStatusLineManager().setCancelEnabled(true);
				new showText(file).start();
/*
				try {
					FileReader fr = new FileReader(file);
					BufferedReader bfr = new BufferedReader(fr);
					textArea.setText("");
					String content = bfr.readLine();
					while (content != null) {
						textArea.append(content + "\r\n");
						content = bfr.readLine();
					}
					sr.start = 0 ;
					sr.length =textArea.getText().length();			
					setStatus("打开的文件是：" + this.path);
					// 将当前打开的文件名 readFile 写入记录文件 recent.rcd
					new RecentRecord().addMenuFile(file.getAbsolutePath());
					bfr.close();
					fr.close();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
*/
			} else {
				setStatus("没有选择文本文件！");
			}
		}
	
	}
	
	class SLAction extends Action {

		public SLAction(String text) {
			super(text);
			// TODO 自动生成的构造函数存根
			this.setEnabled(false);
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();

		}
	}

	class showText extends Thread {
		private File file;
		
		public showText(File file) {
			super();
			this.file = file;
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			Display.getDefault().asyncExec(new Runnable(){
				public void run() {
					statusBarLocate(100,'l');
					Composite cp = (Composite)window.getStatusLineManager().getControl();
					Composite cmp = (Composite)cp.getChildren()[2];
					ProgressIndicator pi = (ProgressIndicator) cmp.getChildren()[0];
					int txtBytes = (int)file.length();
					setStatus("正在打开文件：" + file.getAbsolutePath());
					pi.beginTask(txtBytes);
					textArea.setText("");
					try {
						FileReader fr = new FileReader(file);
						BufferedReader bfr = new BufferedReader(fr);
						String content = bfr.readLine();
						while (content != null) {
							textArea.append(content + "\r\n");
							pi.worked(content.getBytes().length);
							content = bfr.readLine();
						}
						pi.sendRemainingWork();
						Button bt = (Button)cp.getChildren()[3];
						Date dt = new Date(file.lastModified());
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
						bt.setText("文件更新日期："+sdf.format(dt));
						setStatus("打开的文件是：" + file.getAbsolutePath());
						// 将当前打开的文件名 readFile 写入记录文件 recent.rcd
						new RecentRecord().addMenuFile(file.getAbsolutePath());			
						sr.start = 0 ;
						sr.length =textArea.getText().length();			
						if(bfr!=null)
							bfr.close();
						if(fr!=null)
							fr.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			});
		}
		
	}
	
	public class RecentOpenAction extends Action {
		private ContributionManager manager;

		public RecentOpenAction(ContributionManager manager) {
			super();
			// TODO 自动生成的构造函数存根
			this.manager = manager;
			this.setText("最近打开");
			//this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/open.jpg"));
			this.setToolTipText("显示最近打开过的文本文件的内容");
			if(rfMenuManager==null)
				rfMenuManager = new MenuManager("最近打开");
			if(rfMenuManager.getSize()==0) {
			String[] tmstr = new RecentRecord().getFiles();
				for (int i = 0; i < 5 && tmstr[i] != null; i++) {
					TxtFileOpenAction rfAction = new TxtFileOpenAction(tmstr[i]);
					rfAction.setText(tmstr[i]);
					rfMenuManager.add(rfAction)	;	
				}
			}
		}
		
		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();

			if(manager instanceof ToolBarManager) {
				ToolBar toolBar = ((ToolBarManager)manager).getControl() ;
				Menu menuRecentFiles = new Menu(toolBar);
				MenuItem[] rfMenus =rfMenuManager.getMenu().getItems();
				for (int i = 0; i < 5 && rfMenus[i] != null; i++) {
					MenuItem mntmNewRadiobutton = new MenuItem(menuRecentFiles, SWT.NONE);
					mntmNewRadiobutton.setText(rfMenus[i].getText());
					mntmNewRadiobutton.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							String readFile = ((MenuItem) e.getSource()).getText();
							TxtFileOpenAction rfAction = new TxtFileOpenAction(readFile);
							rfAction.run();
						}
					});
				}

				Rectangle bound = toolBar.getBounds();
				Point point = toolBar.toDisplay(bound.x, bound.y + bound.height);
				menuRecentFiles.setLocation(point);
				menuRecentFiles.setVisible(true);
			}
		}
	}

	public class MyExitAction extends Action {
		@Override

		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			getShell().close();
		}

		public MyExitAction() {
			super();
			// TODO 自动生成的构造函数存根
			this.setText("退出(&x)@Ctrl+X");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/exit.jpg"));
			this.setToolTipText("退出程序");
		}			
	}
	
	public class TaFontNameAction extends Action {
		private String fontName;
		
		public TaFontNameAction(String fontName) {
			super();
			this.fontName = fontName;
			this.setText("字体(&M) @Ctrl+M");
			//this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/open.jpg"));
			this.setToolTipText("选择一种字体");		
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();				
			fontData.setName(fontName);
			sr.font = new Font(null, fontData);
			textArea.setStyleRange(sr);		
		}
		
	}

	public class TaFontSizeAction extends Action {
		private int fontSize=10;
		
		public TaFontSizeAction(int fontSize) {
			super();
			this.fontSize = fontSize;
			this.setText("字号(&S) @Ctrl+S");
			//this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/open.jpg"));
			this.setToolTipText("选择一种字号");		
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			fontData.setHeight(fontSize);
			sr.font = new Font(null, fontData);
			textArea.setStyleRange(sr);		
		}
	}

	public class TaFontBoldAction extends Action {

		public TaFontBoldAction() {
			super();
			this.setText("粗体(&B) @Ctrl+B");
			this.setImageDescriptor(ImageDescriptor.createFromFile(getClass(), "/icons/bold.jpg"));
			this.setToolTipText("设置粗体字显示");
			this.setChecked(false);
		}

		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			this.setChecked(this.isChecked());
			if (this.isChecked()) {
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
	}
	
	public class TaFontItalicAction extends Action {

		public TaFontItalicAction() {
			super();
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
			textArea.setStyleRange(sr);	
		}
	}

}