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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import org.eclipse.jface.wizard.Wizard;
import book.stdscore.data.Course;
import book.stdscore.data.Department;
import book.stdscore.data.InitDB;

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
		String[] items = courseWizardPage.getListHasCourse().getItems();
		String sql = "select id from department_grade_class where departmentID=" + 
				currDept.getId() + " and grade=" + grade +" and class="+aclass;
		InitDB db = InitDB.getInitDB();
		ResultSet rs = db.getRs(sql);
		try {
			if(rs.next()) {
				int dgcID = rs.getInt(1);
				for (int i = 0; i < items.length; i++) {
					Course course = Course.getFromDB(items[i].split(":")[1], items[i].split(":")[0]);

					if(course==null) { // 添加新的课程
						course = new Course(items[i].split(":")[1], items[i].split(":")[0]);
						int cid = course.insertToDB();
						course.setId(cid);
					}
					// 登记班级排课
					sql = "select * from dgc_course where dgcID=" + dgcID + " and courseID="+course.getId();
					rs = db.getRs(sql);
					if(!rs.next()) {
						sql = "insert into dgc_course (dgcId,courseID) values (" + dgcID + "," + course.getId() +")";
						db.getStmt().executeUpdate(sql);						
					}
					// 为专业添加新课
					sql = "select * from department_course where departmentID=" + currDept.getId() + " and courseID="+course.getId();
					rs = db.getRs(sql);
					if(!rs.next()) {
						sql = "insert into department_course (departmentID,courseID) values (" + currDept.getId() + "," + course.getId() +")";
						db.getStmt().executeUpdate(sql);						
					}
				}
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		//String dga = currDept.getName() + "," + grade + "," + aclass + ",";

/*		
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
*/
		finish = true;
		return finish;
	}
/*
	public void saveCourseList(Department currDept, String[] courses) {
		LinkedList<Department> deptList = null;
		if (currDept == null)
			return;

		// 设置当前专业新课程列表
		ArrayList<Course> coursesList = new ArrayList<Course>();
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
*/
}
