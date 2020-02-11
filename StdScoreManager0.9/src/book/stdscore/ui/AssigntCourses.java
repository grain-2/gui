package book.stdscore.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FormLayout;
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

public class AssigntCourses {

	protected Shell shell;
	private Table tableNoSelected;
	private Table tableSelected;
	private Clipboard clipboard;

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
		shell.setMinimumSize(new Point(800, 600));
		shell.setSize(450, 300);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		
		Tree tree = new Tree(sashForm, SWT.BORDER);
		
		TreeItem treeItem = new TreeItem(tree, 0);
		treeItem.setText("计算机科学与技术");
		
		TreeItem treeItem_3 = new TreeItem(treeItem, 0);
		treeItem_3.setText("2013级");
		treeItem_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		treeItem_3.setFont(SWTResourceManager.getFont("华文行楷", 9, SWT.NORMAL));
		treeItem_3.setBackground(SWTResourceManager.getColor(SWT.COLOR_CYAN));
		
		TreeItem treeItem_7 = new TreeItem(treeItem_3, 0);
		treeItem_7.setText(new String[] {});
		treeItem_7.setText("1班");
		treeItem_7.setGrayed(true);
		treeItem_7.setChecked(true);
		
		TreeItem trtmJava = new TreeItem(treeItem_7, SWT.NONE);
		trtmJava.setText("Java语言");
		
		TreeItem trtmJavaGui = new TreeItem(treeItem_7, SWT.NONE);
		trtmJavaGui.setText("Java GUI程序设计");
		treeItem_7.setExpanded(true);
		
		TreeItem treeItem_8 = new TreeItem(treeItem_3, 0);
		treeItem_8.setText(new String[] {"2班", "三校生"});
		
		TreeItem treeItem_11 = new TreeItem(treeItem_8, 0);
		treeItem_11.setText("Java语言");
		
		TreeItem trtmjavaGui = new TreeItem(treeItem_8, 0);
		trtmjavaGui.setText("可视化Java GUI程序设计");
		treeItem_8.setExpanded(true);
		treeItem_3.setExpanded(true);
		
		TreeItem treeItem_4 = new TreeItem(treeItem, 0);
		treeItem_4.setText("2014级");
		
		TreeItem treeItem_9 = new TreeItem(treeItem_4, 0);
		treeItem_9.setText(new String[] {});
		treeItem_9.setText("1班");
		treeItem_9.setGrayed(true);
		treeItem_9.setChecked(true);
		
		TreeItem treeItem_10 = new TreeItem(treeItem_4, 0);
		treeItem_10.setText(new String[] {"2班", "三校生"});
		treeItem_4.setExpanded(true);
		
		TreeItem treeItem_5 = new TreeItem(treeItem, 0);
		treeItem_5.setText("2015级");
		
		TreeItem treeItem_6 = new TreeItem(treeItem, 0);
		treeItem_6.setText("2016级");
		treeItem.setExpanded(true);
		
		TreeItem treeItem_1 = new TreeItem(tree, 0);
		treeItem_1.setText("软件工程");
		
		TreeItem treeItem_2 = new TreeItem(tree, 0);
		treeItem_2.setText("网络工程");
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FormLayout());
		
		Label label = new Label(composite, SWT.NONE);
		FormData fd_label = new FormData();
		fd_label.top = new FormAttachment(0, 47);
		fd_label.left = new FormAttachment(0, 149);
		label.setLayoutData(fd_label);
		label.setText("任课教师");
		
		Combo combo = new Combo(composite, SWT.NONE);
		FormData fd_combo = new FormData();
		fd_combo.bottom = new FormAttachment(label, 0, SWT.BOTTOM);
		fd_combo.left = new FormAttachment(label, 6);
		combo.setLayoutData(fd_combo);
		
		Label label_1 = new Label(composite, SWT.NONE);
		FormData fd_label_1 = new FormData();
		fd_label_1.left = new FormAttachment(0, 80);
		label_1.setLayoutData(fd_label_1);
		label_1.setText("未选学生");
		
		tableNoSelected = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		fd_label_1.bottom = new FormAttachment(100, -420);
		FormData fd_tableNoSelected = new FormData();
		fd_tableNoSelected.right = new FormAttachment(label, -10, SWT.RIGHT);
		fd_tableNoSelected.top = new FormAttachment(label_1, 6);
		fd_tableNoSelected.left = new FormAttachment(0, 35);
		fd_tableNoSelected.bottom = new FormAttachment(100, -94);
		tableNoSelected.setLayoutData(fd_tableNoSelected);
		tableNoSelected.setHeaderVisible(true);
		tableNoSelected.setLinesVisible(true);
		
		tableSelected = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		FormData fd_tableSelected = new FormData();
		fd_tableSelected.right = new FormAttachment(100, -32);
		fd_tableSelected.bottom = new FormAttachment(100, -94);
		fd_tableSelected.left = new FormAttachment(100, -201);
		tableSelected.setLayoutData(fd_tableSelected);
		tableSelected.setHeaderVisible(true);
		tableSelected.setLinesVisible(true);
		
		Label label_2 = new Label(composite, SWT.NONE);
		fd_tableSelected.top = new FormAttachment(label_2, 6);
		FormData fd_label_2 = new FormData();
		fd_label_2.top = new FormAttachment(0, 101);
		fd_label_2.right = new FormAttachment(100, -84);
		label_2.setLayoutData(fd_label_2);
		label_2.setText("已选学生");
		
		Button button = new Button(composite, SWT.NONE);
		FormData fd_button = new FormData();
		fd_button.left = new FormAttachment(combo, 12, SWT.LEFT);
		button.setLayoutData(fd_button);
		button.setText("->");
		
		Button button_1 = new Button(composite, SWT.NONE);
		fd_button.bottom = new FormAttachment(button_1, -51);
		fd_button.right = new FormAttachment(button_1, 0, SWT.RIGHT);
		FormData fd_button_1 = new FormData();
		fd_button_1.top = new FormAttachment(0, 314);
		fd_button_1.left = new FormAttachment(tableNoSelected, 28);
		
		TableColumn tableColumnNoID = new TableColumn(tableNoSelected, SWT.NONE);
		tableColumnNoID.setWidth(88);
		tableColumnNoID.setText("学号");
		
		TableColumn tableColumnNoName = new TableColumn(tableNoSelected, SWT.NONE);
		tableColumnNoName.setWidth(83);
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
		
		fd_button_1.right = new FormAttachment(tableSelected, -24);
		
		TableColumn tableColumnSelID = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelID.setWidth(80);
		tableColumnSelID.setText("学号");
		
		TableColumn tableColumnSelName = new TableColumn(tableSelected, SWT.NONE);
		tableColumnSelName.setWidth(84);
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

		ArrayList<String[]> scoreList = getScoreList();
		TableItem item;
		for(String[] strArr : scoreList) {
			item = new TableItem(tableSelected, SWT.NONE);
			item.setText(strArr);			
		}
		
		button_1.setLayoutData(fd_button_1);
		button_1.setText("<-");
		
		Button button_2 = new Button(composite, SWT.NONE);
		FormData fd_button_2 = new FormData();
		button_2.setLayoutData(fd_button_2);
		button_2.setText("分派");
		
		Button button_3 = new Button(composite, SWT.NONE);
		fd_button_2.top = new FormAttachment(button_3, 0, SWT.TOP);
		fd_button_2.right = new FormAttachment(button_3, -52);
		FormData fd_button_3 = new FormData();
		fd_button_3.bottom = new FormAttachment(100, -28);
		fd_button_3.right = new FormAttachment(100, -76);
		button_3.setLayoutData(fd_button_3);
		button_3.setText("退出");
		sashForm.setWeights(new int[] {1, 2});

	}
	
	public ArrayList getScoreList() {
		ArrayList<String[]> scoreList = new ArrayList<String[]>();
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
		return scoreList;
	}
}
