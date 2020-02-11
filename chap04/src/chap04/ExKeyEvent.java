package chap04;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TraverseListener;
import org.eclipse.swt.events.TraverseEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

public class ExKeyEvent {

	protected Shell shell;
	private Text text;
	private Text text1;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ExKeyEvent window = new ExKeyEvent();
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
		
		text = new Text(shell, SWT.BORDER | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text.append(e.getSource() + " 获得焦点。") ;
			}
			@Override
			public void focusLost(FocusEvent e) {
				text.append(e.getSource() + " 失去焦点。") ;
			}
		});
		text.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				text.setText("你按下了键 " + e.character) ;
				text.append(" , 该键的键码是：" + e.keyCode) ;
				if((e.stateMask & SWT.ALT)!=0) {
					text.setText("你按下了 ALT 键 。 ") ;
					text.append(" , 该键的键码是：" + e.keyCode) ;
				} else if((e.stateMask & SWT.CTRL)!=0) {
					text.setText("你按下了 CTRL 键 。 ") ;
					text.append(" , 该键的键码是：" + e.keyCode) ;
				} else if((e.stateMask&SWT.SHIFT)!=0) {
					text.setText("你按下了 SHIFT 键 。 ") ;
					text.append(" , 该键的键码是：" + e.keyCode) ;
				}
				if((e.stateMask & SWT.ALT)!=0 && (e.stateMask & SWT.CTRL)!=0) {
					text.setText("你按下了ALT 和 CTRL 键 。 ") ;
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				text.append("\n你松开了键 " + e.character) ;
				text.append(" , 该键的键码是：" + e.keyCode) ;
			}
		});
		text.setBounds(41, 25, 200, 100);
		
		text1 = new Text(shell, SWT.BORDER);
		text1.addTraverseListener(new TraverseListener() {
			public void keyTraversed(TraverseEvent arg0) {
				arg0.doit = false ;
			}
		});
		text1.setBounds(41, 182, 300, 30);

	}

}
