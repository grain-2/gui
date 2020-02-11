package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;

import book.stdscore.data.ClassTreeNode;
import book.stdscore.data.Course;
import book.stdscore.data.CourseTreeNode;
import book.stdscore.data.GradeClass;
import book.stdscore.data.InitDB;
import book.stdscore.data.ScoreCourseTreeRoot;
import book.stdscore.data.Teacher;
import book.stdscore.data.User;

public class TchScoreChart {

	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	private User user;
	protected Shell shell;
	private TreeViewer treeViewer;
	private Canvas canvas;
	private int[] frequency=null;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TchScoreChart window = new TchScoreChart(null);
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

	public TchScoreChart(User user) {
		super();
		this.user = user;
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setMinimumSize(new Point(600, 400));
		shell.setSize(450, 300);
		shell.setText("班级排课");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		treeViewer = new TreeViewer(sashForm, SWT.BORDER);
		treeViewer.setAutoExpandLevel(4);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
				TreeNode selNode = (TreeNode) ss.getFirstElement();
				if(selNode instanceof CourseTreeNode) {
					GradeClass aclass = (GradeClass) ((ClassTreeNode)(selNode.getParent())).getValue();
					setFrequency((Course)selNode.getValue(),aclass);
				} else {
					frequency = null;
				}
				canvas.redraw();
			}
		});
		treeViewer.setUseHashlookup(true);
		Tree tree = treeViewer.getTree();
		treeViewer.setLabelProvider(new ViewerLabelProvider());

		treeViewer.setContentProvider(new TreeNodeContentProvider(){
			@Override
			public Object[] getElements(Object inputElement) {
				return ((TreeNode) inputElement).getChildren();
			}
		});
		
		treeViewer.setInput(new ScoreCourseTreeRoot(Teacher.getFromDB(Integer.parseInt(user.getName()))));		

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		canvas = new Canvas(composite, SWT.NONE);
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				drawChart(arg0.gc);
				shell.layout();
			}
		});
				
		sashForm.setWeights(new int[] {1, 2});

	}
	
	void setFrequency(Course course, GradeClass aclass) {
		frequency = new int[5];
		for(int i=0;i<frequency.length;i++) {
			frequency[i] = 0 ;
		}
		String sql = "select SC.score from student AS S,student_course AS SC where S.id=SC.studentID " +
				"and SC.courseID=" + course.getId() + " and S.departmentId=" + aclass.getDepartmentId() +
				" and S.grade=" + aclass.getGrade() +" and S.class=" + aclass.getcClass();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {				
				float score = rs.getFloat(1);
				if(score<60)
					frequency[0]++;
				else if(score<70)
					frequency[1]++;
				else if(score<80)
					frequency[2]++;
				else if(score<90)
					frequency[3]++;
				else
					frequency[4]++;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	void drawChart(GC gc) {
		int cWidth = canvas.getClientArea().width ;
		int cHeight = canvas.getClientArea().height ;

		if(frequency==null || gc==null) {
			gc.fillRectangle(0, 0,cWidth,cHeight);
			return;
		}

		String name = null ;
		int num = frequency.length ;
		int maxFreq = -1;
		for(int i=0;i<num;i++) {
			if(frequency[i]>maxFreq)
				maxFreq = frequency[i] ;
		}
		int rWidth = (int)((cWidth/(num+1))*0.5) ; // 直方宽度
		int rSpace = cWidth/(num+1)-rWidth ;  // 直方间距
		int x1=50+rSpace,y1=cHeight-50,rHeight=0;
		// 画坐标轴
		gc.drawLine(50, cHeight-50, cWidth-100,cHeight-50 );  // 画横坐标轴
		gc.drawLine(50, 20, 50,cHeight-50 ); // 画纵坐标轴
		int perHeight = (int)((cHeight-70)/(maxFreq+1));
		for(int k=25; k<cHeight-50; k=k+perHeight) {
			int cy = maxFreq-(k-25)/perHeight+1;
			String str = ""+(cy<10?"  "+cy:(cy<100?" "+cy:cy));
			gc.drawString(str, 20, k-5);
			gc.drawLine(45, k, 50, k);
		}
		// 画直方图
		for (int i = 0;i<num;i++) {
			int value = frequency[i];
			if(i==0)
				name = "[0-60)";
			else if(i==1)
				name = "[60-70)";
			else if(i==2)
				name = "[70-80)";
			else if(i==3)
				name = "[80-90)";
			else if(i==4)
				name = "[90-100]";
			rHeight = (int)((cHeight-70)/(maxFreq+1)*value);
			y1 = y1 - rHeight;
			Color oldBgColor = gc.getBackground() ;
			gc.setBackground(shell.getDisplay().getSystemColor(SWT.COLOR_BLUE));
			gc.fillRectangle(x1, y1, rWidth, rHeight);
			gc.setBackground(oldBgColor);
			gc.drawString(name, (int)(x1-rSpace*0.5)+50, cHeight-48);
			x1 = x1 + rWidth + rSpace  ;
			y1 = cHeight -50 ;
		}
	}
	
}
