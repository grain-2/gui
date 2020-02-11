package book.stdscore.data;

import java.io.Serializable;
import java.util.LinkedList;

public class Department implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4635709273756682488L;
	private String name;
	private LinkedList<Course> coursesList;

	public Department(String name) {
		super();
		this.name = name;
		coursesList = new LinkedList<Course>();
	}

	public Department(String name,LinkedList<Course> coursesList) {
		super();
		this.name = name;
		if(coursesList!=null)
			this.coursesList = coursesList;
		else
			this.coursesList = new LinkedList<Course>();
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LinkedList<Course> getCoursesList() {
		return coursesList;
	}
	
	public void setCoursesList(LinkedList<Course> coursesList) {
		this.coursesList = coursesList;
	}

	public void addCourse(Course course) {
		coursesList.add(course);
	}

}
