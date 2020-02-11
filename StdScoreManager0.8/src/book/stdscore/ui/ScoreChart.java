package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import book.stdscore.data.*;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;

public class ScoreChart {

	protected Shell shell;
	private int cWidth;
	private int cHeight;
	private Student student;

	public ScoreChart(Student student) {
		super();
		this.student = student;
		open();
	}

	public Shell getShell() {
		return shell;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ScoreChart window = new ScoreChart(null);
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
		shell.setMinimumSize(new Point(800, 600));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				cWidth = canvas.getClientArea().width ;
				cHeight = canvas.getClientArea().height ;
				drawChart(arg0.gc);
				shell.layout();
			}
		});

	}

	void drawChart(GC gc) {
		ArrayList<Course> courseList = student.getCourseList(); 
		float score ;
		String name = null ;
		int num = courseList.size() ;
		int rWidth = (int)((cWidth/(num+1))*0.9) ; // 直方宽度
		int rSpace = cWidth/(num+1)-rWidth ;  // 直方间距
		int x1=50+rSpace,y1=cHeight-50,rHeight=0;
		// 画坐标轴
		gc.drawLine(50, cHeight-50, cWidth,cHeight-50 );
		gc.drawLine(50, 20, 50,cHeight-50 );
		int perHeight = (int)((cHeight-70)/10);
		for(int k=15; k<cHeight-50; k=k+perHeight) {
			int cy = 110-((k-15)/perHeight+1)*10;
			String str = ""+(cy<10?"  "+cy:(cy<100?" "+cy:cy));
			gc.drawString(str, 20, k+5);
			gc.drawLine(45, k+11, 50, k+11);
		}
		// 画直方图
		for (int i = 0;i<num;i++) {
			name = courseList.get(i).getName();
			score = courseList.get(i).getScore();
			rHeight = (int)((cHeight-70)/100*score);
			y1 = y1 - rHeight;
			Color oldBgColor = gc.getBackground() ;
			gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
			gc.fillRectangle(x1, y1, rWidth, rHeight);
			gc.setBackground(oldBgColor);
			gc.drawString(name, (int)(x1-rSpace*0.5),cHeight-48);
			x1 = x1 + rWidth + rSpace  ;
			y1 = cHeight -50 ;
		}
	}

}
