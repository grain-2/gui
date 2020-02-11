package book.demo;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.CoolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.dialogs.ProgressIndicator;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class JFaceStatusBarDemo extends ApplicationWindow {
	static JFaceStatusBarDemo window;
	/**
	 * Create the application window,
	 */
	public JFaceStatusBarDemo() {
		super(null);
		createActions();
		addCoolBar(SWT.FLAT);
		addMenuBar();
		addStatusLine();
	}

	/**
	 * Create contents of the application window.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createContents(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setStatus("一个长长的状态栏，有许多文字。");
		return container;
	}

	/**
	 * Create the actions.
	 */
	private void createActions() {
		// Create the actions
	}

	/**
	 * Create the menu manager.
	 * 
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the coolbar manager.
	 * 
	 * @return the coolbar manager
	 */
	@Override
	protected CoolBarManager createCoolBarManager(int style) {
		CoolBarManager coolBarManager = new CoolBarManager(style);
		return coolBarManager;
	}


	/**
	 * Create the status line manager.
	 * 
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		statusLineManager.add(new SLAction("第一个", statusLineManager));

		return statusLineManager;
	}

	class SLAction extends Action {
		private String text;
		private StatusLineManager statusLineManager;
		ProgressIndicator pi;

		public SLAction(String text, StatusLineManager statusLineManager) {
			super(text);
			// TODO 自动生成的构造函数存根
			this.text = text;
			this.statusLineManager = statusLineManager;
			statusLineManager.setCancelEnabled(true);
		}

		@Override
		public void run() {
			// TODO 自动生成的方法存根
			super.run();
			statusLineManager.setErrorMessage("错误信息:" + text);
			statusLineManager.setErrorMessage(null);
			statusLineManager.setMessage("状态栏信息：" + text);
			Composite cp = (Composite) statusLineManager.getControl();
			cp.setBackground(new Color(null, 255, 255, 255));
			System.out.println("Composite cp in : " + cp.getLocation().x + "," + cp.getLocation().y);
			System.out.println("Composite cp wdith=" + cp.getSize().x + ", height=" + cp.getSize().y);
			Control[] controls = cp.getChildren();
			for (int i = 0; i < controls.length; i++) {
				System.out.println(i + " : " + controls[i].toString());
				if (controls[i] instanceof CLabel) {
					CLabel cl = (CLabel) controls[i];
					cl.setText("CLabel");
					cl.setForeground(new Color(null, 255, 0, 0));
					cl.setBackground(new Color(null, 255, 255, 0));
					cl.pack();
					System.out.println("CLabel in : " + cl.getLocation().x + "," + cl.getLocation().y);
					System.out.println("CLabel wdith=" + cl.getSize().x + ", height=" + cl.getSize().y);
				} else if (controls[i] instanceof ToolBar) {
					ToolBar tb = (ToolBar) controls[i];
					tb.setLocation(160, 6);
					tb.setSize(25, 24);
					tb.setBackground(new Color(null, 0, 255, 0));
					tb.setVisible(true);
					System.out.println("ToolBar in : " + tb.getLocation().x + "," + tb.getLocation().y);
					System.out.println("ToolBar wdith=" + tb.getSize().x + ", height=" + tb.getSize().y);
					System.out.println("ToolBar length :" + tb.getItems().length);
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
					System.out.println(
							"ToolItem in : " + tb.getItem(0).getBounds().x + "," + tb.getItem(0).getBounds().y);
					System.out.println("ToolItem wdith=" + tb.getItem(0).getBounds().width + ", height="
							+ tb.getItem(0).getBounds().height);
					System.out.println("" + tb.getItem(0).getEnabled());
				} else if (controls[i] instanceof Composite) {
					Composite cmp = (Composite) controls[i];
					cmp.setLocation(190, 0);
					cmp.setSize(100, 34);
					cmp.setBackground(new Color(null, 0, 255, 255));
					System.out.println("Composite in : " + cmp.getLocation().x + "," + cmp.getLocation().y);
					System.out
							.println("Composite width=" + cmp.getBounds().width + ",height=" + cmp.getBounds().height);
					// System.out.println("Composite of length : "+(cmp.getChildren()).length);
					pi = (ProgressIndicator) (cmp.getChildren()[0]);
					pi.setLocation(0, 4);
					pi.setSize(100, 26);
					pi.beginTask(100);
					pi.worked(0);
					System.out.println("Composite of contents : " + (cmp.getChildren()[0]).toString());
					System.out.println("PI in : " + pi.getBounds().x + "," + pi.getBounds().y);
					System.out.println("PI wdith=" + pi.getBounds().width + ", height=" + pi.getBounds().height);
					cmp.setVisible(true);

					Runnable Run = new Runnable() {
						public void run() {
							for (int i = 0; i < 100; i++) {
								try {
									// 让线程睡眠0.1秒
									Thread.sleep(100);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								// 让UI线程更新滚动条

								Display.getDefault().asyncExec(new Runnable() {
									public void run() {
										pi.worked(1);
										if (pi.isDisposed())
											return;
									}
								});
							}
						}
					};
					pi.setVisible(true);
					new Thread(Run).start();
					statusLineManager.setCancelEnabled(true);
					cmp.setVisible(true);

				} else if (controls[i] instanceof Button) {
					Button btt = (Button) controls[i];
					btt.pack();
					btt.setBackground(new Color(null, 255, 0, 0));
					System.out.println("Button in : " + btt.getLocation().x + "," + btt.getLocation().y);
					System.out.println("Button wdith : " + btt.getSize().x + ", height:" + btt.getSize().y);
				}
			}
		}

	}

	/**
	 * 显示状态栏中的进度指示器
	 * @param dir 进度指示器是否靠左还是靠右放置，靠右传字符‘r’
	 * @param piw 进度指示器宽度
	 */
	void statusBarLocate(int piw,char dir) {
		StatusLineManager statusLineManager = window.getStatusLineManager();
		Composite cp = (Composite) statusLineManager.getControl();
		Control[] controls = cp.getChildren();

		CLabel cl = (CLabel) controls[0];
		//cl.setText("CLabel");
		cl.pack();

		Composite cmp = (Composite) controls[2];
		GridLayout layout = (GridLayout)cmp.getLayout();
/*		
		int cmp3rd=0;
		if(statusOthers==null) {
			cmp3rd = piw;
		} else {
			cmp3rd = piw + statusOthers.getSize().x;
		}
*/
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
/*
		Runnable Run = new Runnable() {
			public void run() {
				for (int i = 0; i < 100; i++) {
					try {
						// 让线程睡眠0.1秒
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					// 让UI线程更新滚动条
					Display.getDefault().asyncExec(new Runnable() {
						public void run() {
							pi.worked(1);
							if (pi.isDisposed())
								return;
						}
					});
				}
			}
		};
*/
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			window = new JFaceStatusBarDemo();
			window.setBlockOnOpen(true);
			window.open();
			Display.getCurrent().dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Configure the shell.
	 * 
	 * @param newShell
	 */
	@Override
	protected void configureShell(Shell newShell) {
		newShell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				window.getStatusLineManager().setCancelEnabled(true);
				statusBarLocate(100,'l');
			}
		});
		super.configureShell(newShell);
		newShell.setText("New Application");
	}

	/**
	 * Return the initial size of the window.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 300);
	}
}
