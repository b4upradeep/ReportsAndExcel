package app;

import java.util.List;

public class Course {
	private String courseName;
	
	private List<UniversityStudent> students;
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseId) {
		this.courseName = courseId;
	}

	public List<UniversityStudent> getStudents() {
		return students;
	}

	public void setStudents(List<UniversityStudent> students) {
		this.students = students;
	}

		
}
