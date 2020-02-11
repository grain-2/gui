package studentTree;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.graphics.Image;
import java.lang.Object;
import java.lang.String;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.ui.dialogs.ContainerCheckedTreeViewer;

public class TableTreeDemo {

	private Shell sShell = null;
	private Tree tree = null;
	//private TreeViewer treeViewer = null;
	private ContainerCheckedTreeViewer treeViewer = null;

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
		TableTreeDemo thisClass = new TableTreeDemo();
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
		sShell.setSize(new Point(300, 200));
		sShell.setLayout(new FillLayout());
		MyTreeData myTreeData = new MyTreeData();
		myTreeData.setParent(null);
		myTreeData.setChildren(myTreeData.getDepartmentList().toArray(
				new Department[myTreeData.getDepartmentList().size()]));
		tree = new Tree(sShell, SWT.CHECK);
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		//treeViewer = new TreeViewer(tree);
		//treeViewer = new CheckboxTreeViewer(tree);
		treeViewer = new ContainerCheckedTreeViewer(tree);
		treeViewer.setContentProvider(new TreeNodeContentProvider() {

			@Override
			public Object[] getChildren(Object parentElement) {
				// TODO Auto-generated method stub
				return ((TreeNode) parentElement).getChildren();
			}

			@Override
			public Object[] getElements(Object inputElement) {
				// TODO Auto-generated method stub
				//treeViewer.setGrayedElements(((TreeNode) inputElement).getChildren());
				return ((TreeNode) inputElement).getChildren();
			}

			@Override
			public Object getParent(Object element) {
				// TODO Auto-generated method stub
				//treeViewer.setParentsGrayed(element, true);
				return ((TreeNode) element).getParent();
			}

			@Override
			public boolean hasChildren(Object element) {
				// TODO Auto-generated method stub
				return ((TreeNode) element).hasChildren();
			}

		});
		treeViewer.setLabelProvider(new ITableLabelProvider() {

			private int sumPeaple;
			private float sumScore;

			public Image getColumnImage(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				return null;

			}

			public String getColumnText(Object arg0, int arg1) {
				// TODO Auto-generated method stub
				if (arg0 instanceof TreeNode) {
					TreeNode node = (TreeNode) arg0;
					if (arg1 == 0) {
						return node.getValue().toString();
					} else if (arg1 == 1) {
						if (node instanceof Department) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (Grade grade : (Grade[]) (node.getChildren())) {
								for (CClass cClass : (CClass[]) (grade
										.getChildren())) {
									for (Student student : (Student[]) (cClass
											.getChildren())) {
										sumPeaple++;
										sumScore += student.getScore();
									}
								}
							}
							return sumPeaple + "";
						} else if (node instanceof Grade) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (CClass cClass : (CClass[]) (node.getChildren())) {
								for (Student student : (Student[]) (cClass
										.getChildren())) {
									sumPeaple++;
									sumScore += student.getScore();
								}
							}
							return sumPeaple + "";
						} else if (node instanceof CClass) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (Student student : (Student[]) (node
									.getChildren())) {
								sumPeaple++;
								sumScore += student.getScore();
							}
							return sumPeaple + "";
						}
					} else if (arg1 == 2) {
						if (node instanceof Department) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (Grade grade : (Grade[]) (node.getChildren())) {
								for (CClass cClass : (CClass[]) (grade
										.getChildren())) {
									for (Student student : (Student[]) (cClass
											.getChildren())) {
										sumPeaple++;
										sumScore += student.getScore();
									}
								}
							}
							return sumScore / (float) sumPeaple + "";
						} else if (node instanceof Grade) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (CClass cClass : (CClass[]) (node.getChildren())) {
								for (Student student : (Student[]) (cClass
										.getChildren())) {
									sumPeaple++;
									sumScore += student.getScore();
								}
							}
							return sumScore / (float) sumPeaple + "";
						} else if (node instanceof CClass) {
							sumPeaple = 0;
							sumScore = 0.0F;
							for (Student student : (Student[]) (node
									.getChildren())) {
								sumPeaple++;
								sumScore += student.getScore();
							}
							return sumScore / (float) sumPeaple + "";
						}
					}
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
		TreeColumn treeColumn = new TreeColumn(tree, SWT.NONE);
		treeColumn.setWidth(150);
		treeColumn.setMoveable(true);
		treeColumn.setResizable(true);
		treeColumn.setText("");
		TreeColumn treeColumn1 = new TreeColumn(tree, SWT.NONE);
		treeColumn1.setWidth(60);
		treeColumn1.setText("人数");
		TreeColumn treeColumn2 = new TreeColumn(tree, SWT.NONE);
		treeColumn2.setWidth(80);
		treeColumn2.setText("平均分");
		treeViewer.setInput(myTreeData);
		treeViewer.addCheckStateListener(new ICheckStateListener() {

			@Override
			public void checkStateChanged(CheckStateChangedEvent arg0) {
				// TODO Auto-generated method stub
				//TreeNode node = (TreeNode)arg0.getElement() ;
				Object[]nodes = treeViewer.getCheckedElements();
				for(Object node : nodes) {
					System.out.println(node);
				}
			}
			
		}) ;

	}

}
