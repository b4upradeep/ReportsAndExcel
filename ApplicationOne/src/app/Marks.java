package app;

public class Marks {
	private int internalMarks,externalMarks,totalMarks;
	private char grade;
	public int getExternalMarks() {
		return externalMarks;
	}
	public void setExternalMarks(int externalMarks) {
		this.externalMarks = externalMarks;
	}
	public int getTotalMarks() {
		return totalMarks;
	}
	public void setTotalMarks(int totalMarks) {
		this.totalMarks = totalMarks;
	}
	public int getInternalMarks() {
		return internalMarks;
	}
	public void setInternalMarks(int internalMarks) {
		this.internalMarks = internalMarks;
	}
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	
}
