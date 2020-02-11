package book.stdscore.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import org.eclipse.jface.wizard.Wizard;
import book.stdscore.data.Course;
import book.stdscore.data.Department;

public class CourseSetWizard extends Wizard {

	private DepartmentWizardPage departmentWizardPage;
	private GradeClassWizardPage gradeClassWizardPage;
	private CourseWizardPage courseWizardPage;

	public CourseSetWizard() {
		setWindowTitle("课程设置");
	}

	@Override
	public void addPages() {
		departmentWizardPage = new DepartmentWizardPage();
		addPage(departmentWizardPage);
		gradeClassWizardPage = new GradeClassWizardPage();
		addPage(gradeClassWizardPage);
		courseWizardPage = new CourseWizardPage();
		addPage(courseWizardPage);
	}

	@Override
	public boolean performFinish() {
		boolean finish = false;
		Department currDept = departmentWizardPage.getCurrDept();
		int grade = gradeClassWizardPage.getGrade();
		int aclass = gradeClassWizardPage.getAclass();

		// 保存课程设置表
		String allStr = "";
		String lineStr = "";
		try {
			File file = new File("deptGradeClassCourse.txt");
			if (file.exists()) {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				lineStr = br.readLine();
				while (lineStr != null && !lineStr.equals("")) {
					allStr += lineStr + "\r\n";
					lineStr = br.readLine();
				}
				if (br != null)
					br.close();
				if (fr != null)
					fr.close();
			}
			String[] items = courseWizardPage.getListHasCourse().getItems();
			String dga = currDept.getName() + "," + grade + "," + aclass + ",";
			for (int i = 0; i < items.length; i++) {
				allStr += dga + items[i].split("`")[0] + "," + items[i].split("`")[1] + "\r\n";
			}
			FileWriter fw = new FileWriter(file);
			fw.write(allStr);
			if (fw != null)
				fw.close();

			// 更新专业列表文件，以便本专业采用新课程列表
			saveCourseList(currDept, items);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		finish = true;
		return finish;
	}

	public void saveCourseList(Department currDept, String[] courses) {
		LinkedList<Department> deptList = null;
		if (currDept == null)
			return;

		// 设置当前专业新课程列表
		LinkedList<Course> coursesList = new LinkedList<Course>();
		for (int i = 0; i < courses.length; i++) {
			coursesList.add(new Course(courses[i].split("`")[1], courses[i].split("`")[0]));
		}
		currDept.setCoursesList(coursesList);
		
		// 更新专业列表，以便使当前专业采用新课程列表
		File deptFile = new File("deptList.obj");
		try {
			if (deptFile.exists()) {
				FileInputStream fis = new FileInputStream(deptFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				deptList = (LinkedList<Department>) ois.readObject();
				if (ois != null)
					ois.close();
				if (fis != null)
					fis.close();
			} else {
				deptList = new LinkedList<Department>();
				deptList.add(currDept);
			}
			for (int i = 0; i < deptList.size(); i++) {
				if (deptList.get(i).getName().equals(currDept.getName())) {
					deptList.set(i, currDept);
					break;
				}
			}
			FileOutputStream fos = new FileOutputStream(deptFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(deptList);
			if (oos != null)
				oos.close();
			if (fos != null)
				fos.close();
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
