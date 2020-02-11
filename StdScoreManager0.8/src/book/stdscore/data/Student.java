package book.stdscore.data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Student {
	private int id;
	private String name;
	private ArrayList<Course> courseList;

	public Student(int id) {
		super();
		this.id = id;
	}

	public ArrayList<Course> getCourseList() {
		courseList = new ArrayList<Course>();
		try {
			FileReader fr = new FileReader(id + ".csv");
			BufferedReader br = new BufferedReader(fr);
			String str = br.readLine();
			String[] strArr;
			Course course;
			while(str!=null) {
				strArr = str.split(",");
				course = new Course(strArr[0],strArr[1]);
				course.setScore(Float.parseFloat(strArr[2]));
				courseList.add(course);				
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

		return courseList;
	}
	

}
