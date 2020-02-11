package book.stdscore.ui;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Tree;

import book.stdscore.data.ClassTreeNode;
import book.stdscore.data.Course;
import book.stdscore.data.CourseTreeNode;
import book.stdscore.data.Department;
import book.stdscore.data.DepartmentTreeNode;
import book.stdscore.data.GradeClass;
import book.stdscore.data.GradeTreeNode;
import book.stdscore.data.InitDB;
import book.stdscore.data.SelCourseTreeRoot;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.CellEditor;

public class TreeViewerDemo2 extends ApplicationWindow {
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	/**
	 * Create the application window.
	 */
	public TreeViewerDemo2() {
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
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		{
			TreeViewer treeViewer = new TreeViewer(container, SWT.BORDER);
			treeViewer.setUseHashlookup(true);
			Tree tree = treeViewer.getTree();
			tree.setLinesVisible(true);
			tree.setHeaderVisible(true);
			{
				TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
				treeViewerColumn.setLabelProvider(new ColumnLabelProvider() {
					public Image getImage(Object element) {
						// TODO Auto-generated method stub
						return null;
					}
					public String getText(Object element) {
						// TODO Auto-generated method stub
						return element == null ? "" : element.toString();
					}
				});
/*
				treeViewerColumn.setEditingSupport(new EditingSupport(treeViewer) {
					protected boolean canEdit(Object element) {
						// TODO Auto-generated method stub
						return true;
					}
					protected CellEditor getCellEditor(Object element) {
						// TODO Auto-generated method stub
						return new TextCellEditor();
					}
					protected Object getValue(Object element) {
						// TODO Auto-generated method stub
						return element.toString();
					}
					protected void setValue(Object element, Object value) {
						// TODO Auto-generated method stub
						((TreeNode)element).getValue();
					}
				});
*/			
				TreeColumn trclmnNewColumn = treeViewerColumn.getColumn();
				trclmnNewColumn.setWidth(267);
			}
			{
				TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
				treeViewerColumn.setLabelProvider(new ColumnLabelProvider() {
					public Image getImage(Object element) {
						// TODO Auto-generated method stub
						return null;
					}
					public String getText(Object element) {
						// TODO Auto-generated method stub
						if(element instanceof CourseTreeNode) {
							int courseId = ((Course)(((CourseTreeNode) element).getValue())).getId();
							ClassTreeNode ctn = (ClassTreeNode)(((CourseTreeNode) element).getParent().getParent());
							int cClass = ((GradeClass)ctn.getValue()).getcClass();
							GradeTreeNode gtn = (GradeTreeNode) ctn.getParent();
							int grade = ((GradeClass) gtn.getValue()).getGrade();
							DepartmentTreeNode dtn = (DepartmentTreeNode) gtn.getParent();
							int deptId= ((Department) dtn.getValue()).getId();
							String sql= "select count(student_course.id) from student,student_course where student.departmentId=" +
									deptId +" and student.grade=" + grade + " and student.class=" + cClass + 
									" and student_course.studentID=student.id and  student_course.courseID=" + courseId;
							
							ResultSet rs = InitDB.getInitDB().getRs(sql);
							int num = 0 ;		
							try {
								if(rs.next())
									num = rs.getInt(1);
							} catch (SQLException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
							return num+"";
						}
						return "";
					}
				});
				TreeColumn trclmnNewColumn_1 = treeViewerColumn.getColumn();
				trclmnNewColumn_1.setWidth(188);
				trclmnNewColumn_1.setText("待选学生人数");
			}
			{
				TreeViewerColumn treeViewerColumn = new TreeViewerColumn(treeViewer, SWT.NONE);
				TreeColumn trclmnNewColumn_2 = treeViewerColumn.getColumn();
				treeViewerColumn.setEditingSupport(new EditingSupport(treeViewer) {
					protected boolean canEdit(Object element) {
						// TODO Auto-generated method stub
						if(element instanceof CourseTreeNode)
							return true;
						else
							return false;
					}
					protected CellEditor getCellEditor(Object element) {
						// TODO Auto-generated method stub
						return new TextCellEditor(tree);
					}
					protected Object getValue(Object element) {
						// TODO Auto-generated method stub
						//System.out.println(element.getClass());
						return element.toString();
					}
					protected void setValue(Object element, Object value) {
						// TODO Auto-generated method stub
						//System.out.println(element.getClass()+","+value);
						((Course)(((CourseTreeNode)element).getValue())).setName(value.toString());
						treeViewer.update(element, (String[]) treeViewer.getColumnProperties());
					}
				});
				treeViewerColumn.setLabelProvider(new ColumnLabelProvider() {
					public Image getImage(Object element) {
						// TODO Auto-generated method stub
						return null;
					}
					public String getText(Object element) {
						// TODO Auto-generated method stub
						return element.toString();
					}
				});
				trclmnNewColumn_2.setWidth(100);
				trclmnNewColumn_2.setText("备注");
			}

			//treeViewer.setLabelProvider(new ViewerLabelProvider());

			treeViewer.setContentProvider(new TreeNodeContentProvider(){
/*
				@Override
				public Object[] getChildren(Object parentElement) {
					return ((TreeNode) parentElement).getChildren();
				}
*/
				@Override
				public Object[] getElements(Object inputElement) {
					return ((TreeNode) inputElement).getChildren();
				}
/*
				@Override
				public Object getParent(Object element) {
					return ((TreeNode) element).getParent();
				}
				@Override
				public boolean hasChildren(Object element) {
					return ((TreeNode) element).hasChildren();
				}
*/
			});
			
/*			
			treeViewer.setLabelProvider(new ILabelProvider(){

				@Override
				public void addListener(ILabelProviderListener arg0) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public void dispose() {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public boolean isLabelProperty(Object arg0, String arg1) {
					// TODO 自动生成的方法存根
					return false;
				}

				@Override
				public void removeListener(ILabelProviderListener arg0) {
					// TODO 自动生成的方法存根
					
				}

				@Override
				public Image getImage(Object arg0) {
					// TODO 自动生成的方法存根
					return null;
				}

				@Override
				public String getText(Object arg0) {
					// TODO 自动生成的方法存根
				//	if (arg0 instanceof TreeNode) {
						((TreeNode) arg0).getValue().toString();
				//	}
					return arg0.toString();
				}
				
			});
*/
			treeViewer.setInput(new SelCourseTreeRoot());
		}

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
	 * @return the menu manager
	 */
	@Override
	protected MenuManager createMenuManager() {
		MenuManager menuManager = new MenuManager("menu");
		return menuManager;
	}

	/**
	 * Create the toolbar manager.
	 * @return the toolbar manager
	 */
	@Override
	protected ToolBarManager createToolBarManager(int style) {
		ToolBarManager toolBarManager = new ToolBarManager(style);
		return toolBarManager;
	}

	/**
	 * Create the status line manager.
	 * @return the status line manager
	 */
	@Override
	protected StatusLineManager createStatusLineManager() {
		StatusLineManager statusLineManager = new StatusLineManager();
		return statusLineManager;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			TreeViewerDemo2 window = new TreeViewerDemo2();
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
