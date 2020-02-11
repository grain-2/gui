package book.demo;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.DragDetectListener;
import org.eclipse.swt.events.DragDetectEvent;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;

public class DragDropDemo {

	protected Shell shell;
	private Text text;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DragDropDemo window = new DragDropDemo();
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
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(26, 26, 97, 30);
		Transfer[] types = new Transfer[]{TextTransfer.getInstance()};
		DragSource dragSource = new DragSource(text, DND.DROP_MOVE);
		dragSource.setTransfer(types);
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				//event.detail = DND.DROP_MOVE;
				if(text.getText().trim().equals(""))
					return;
				if (TextTransfer.getInstance().isSupportedType(event.dataType)) {
					//给event.data赋值字符串
					event.data = text.getText().trim();
					text.setText("");
				}
			}
		});
		
		List list = new List(shell, SWT.BORDER);
		list.setBounds(187, 26, 140, 164);
		
		DropTarget dropTarget = new DropTarget(list, DND.DROP_MOVE);
		dropTarget.setTransfer(types);
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				if (TextTransfer.getInstance().isSupportedType(event.currentDataType)) {
					String str = (String) event.data;
					list.add(str);
				}				
			}
		});
	}
}
