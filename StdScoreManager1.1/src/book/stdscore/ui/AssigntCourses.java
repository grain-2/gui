package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

import book.stdscore.data.*;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DropTargetAdapter;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeNodeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public class AssigntCourses {
	private static class ViewerLabelProvider_2 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Teacher)element).getName();
		}
	}
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return ((Department)element). getName();
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return element.toString();
		}
	}

	protected Shell shell;
	private Table tableNoSelected;
	private Table tableSelected;
	private Clipboard clipboard;
	private TreeViewer treeViewer;
	private ComboViewer comboViewerName;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AssigntCourses window = new AssigntCourses();
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
		clipboard = new Clipboard(display);
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
		shell.setMinimumSize(new Point(900, 600));
		shell.setSize(450, 300);
		shell.setText("班级排课");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		treeViewer = new TreeViewer(sashForm, SWT.BORDER);
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent arg0) {
				StructuredSelection ss = (StructuredSelection) treeViewer.getSelection();
				TreeNode selNode = (TreeNode) ss.getFirstElement();
				if(selNode instanceof CourseTreeNode) {
					GradeClass aclass = (GradeClass) ((ClassTreeNode)(selNode.getParent().getParent())).getValue();
					ArrayList<String[]> scoreList = getStdList(aclass);
					tableSelected.removeAll();
					TableItem item;
					for(String[] strArr : scoreList) {
						item = new TableItem(tableSelected, SWT.NONE);
						item.setText(strArr);			
					}
				}
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
		
		treeViewer.setInput(new SelCourseTreeRoot());		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label label = new Label(composite, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 42);
		fd_label.left = new FormAttachment(0, 65);
		label.setLayoutData(fd_label);
		label.setText("任课教师");
		
		ComboViewer comboViewerDept = new ComboViewer(composite, SWT.NONE);
		Combo comboDept = comboViewerDept.getCombo();
		//Combo comboDept = new Combo(composite, SWT.NONE);
		FormData fd_comboDept = new FormData();
		fd_comboDept.right = new FormAttachment(label, 175, SWT.RIGHT);
		fd_comboDept.left = new FormAttachment(label, 15);
		fd_comboDept.top = new FormAttachment(0, 39);
		comboDept.setLayoutData(fd_comboDept);
		
		Label label_1 = new Label(composite, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 80);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("未选学生");
		
		tableNoSelected = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		fd_label_1.bottom = new FormAttachment(100, -420);
		FormData fd_tableNoSelected = new FormData();
		fd_tableNoSelected.left = new FormAttachment(0, 10);
		fd_tableNoSelected.top = new FormAttachment(label_1, 6);
		fd_tableNoSelected.bottom = new FormAttachment(100, -94);
		tableNoSelected.setLayoutData(fd_tableNoSelected);
		tableNoSelected.setHeaderVisible(true);
		tableNoSelected.setLinesVisible(true);
		
		tableSelected = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		FormData fd_tableSelected = new FormData();
		fd_tableSelected.right = new FormAttachment(100, -50);
		fd_tableSelected.bottom = new FormAttachment(100, -94);
		tableSelected.setLayoutData(fd_tableSelected);
		tableSelected.setHeaderVisible(true);
		tableSelected.setLinesVisible(true);
		
		Label label_2 = new Label(composite, SWT.NONE);
		fd_tableSelected.top = new FormAttachment(label_2, 6);
		FormData fd_label_2 = new FormData();
		fd_label_2.left = new FormAttachment(comboDept, 60);
		fd_label_2.top = new FormAttachment(0, 101);
		fd_label_2.right = new FormAttachment(100, -84);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("已选学生");
		
		Button buttonAdd = new Button(composite, SWT.NONE);
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items =  tableNoSelected.getSelection();
				String[][] data = new String[items.length][2];
				for(int i=0;i<items.length;i++) {
					data[i][0] = items[i].getText(0) ;
					data[i][1] = items[i].getText(1) ;
				}
				boolean hasItem = false;
				TableItem item = null;
				TableItem[] selItems =  tableSelected.getItems();
				for(int i=0;i<data.length;i++) {
					for(TableItem aitem : selItems) {
						if(aitem.getText(0).equals(data[i][0])) {
							hasItem=true;
							break;
						}
					}
					if(!hasItem) {
						item = new TableItem(tableSelected, SWT.NONE);
						item.setText(data[i]);
						int idx = tableNoSelected.indexOf(items[i]);
						tableNoSelected.remove(idx);
					}
				}
			}
		});
		fd_tableSelected.left = new FormAttachment(buttonAdd, 25);
		fd_tableNoSelected.right = new FormAttachment(buttonAdd, -26);
		FormData fd_buttonAdd = new FormData();
		fd_buttonAdd.top = new FormAttachment(comboDept, 158);
		fd_buttonAdd.right = new FormAttachment(100, -292);
		fd_buttonAdd.left = new FormAttachment(0, 242);
		buttonAdd.setLayoutData(fd_buttonAdd);
		buttonAdd.setText("->");
		
		Button buttonDel = new Button(composite, SWT.NONE);
		buttonDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items =  tableSelected.getSelection();
				String[][] data = new String[items.length][2];
				for(int i=0;i<items.length;i++) {
					data[i][0] = items[i].getText(0) ;
					data[i][1] = items[i].getText(1) ;
				}
				boolean hasItem = false;
				TableItem item = null;
				TableItem[] noSelItems =  tableNoSelected.getItems();
				for(int i=0;i<data.length;i++) {
					for(TableItem aitem : noSelItems) {
						if(aitem.getText(0).equals(data[i][0])) {
							hasItem=true;
							break;
						}
					}
					if(!hasItem) {
						item = new TableItem(tableNoSelected, SWT.NONE);
						item.setText(data[i]);
						int idx = tableSelected.indexOf(items[i]);
						tableSelected.remove(idx);
					}
				}
			}
		});
		FormData fd_buttonDel = new FormData();
		fd_buttonDel.top = new FormAttachment(buttonAdd, 60);
		
		fd_buttonDel.right = new FormAttachment(buttonAdd, 0, SWT.RIGHT);
		fd_buttonDel.left = new FormAttachment(buttonAdd, 0, SWT.LEFT);
		
		TableColumn tableColumnNoID = new TableColumn(tableNoSelected, SWT.NONE);
		tableColumnNoID.setWidth(120);
		tableColumnNoID.setText("学号");
		
		TableColumn tableColumnNoName = new TableColumn(tableNoSelected, SWT.NONE);
		tableColumnNoName.setWidth(80);
		tableColumnNoName.setText("姓名");
		
		Menu menuNoSelectedStd = new Menu(tableNoSelected);
		tableNoSelected.setMenu(menuNoSelectedStd);
		
		MenuItem menuItemNoSelCut = new MenuItem(menuNoSelectedStd, SWT.NONE);
		menuItemNoSelCut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = tableNoSelected.getSelection();
				if(items.length==0)
					return;
				// 准备传输的数据 data
				String[] data = new String[items.length];
				for(int i=0;i<items.length;i++) {
					data[i] = items[i].getText(0) + "," + items[i].getText(1) ;
				}
				// 设置传输的数据类型
				Transfer[] types = new FileTransfer[]{FileTransfer.getInstance()};
				// 将选择的行数据存入剪切板
				Object[] objs = new Object[]{data}; // 非常重要！objs数组中的元素个数必须与types相同。
				clipboard.setContents(objs, types);
				// 从选课学生表删除剪切的行
				for(int i=0;i<items.length;i++) {
					int idx = tableNoSelected.indexOf(items[i]);
					tableNoSelected.remove(idx);
				}
			}
		});
		menuItemNoSelCut.setText("剪切");
		
		MenuItem menuItemNoSelPaste = new MenuItem(menuNoSelectedStd, SWT.NONE);
		menuItemNoSelPaste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] data = (String[])clipboard.getContents(FileTransfer.getInstance());
				if(data==null)
					return;
				String[] line = new String[2];
				TableItem[] items = tableNoSelected.getItems();
				boolean hasItem = false;
				TableItem item = null;
				for(int i=0;i<data.length;i++) {
					line=data[i].split(",");
					for(TableItem aitem : items) {
						if(aitem.getText(0).equals(line[0])) {
							hasItem=true;
							break;
						}
					}
					if(!hasItem) {
						item = new TableItem(tableNoSelected, SWT.NONE);
						item.setText(line);
					}
				}
			}
		});
		menuItemNoSelPaste.setText("粘贴");
		
		DropTarget dropTarget = new DropTarget(tableNoSelected, DND.DROP_MOVE);
		dropTarget.setTransfer(new Transfer[]{FileTransfer.getInstance()});
		
		DragSource dragSource_1 = new DragSource(tableNoSelected, DND.DROP_MOVE);
		dragSource_1.setTransfer(new Transfer[]{FileTransfer.getInstance()});
		
		dropTarget.addDropListener(new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				if (FileTransfer.getInstance().isSupportedType(event. currentDataType)) {
					String[] data = (String[])event.data;
					if(data==null)
						return;
					String[] line = new String[2];
					TableItem[] items = tableNoSelected.getItems();
					boolean hasItem = false;
					TableItem item = null;
					for(int i=0;i<data.length;i++) {
						line=data[i].split(",");
						for(TableItem aitem : items) {
							if(aitem.getText(0).equals(line[0])) {
								hasItem=true;
								break;
							}
						}
						if(!hasItem) {
							item = new TableItem(tableNoSelected, SWT.NONE);
							item.setText(line);
						}
					}
				}
			}
		});
		
		dragSource_1.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragStart(DragSourceEvent event) {
				TableItem[] items =  tableNoSelected.getSelection();
				// 没有选择的学生数据行时不允许拖放
				if(items.length>0)				
					event.doit = true ;
				else
					event.doit = false;
			}
			@Override
			public void dragSetData(DragSourceEvent event) {
				TableItem[] items =  tableNoSelected.getSelection();
				if(items.length==0)
					return;
				// 准备传输的数据 data
				String[] data = new String[items.length];
				for(int i=0;i<items.length;i++) {
					data[i] = items[i].getText(0) + "," + items[i].getText(1) ;
				}
				// 设置传输的数据 data
				if(FileTransfer.getInstance().isSupportedType(event.dataType))
					event.data = data ;
				// 从选课学生表删除拖放的行
				for(int i=0;i<items.length;i++) {
					int idx = tableNoSelected.indexOf(items[i]);
					tableNoSelected.remove(idx);
				}	
			}
		});
		
		TableColumn tableColumnSelID = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelID.setWidth(120);
		tableColumnSelID.setText("学号");
		
		TableColumn tableColumnSelName = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelName.setWidth(80);
		tableColumnSelName.setText("姓名");
		
		Menu menuSelectedStd = new Menu(tableSelected);
		tableSelected.setMenu(menuSelectedStd);
		
		MenuItem menuItemSelCut = new MenuItem(menuSelectedStd, SWT.NONE);
		menuItemSelCut.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] items = tableSelected.getSelection();
				if(items.length==0)
					return;
				// 准备传输的数据 data
				String[] data = new String[items.length];
				for(int i=0;i<items.length;i++) {
					data[i] = items[i].getText(0) + "," + items[i].getText(1) ;
				}
				// 设置传输的数据类型
				Transfer[] types = new FileTransfer[]{FileTransfer.getInstance()};
				// 将选择的行数据存入剪切板
				Object[] objs = new Object[]{data}; // 非常重要！objs数组中的元素个数必须与types相同。
				clipboard.setContents(objs, types);
				// 从选课学生表删除剪切的行
//				int[] indices = tableSelected.getSelectionIndices();
				for(int i=0;i<items.length;i++) {
					int idx = tableSelected.indexOf(items[i]);
					tableSelected.remove(idx);
				}
			}
		});
		menuItemSelCut.setText("剪切");
		
		MenuItem menuItemSelPaste = new MenuItem(menuSelectedStd, SWT.NONE);
		menuItemSelPaste.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] data = (String[])clipboard.getContents(FileTransfer.getInstance());
				if(data==null)
					return;
				String[] line = new String[2];
				TableItem[] items = tableSelected.getItems();
				boolean hasItem = false;
				TableItem item = null;
				for(int i=0;i<data.length;i++) {
					line=data[i].split(",");
					for(TableItem aitem : items) {
						if(aitem.getText(0).equals(line[0])) {
							hasItem=true;
							break;
						}
					}
					if(!hasItem) {
						item = new TableItem(tableSelected, SWT.NONE);
						item.setText(line);
					}
				}
			}
		});
		menuItemSelPaste.setText("粘贴");
		
		DragSource dragSource = new DragSource(tableSelected, DND.DROP_MOVE);
		dragSource.setTransfer(new Transfer[]{FileTransfer.getInstance()});
		
		DropTarget dropTarget_1 = new DropTarget(tableSelected, DND.DROP_MOVE);
		dropTarget_1.setTransfer(new Transfer[]{FileTransfer.getInstance()});
		
		dragSource.addDragListener(new DragSourceAdapter() {
			@Override
			public void dragSetData(DragSourceEvent event) {
				TableItem[] items =  tableSelected.getSelection();
				if(items.length==0)
					return;
				// 准备传输的数据 data
				String[] data = new String[items.length];
				for(int i=0;i<items.length;i++) {
					data[i] = items[i].getText(0) + "," + items[i].getText(1) ;
				}
				// 设置传输的数据 data
				if(FileTransfer.getInstance().isSupportedType(event.dataType))
					event.data = data ;
				// 从选课学生表删除拖放的行
				for(int i=0;i<items.length;i++) {
					int idx = tableSelected.indexOf(items[i]);
					tableSelected.remove(idx);
				}				
			}
			@Override
			public void dragStart(DragSourceEvent event) {
				TableItem[] items =  tableSelected.getSelection();
				// 没有选择的学生数据行时不允许拖放
				if(items.length>0)				
					event.doit = true ;
				else
					event.doit = false;
			}
		});

		dropTarget_1.addDropListener(new DropTargetAdapter() {
			@Override
			public void drop(DropTargetEvent event) {
				if (FileTransfer.getInstance().isSupportedType(event. currentDataType)) {
					String[] data = (String[])event.data;
					if(data==null)
						return;
					String[] line = new String[2];
					TableItem[] items = tableSelected.getItems();
					boolean hasItem = false;
					TableItem item = null;
					for(int i=0;i<data.length;i++) {
						line=data[i].split(",");
						for(TableItem aitem : items) {
							if(aitem.getText(0).equals(line[0])) {
								hasItem=true;
								break;
							}
						}
						if(!hasItem) {
							item = new TableItem(tableSelected, SWT.NONE);
							item.setText(line);
						}
					}
				}
			}
		});
		
		buttonDel.setLayoutData(fd_buttonDel);
		buttonDel.setText("<-");
		
		Button buttonOK = new Button(composite, SWT.NONE);
		buttonOK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				dgc_courseSave();
				tchCourseSave();
				stdCourseSave();
			}
		});
		FormData fd_buttonOK = new FormData();
		buttonOK.setLayoutData(fd_buttonOK);
		buttonOK.setText("保存");
		
		Button buttonQuit = new Button(composite, SWT.NONE);
		buttonQuit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		fd_buttonOK.top = new FormAttachment(buttonQuit, 0, SWT.TOP);
		fd_buttonOK.right = new FormAttachment(buttonQuit, -52);
		FormData fd_buttonQuit = new FormData();
		fd_buttonQuit.bottom = new FormAttachment(100, -28);
		fd_buttonQuit.right = new FormAttachment(100, -76);
		buttonQuit.setLayoutData(fd_buttonQuit);
		buttonQuit.setText("退出");
		
		comboViewerName = new ComboViewer(composite, SWT.NONE);
		Combo comboName = comboViewerName.getCombo();
		//Combo comboName = new Combo(composite, SWT.NONE);
		FormData fd_comboName = new FormData();
		fd_comboName.right = new FormAttachment(comboDept, 135, SWT.RIGHT);
		fd_comboName.top = new FormAttachment(comboDept, 0, SWT.TOP);
		fd_comboName.left = new FormAttachment(comboDept, 19);
		comboViewerDept.setLabelProvider(new ViewerLabelProvider_1());
		comboViewerDept.setContentProvider(new ArrayContentProvider());
		comboViewerDept.setInput(getDepartments());
		comboName.setLayoutData(fd_comboName);
		comboViewerName.setLabelProvider(new ViewerLabelProvider_2());
		comboViewerName.setContentProvider(new ArrayContentProvider());
		comboViewerDept.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				StructuredSelection ss = (StructuredSelection) arg0.getSelection();
				Department dept = (Department) ss.getFirstElement();
				//dept.setCoursesListFromDB();
				comboViewerName.setInput(getTchList(dept));
				comboViewerName.refresh();
			}
		});
		
		sashForm.setWeights(new int[] {1, 2});

	}
	
	public ArrayList<String[]> getStdList(GradeClass aclass) {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
		String sql = "select id,name from student where departmentID=" + aclass.getDepartmentId() +
				" and grade=" + aclass.getGrade() +" and class=" + aclass.getcClass();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				scoreList.add(new String[]{rs.getInt(1) + "",rs.getString(2)});
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
/*
		try {
			FileReader fr = new FileReader("stdcourse.csv");
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			String[] strArr;
			while(str!=null) {
				strArr = str.split(",");
				scoreList.add(strArr);
				str = br.readLine();
			}
			if(br!=null)
				br.close();
			if(fr!=null)
				fr.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} 
*/		
		return scoreList;
	}
	
	ArrayList<Teacher> getTchList(Department dept) {
		ArrayList<Teacher> tchList = new ArrayList<Teacher>();
		String sql = "select * from teacher where departmentID="+dept.getId();
		ResultSet rs = InitDB.getInitDB().getRs(sql);
		try {
			while(rs.next()) {
				Teacher teacher = new Teacher(rs.getInt(1),rs.getString(2),rs.getString(3),
						rs.getInt(4),dept,rs.getString(6),rs.getString(7),rs.getString(8)) ;
				tchList.add(teacher);
			}
			if(rs!=null)
				rs.close();
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return tchList;
	}

	static Department[] getDepartments() {
		String sql = "select * from department";
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		ArrayList<Department> depts = new ArrayList<Department>();
		try {
			while(rs.next()) {
				depts.add(new Department(rs.getInt(1),rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return depts.toArray(new Department[depts.size()]);
	}
	
	void dgc_courseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		ClassTreeNode classNode = (ClassTreeNode) ((CourseTypeTreeNode)courseNode.getParent()).getParent();
		GradeTreeNode gradeNode = (GradeTreeNode) classNode.getParent();
		DepartmentTreeNode deptNode = (DepartmentTreeNode) gradeNode.getParent();
		int courseID = ((Course)courseNode.getValue()).getId();
		int deptID = ((Department)deptNode.getValue()).getId();
		int grade = ((GradeClass)gradeNode.getValue()).getGrade();
		int aclass = ((GradeClass)classNode.getValue()).getcClass();
		int dgcID ;
		String sql="select id from department_grade_class where departmentID=" + deptID +
				" and grade=" +grade +" and class=" +aclass;
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		try {
			if(rs.next()) {
				dgcID = rs.getInt(1);
				sql = "select * from dgc_course where dgcID=" + dgcID +
						" and courseID=" + courseID;
				rs = db.getRs(sql);
				if(!rs.next()) {
					sql = "insert into dgc_course (dgcID,courseID) values(" + dgcID + "," + courseID +")" ;
					db.getStmt().executeUpdate(sql);
				}
			}
			
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	void tchCourseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		ClassTreeNode classNode = (ClassTreeNode) ((CourseTypeTreeNode)courseNode.getParent()).getParent();
		GradeTreeNode gradeNode = (GradeTreeNode) classNode.getParent();
		DepartmentTreeNode deptNode = (DepartmentTreeNode) gradeNode.getParent();
		int courseID = ((Course)courseNode.getValue()).getId();
		int deptID = ((Department)deptNode.getValue()).getId();
		int grade = ((GradeClass)gradeNode.getValue()).getGrade();
		int aclass = ((GradeClass)classNode.getValue()).getcClass();
		int dgcID ;
		
		ss = (StructuredSelection) comboViewerName.getStructuredSelection();
		Teacher teacher = (Teacher) ss.getFirstElement();		
		int teacherID = teacher.getId();
		
		String sql="select id from teacher_course where departmentID=" + deptID +
				" and grade=" +grade +" and class=" +aclass + " and courseID=" + courseID;
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		try {
			if(!rs.next()) {
				sql = "insert into teacher_course (teacherID,courseID,departmentID,grade,class) values(" + 
						teacherID + "," + courseID + "," + deptID + "," + grade + "," + aclass + ")" ;
				db.getStmt().executeUpdate(sql);	
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	void stdCourseSave() {
		StructuredSelection ss = (StructuredSelection) treeViewer.getStructuredSelection();
		CourseTreeNode courseNode = (CourseTreeNode) ss.getFirstElement();
		int courseID = ((Course)courseNode.getValue()).getId();
		
		TableItem[] items = tableSelected.getItems();
		long stdID;

		for(int i=0; i<items.length; i++) {
			stdID = Long.parseLong(items[i].getText(0));
			String sql="select * from student_course where studentID=" + stdID + " and courseID=" + courseID;
			InitDB db = InitDB.getInitDB();
			ResultSet rs = db.getRs(sql);
			try {
				if(!rs.next()) {
					Date date  = new Date();
					Timestamp tt = new Timestamp(date.getTime());					
					sql = "insert into student_course (studentID,courseID,updatetime) values(" +
							stdID + "," + courseID + ",'" + tt + "')" ;
					db.getStmt().executeUpdate(sql);	
				}
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}		
		}
	}
	
}
