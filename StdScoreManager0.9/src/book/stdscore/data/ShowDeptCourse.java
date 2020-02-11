package book.stdscore.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

public class ShowDeptCourse {

	private static LinkedList<Department> deptList;

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
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
			}
			int i ;
			LinkedList<Course> cl;
			for (i = 0; i < deptList.size(); i++) {
				System.out.println("name:"+deptList.get(i).getName());
				cl = deptList.get(i).getCoursesList();
				for(int j=0;j<cl.size();j++) {
					System.out.println("course name:"+cl.get(j).getName()+","+cl.get(j).getType());
				}
				System.out.println("----------------");
			}
		} catch (ClassNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

}
