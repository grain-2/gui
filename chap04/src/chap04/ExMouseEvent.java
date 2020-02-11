package chap04;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseWheelListener;

public class ExMouseEvent {

	protected Shell shell;
	private Text textArea;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExMouseEvent window = new ExMouseEvent();
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
		shell.addMouseWheelListener(new MouseWheelListener() {
			public void mouseScrolled(MouseEvent arg0) {
				if(arg0.count==3) {
					shell.setSize(shell.getSize().x+10, 300);
				}
				if(arg0.count==-3) {
					shell.setSize(shell.getSize().x-10, 300);
				}
			}
		});
		shell.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				textArea.setText("鼠标移动到窗口中（"+arg0.x+","+arg0.y+"）位置。") ;				
			}
		});
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		
		Button button = new Button(shell, SWT.NONE);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				textArea.setText("鼠标双击了" + e.getSource().toString()) ;
			}
			@Override
			public void mouseDown(MouseEvent e) {
				String btno = e.button==1?"左键":(e.button==2?"中键":"右键") ;
				textArea.setText("鼠标在" + e.getSource().toString() +"按下" ) ;
				textArea.append("\n你按下的是:" + btno) ;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				String btno = e.button==1?"左键":(e.button==2?"中键":"右键") ;
				textArea.setText("鼠标在" + e.getSource().toString() +"键被松开" ) ;
				textArea.append("\n你松开的是:" + btno) ;
			}
		});
		button.setBounds(35, 30, 114, 34);
		button.setText("初始按钮");
		
		textArea = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textArea.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseEnter(MouseEvent e) {
				textArea.setText("\n鼠标进入" + e.getSource().toString()) ;
			}
			@Override
			public void mouseExit(MouseEvent e) {
				textArea.append("\n鼠标退出" + e.getSource().toString()) ;
			}
			@Override
			public void mouseHover(MouseEvent e) {
				textArea.append("\n鼠标悬停在" + e.getSource().toString()) ;
			}
		});
		textArea.setBounds(35, 99, 300, 120);

	}
	
	
}
