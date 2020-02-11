package book.stdscore.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;

public class RegisterTabFolder {
	private static class ViewerLabelProvider_2 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return (String)element;
		}
	}
	private static class ContentProvider_2 implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof String[])
				return (String[])inputElement;
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private static class ViewerLabelProvider_1 extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			//return super.getText(element);
			return (String)element;
		}
	}
	private static class ContentProvider_1 implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			if(inputElement instanceof String[])
				return (String[])inputElement;
			return new Object[0];
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}
	private static class ViewerLabelProvider extends LabelProvider {
		public Image getImage(Object element) {
			return super.getImage(element);
		}
		public String getText(Object element) {
			return ((Department)element).getName();
		}
	}
	private static class ContentProvider implements IStructuredContentProvider {
		public Object[] getElements(Object inputElement) {
			//return new Object[0];
			return getDepartments();
		}
		public void dispose() {
		}
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	protected Shell shell;
	private Text textStdID;
	private Text textStdName;
	private Text textStdItr;
	private Text textTchID;
	private Text textTchName;
	private Text textTchAge;
	private Text textTchAddr;
	private Text textTchIntro;
	private ImageRegistry imageRegistry = null;
	private ComboViewer comboDepartmentViewer;
	private ComboViewer comboGradeViewer;
	private ComboViewer comboClassViewer;
	private ComboViewer comboTchDeptViewer;
	private TabFolder tabFolder;
	private TabItem tabItemStd;
	private TabItem tabItemTch;
	private Label lblStdPic;
	private Button buttonStdSave;
	
	public Shell getShell() {
		return shell;
	}

	public RegisterTabFolder() {
		super();
		// TODO 自动生成的构造函数存根
		// open();
	}
	
	public TabFolder getTabFolder() {
		return tabFolder;
	}

	public TabItem getTabItemStd() {
		return tabItemStd;
	}

	public TabItem getTabItemTch() {
		return tabItemTch;
	}

	public Text getTextStdID() {
		return textStdID;
	}

	public Text getTextStdName() {
		return textStdName;
	}

	public Text getTextStdItr() {
		return textStdItr;
	}

	public ComboViewer getComboDepartmentViewer() {
		return comboDepartmentViewer;
	}

	public ComboViewer getComboGradeViewer() {
		return comboGradeViewer;
	}

	public ComboViewer getComboClassViewer() {
		return comboClassViewer;
	}

	public Label getLblStdPic() {
		return lblStdPic;
	}

	public Button getButtonStdSave() {
		return buttonStdSave;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			RegisterTabFolder window = new RegisterTabFolder();
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
		imageRegistry = new ImageRegistry(display) ;
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
		shell.setMinimumSize(new Point(650, 600));
		shell.setSize(450, 300);
		shell.setText("用户注册");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		tabFolder = new TabFolder(shell, SWT.NONE);
		
		tabItemStd = new TabItem(tabFolder, SWT.NONE);
		tabItemStd.setText("学生注册");
		
		Composite compositeStd = new Composite(tabFolder, SWT.NONE);
		tabItemStd.setControl(compositeStd);
		GridLayout gl_compositeStd = new GridLayout(7, false);
		gl_compositeStd.verticalSpacing = 15;
		gl_compositeStd.horizontalSpacing = 15;
		compositeStd.setLayout(gl_compositeStd);
		
		Label lblSA = new Label(compositeStd, SWT.NONE);
		lblSA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdID = new Label(compositeStd, SWT.NONE);
		lblStdID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdID.setText("学号");
		
		textStdID = new Text(compositeStd, SWT.BORDER);
		GridData gd_textStdID = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_textStdID.widthHint = 99;
		textStdID.setLayoutData(gd_textStdID);
		
		Label lblSB = new Label(compositeStd, SWT.NONE);
		GridData gd_lblSB = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_lblSB.widthHint = 50;
		lblSB.setLayoutData(gd_lblSB);
		
		Label lblStdPicL = new Label(compositeStd, SWT.NONE);
		lblStdPicL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdPicL.setText("照片");
		
		lblStdPic = new Label(compositeStd, SWT.BORDER | SWT.SHADOW_NONE);
		GridData gd_lblStdPic = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
		gd_lblStdPic.widthHint = 100;
		lblStdPic.setLayoutData(gd_lblStdPic);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);

		textStdID.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String str = textStdID.getText().trim();
				if((str!=null||!"".equals(str)) && str.matches("[0-9]++")) {
					try { // 此语句块使用了图像描述符、图像注册表，创建图像
						ImageDescriptor imgdsc = ImageDescriptor.createFromURL(new URL("file:picStd/"+str+".jpg"));
						imageRegistry.put("std"+str, imgdsc);
						ImageDescriptor imgdescr = imageRegistry.getDescriptor("std"+str);
						if(imgdescr!=null)
							lblStdPic.setImage(imgdescr.createImage());
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}
				
			}
		});
		
		Label lblStdName = new Label(compositeStd, SWT.NONE);
		lblStdName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdName.setText("姓名");
		
		textStdName = new Text(compositeStd, SWT.BORDER);
		textStdName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeStd, SWT.NONE);
		
		Button buttonStdPicUp = new Button(compositeStd, SWT.ARROW);
		buttonStdPicUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fDialog = new FileDialog(shell,SWT.SAVE); 
				fDialog.setFilterExtensions(new String[]{"*.jpg","*.JPG"});
				fDialog.setFilterNames(new String[]{"jpeg文件(*.jpg)","JPEG文件(*.JPG)"});
				String picName = fDialog.open();
				String saveName = textStdID.getText().trim();
				if(picName!=null && !"".equals(picName) && saveName!=null && !"".equals(saveName)) {
					try {
						FileInputStream fis = new FileInputStream(picName) ;
						FileOutputStream fos = new FileOutputStream("picStd/"+saveName+".jpg");
						int b =fis.read() ;
						while(b!=-1) {
							fos.write(b);
							b =fis.read();
						}
						fos.close();
						fis.close();
						lblStdPic.setImage(imageRegistry.getDescriptor("std"+saveName).createImage());
						shell.layout();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
					}
				}				
			}
		});
		buttonStdPicUp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		buttonStdPicUp.setText("New Button");
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdDept = new Label(compositeStd, SWT.NONE);
		lblStdDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdDept.setText("专业");
		
		comboDepartmentViewer = new ComboViewer(compositeStd, SWT.READ_ONLY);
		comboDepartmentViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				StructuredSelection ss = (StructuredSelection)arg0.getSelection();
				Department dept = (Department) ss.getFirstElement();
				getGrades(dept);
			}
		});
		Combo comboDepartment = comboDepartmentViewer.getCombo();
		comboDepartment.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboDepartmentViewer.setLabelProvider(new ViewerLabelProvider());
		comboDepartmentViewer.setContentProvider(new ContentProvider());
		comboDepartmentViewer.setInput(getDepartments());
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdGrade = new Label(compositeStd, SWT.NONE);
		lblStdGrade.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdGrade.setText("年级");
		
		comboGradeViewer = new ComboViewer(compositeStd, SWT.READ_ONLY);
		comboGradeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent arg0) {
				Department dept = (Department) comboDepartmentViewer.getStructuredSelection().getFirstElement();
				StructuredSelection ss = (StructuredSelection)arg0.getSelection();
				String grade = (String) ss.getFirstElement();
				getClasses(dept,Integer.parseInt(grade));
			}
		});
		Combo comboGrade = comboGradeViewer.getCombo();
		comboGrade.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboGradeViewer.setLabelProvider(new ViewerLabelProvider_1());
		comboGradeViewer.setContentProvider(new ContentProvider_1());
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdItr = new Label(compositeStd, SWT.NONE);
		lblStdItr.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdItr.setText("学习兴趣");
		
		textStdItr = new Text(compositeStd, SWT.BORDER);
		textStdItr.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 2));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblStdClass = new Label(compositeStd, SWT.NONE);
		lblStdClass.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStdClass.setText("班级");
		
		comboClassViewer = new ComboViewer(compositeStd, SWT.READ_ONLY);
		Combo comboClass = comboClassViewer.getCombo();
		comboClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboClassViewer.setLabelProvider(new ViewerLabelProvider_2());
		comboClassViewer.setContentProvider(new ContentProvider_2());
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblSC = new Label(compositeStd, SWT.NONE);
		lblSC.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		buttonStdSave = new Button(compositeStd, SWT.NONE);
		buttonStdSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String id = textStdID.getText().trim();
				if(id==null || "".equals(id))
					return;
				String sql = "select * from student where id=" + Integer.parseInt(id) ;
				InitDB db = InitDB.getInitDB();
				ResultSet rs = db.getRs(sql);
				try {
					if(!rs.next()) {
						String name = textStdName.getText().trim();
						Department dept = (Department) comboDepartmentViewer.getStructuredSelection().getFirstElement();
						String grade = (String) comboGradeViewer.getStructuredSelection().getFirstElement();
						String aclass = (String) comboClassViewer.getStructuredSelection().getFirstElement();
						String pic = "picStd/"+id+".jpg";
						String interested = textStdItr.getText().trim();
						sql = "insert into student values(" + Integer.parseInt(id) + ",'" + name+"'," + dept.getId() + 
								"," + Integer.parseInt(grade) + "," + Integer.parseInt(aclass) + ",'" + pic + "','" + interested +"')"; 
						db.getStmt().executeUpdate(sql);
						sql = "select * from users where name='" + id + "' and job=0" ;
						rs = db.getRs(sql);
						if(!rs.next()) {
							sql = "insert into users values('" + id + "','123',0)" ;
							db.getStmt().executeUpdate(sql);
						}
						textStdID.setText("");
						textStdName.setText("");
						textStdItr.setText("");
						textStdID.setFocus();
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		buttonStdSave.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		buttonStdSave.setText("保存");
		new Label(compositeStd, SWT.NONE);
		
		Button buttonStdClose = new Button(compositeStd, SWT.NONE);
		buttonStdClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		buttonStdClose.setText("关闭");
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		new Label(compositeStd, SWT.NONE);
		
		Label lblSD = new Label(compositeStd, SWT.NONE);
		lblSD.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		
		tabItemTch = new TabItem(tabFolder, SWT.NONE);
		tabItemTch.setText("教师注册");
		
		Composite compositeTch = new Composite(tabFolder, SWT.NONE);
		tabItemTch.setControl(compositeTch);
		GridLayout gl_compositeTch = new GridLayout(7, false);
		gl_compositeTch.verticalSpacing = 15;
		gl_compositeTch.horizontalSpacing = 15;
		compositeTch.setLayout(gl_compositeTch);
		
		Label lblTA = new Label(compositeTch, SWT.NONE);
		lblTA.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchID = new Label(compositeTch, SWT.NONE);
		lblTchID.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchID.setText("工号");
		
		textTchID = new Text(compositeTch, SWT.BORDER);
		textTchID.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchPicL = new Label(compositeTch, SWT.NONE);
		lblTchPicL.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchPicL.setText("照片");
		
		Label lblTchPic = new Label(compositeTch, SWT.BORDER);
		GridData gd_lblTchPic = new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3);
		gd_lblTchPic.widthHint = 100;
		lblTchPic.setLayoutData(gd_lblTchPic);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchName = new Label(compositeTch, SWT.NONE);
		lblTchName.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchName.setText("姓名");
		
		textTchName = new Text(compositeTch, SWT.BORDER);
		textTchName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		
		Button buttonTchPicUp = new Button(compositeTch, SWT.ARROW);
		buttonTchPicUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fDialog = new FileDialog(shell,SWT.SAVE); 
				fDialog.setFilterExtensions(new String[]{"*.jpg","*.JPG"});
				fDialog.setFilterNames(new String[]{"jpeg文件(*.jpg)","JPEG文件(*.JPG)"});
				String picName = fDialog.open();
				String saveName = textTchID.getText().trim();
				if(picName!=null && !"".equals(picName) && saveName!=null && !"".equals(saveName)) {
					try {
						FileInputStream fis = new FileInputStream(picName) ;
						FileOutputStream fos = new FileOutputStream("picTch/"+saveName+".jpg");
						int b =fis.read() ;
						while(b!=-1) {
							fos.write(b);
							b =fis.read();
						}
						fos.close();
						fis.close();
						lblTchPic.setImage(imageRegistry.getDescriptor("tch"+saveName).createImage());
						shell.layout();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e2) {
					}
				}					
			}
		});
		buttonTchPicUp.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false, 1, 1));
		buttonTchPicUp.setText("New Button");
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchSex = new Label(compositeTch, SWT.NONE);
		lblTchSex.setText("性别");
		
		Button buttonTchMale = new Button(compositeTch, SWT.RADIO);
		buttonTchMale.setText("男");
		
		Button buttonTchFemale = new Button(compositeTch, SWT.RADIO);
		buttonTchFemale.setText("女");
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchAge = new Label(compositeTch, SWT.NONE);
		lblTchAge.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchAge.setText("年龄");
		
		textTchAge = new Text(compositeTch, SWT.BORDER);
		textTchAge.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchIntro = new Label(compositeTch, SWT.NONE);
		lblTchIntro.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchIntro.setText("简介");
		
		textTchIntro = new Text(compositeTch, SWT.BORDER);
		textTchIntro.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 3));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchDept = new Label(compositeTch, SWT.NONE);
		lblTchDept.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTchDept.setText("部门");
		
		comboTchDeptViewer = new ComboViewer(compositeTch, SWT.READ_ONLY);
		Combo comboTchDept = comboTchDeptViewer.getCombo();
		comboTchDept.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTchDeptViewer.setLabelProvider(new ViewerLabelProvider());
		comboTchDeptViewer.setContentProvider(new ContentProvider());
		comboTchDeptViewer.setInput(getDepartments());
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTchAddr = new Label(compositeTch, SWT.NONE);
		lblTchAddr.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblTchAddr.setText("住址");
		
		textTchAddr = new Text(compositeTch, SWT.BORDER);
		GridData gd_textTchAddr = new GridData(SWT.FILL, SWT.TOP, false, false, 1, 1);
		gd_textTchAddr.heightHint = 80;
		textTchAddr.setLayoutData(gd_textTchAddr);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		textTchID.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				String str = textTchID.getText().trim();
				if((str!=null||!"".equals(str)) && str.matches("[0-9]++")) {
					try { // 此语句块使用了图像描述符、图像注册表，创建图像
						ImageDescriptor imgdsc = ImageDescriptor.createFromURL(new URL("file:picTch/"+str+".jpg"));
						imageRegistry.put("tch"+str, imgdsc);
						ImageDescriptor imgdescr = imageRegistry.getDescriptor("tch"+str);
						if(imgdescr!=null)
							lblTchPic.setImage(imgdescr.createImage());
					} catch (MalformedURLException e1) {
						e1.printStackTrace();
					}
				}

			}
		});
		
		Label lblTB = new Label(compositeTch, SWT.NONE);
		lblTB.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Button buttonTchSave = new Button(compositeTch, SWT.NONE);
		buttonTchSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String id = textTchID.getText().trim();
				if(id==null || "".equals(id))
					return;
				String sql = "select * from teacher where id=" + Integer.parseInt(id) ;
				InitDB db = InitDB.getInitDB();
				ResultSet rs = db.getRs(sql);
				try {
					if(!rs.next()) {
						String name = textTchName.getText().trim();
						String sex = buttonTchFemale.getSelection()? "女" : "男" ;
						int age = Integer.parseInt(textTchAge.getText().trim());
						Department dept = (Department) comboTchDeptViewer.getStructuredSelection().getFirstElement();
						String adress = textTchAddr.getText().trim();
						String pic = "picTch/"+id+".jpg";
						String intro = textTchIntro.getText().trim();
						
						sql = "insert into teacher values(" + Integer.parseInt(id) + ",'" + name+"','" + sex + "'," + 
								age +"," + dept.getId() + ",'" + adress + "','" + pic + "','" + intro +"')"; 
						db.getStmt().executeUpdate(sql);
						sql = "select * from users where name='" + id + "' and job=1" ;
						rs = db.getRs(sql);
						if(!rs.next()) {
							sql = "insert into users values('" + id + "','456',1)" ;
							db.getStmt().executeUpdate(sql);
						}
						textTchID.setText("");
						textTchName.setText("");
						textTchAge.setText("");
						textTchAddr.setText("");
						textTchIntro.setText("");
						textTchID.setFocus();
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}				
			}
		});
		buttonTchSave.setText("保存");
		new Label(compositeTch, SWT.NONE);
		
		Button buttonTchClose = new Button(compositeTch, SWT.NONE);
		buttonTchClose.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.dispose();
			}
		});
		buttonTchClose.setText("关闭");
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		new Label(compositeTch, SWT.NONE);
		
		Label lblTC = new Label(compositeTch, SWT.NONE);
		lblTC.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, true, 1, 1));

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
	
	String[] getGrades(Department dept) {
		String sql = "select grade from department_grade_class where departmentID=" +
				dept.getId() + " group by grade"; 
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		ArrayList<String> grades = new ArrayList<String>();
		try {
			while(rs.next()) {
				grades.add(rs.getInt(1)+"");
			}
			comboGradeViewer.setInput(grades.toArray(new String[grades.size()]));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return grades.toArray(new String[grades.size()]);
	}
	
	String[] getClasses(Department dept,int grade) {
		String sql = "select class from department_grade_class where departmentID=" +
				dept.getId() + " and grade=" + grade + " group by class";
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		ArrayList<String> classes = new ArrayList<String>();
		try {
			while(rs.next()) {
				classes.add(rs.getInt(1)+"");
			}
			comboClassViewer.setInput(classes.toArray(new String[classes.size()]));
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		return classes.toArray(new String[classes.size()]);
	}
	
}
