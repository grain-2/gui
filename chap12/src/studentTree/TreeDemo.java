package studentTree;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import java.lang.Object;
import java.lang.String;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;

public class TreeDemo {

	private MyTreeData myTreeData;

	private Shell sShell = null;
	private SashForm sashForm = null;
	private Tree tree = null;
	private Composite composite = null;

	private TreeViewer treeViewer = null;

	private Table table = null;

	private TableViewer tableViewer = null;

	/**
	 * This method initializes sashForm
	 * 
	 */
	private void createSashForm() {
		sashForm = new SashForm(sShell, SWT.NONE);
		tree = new Tree(sashForm, SWT.NONE);
		tree.setLinesVisible(false);
		treeViewer = new TreeViewer(tree);
		treeViewer.setContentProvider(new TreeNodeContentProvider() {

			@Override
			public Object[] getChildren(Object parentElement) {
				// TODO Auto-generated method stub
				return ((TreeNode) parentElement).getChildren();
			}

			@Override
			public Object[] getElements(Object inputElement) {
				// TODO Auto-generated method stub
				return ((TreeNode) inputElement).getChildren();
			}

			@Override
			public Object getParent(Object element) {
				// TODO Auto-generated method stub
				return ((TreeNode) element).getParent();
			}

			@Override
			public boolean hasChildren(Object element) {
				// TODO Auto-generated method stub
				return ((TreeNode) element).hasChildren();
			}

		});
		treeViewer.setLabelProvider(new ILabelProvider() {

			public Image getImage(Object arg0) {
				// TODO Auto-generated method stub
				return null;

			}

			public String getText(Object arg0) {
				// TODO Auto-generated method stub
				if (arg0 instanceof TreeNode) {
					((TreeNode) arg0).getValue().toString();
				}
				return arg0.toString();

			}

			public void addListener(ILabelProviderListener arg0) {
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object arg0, String arg1) {
				// TODO Auto-generated method stub
				return false;

			}

			public void removeListener(ILabelProviderListener arg0) {
			}
		});
		treeViewer.setInput(myTreeData);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent arg0) {
				// TODO Auto-generated method stub
				TreeSelection selectedNode = (TreeSelection) arg0
						.getSelection();
				if (selectedNode.getFirstElement() instanceof CClass) {
					CClass cClass = (CClass) selectedNode.getFirstElement();
					Student[] stdArray = (Student[]) cClass.getChildren();
					tableViewer.setInput(stdArray);
				}
			}

		});
		createComposite();
		sashForm.setWeights(new int[]{35,65}) ;
	}

	/**
	 * This method initializes composite
	 * 
	 */
	private void createComposite() {
		composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout());
		table = new Table(composite, SWT.NONE);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		tableViewer = new TableViewer(table);
		tableViewer.setLabelProvider(new ITableLabelProvider() {

			public Image getColumnImage(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;

			}

			public String getColumnText(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				Student std = (Student) arg0;
				if (arg1 == 0) {
					return std.getId()+"";
				} else if (arg1 == 1) {
					return std.getName();
				}
				if (arg1 == 2) {
					String score = std.getScore()>0.5F?std.getScore()+"" : "" ;
					return score ;
				}

				return null;

			}

			public void addListener(ILabelProviderListener arg0) {
			}

			public void dispose() {
			}

			public boolean isLabelProperty(Object arg0, String arg1) {
				// TODO Auto-generated method stub
				return false;

			}

			public void removeListener(ILabelProviderListener arg0) {
			}
		});
		tableViewer.setContentProvider(new ArrayContentProvider());
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("Ñ§ºÅ");
		TableColumn tableColumn1 = new TableColumn(table, SWT.NONE);
		tableColumn1.setWidth(80);
		tableColumn1.setText("ÐÕÃû");
		TableColumn tableColumn2 = new TableColumn(table, SWT.NONE);
		tableColumn2.setWidth(60);
		tableColumn2.setText("³É¼¨");
		tableViewer.setInput(new Student[0]);
//		tableViewer.setInput(myTreeData.getStudentAll()
//				.toArray(new Student[myTreeData.getStudentAll().size()]));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 * Before this is run, be sure to set up the launch configuration
		 * (Arguments->VM Arguments) for the correct SWT library path in order
		 * to run with the SWT dlls. The dlls are located in the SWT plugin jar.
		 * For example, on Windows the Eclipse SWT 3.1 plugin jar is:
		 * installation_directory\plugins\org.eclipse.swt.win32_3.1.0.jar
		 */
		Display display = Display.getDefault();
		TreeDemo thisClass = new TreeDemo();
		thisClass.createSShell();
		thisClass.sShell.open();

		while (!thisClass.sShell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * This method initializes sShell
	 */
	private void createSShell() {
		sShell = new Shell();
		sShell.setText("Shell");
		createSashForm();
		sShell.setSize(new Point(400, 350));
		sShell.setLayout(new FillLayout());
		sShell.layout();
	}

	public TreeDemo() {
		super();
		myTreeData = new MyTreeData();
		myTreeData.setParent(null);
		myTreeData.setChildren(myTreeData.getDepartmentList().toArray(
				new Department[myTreeData.getDepartmentList().size()]));
	}

}
