package app;

import java.util.List;

public class UniversityStudent {
	private String registerNumber;
	private double gpa;
	private char result;
	private List<Subject> subjects;
	public String getRegisterNumber() {
		return registerNumber;
	}
	public void setRegisterNumber(String registerNumber) {
		this.registerNumber = registerNumber;
	}
	public double getGpa() {
		return gpa;
	}
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	public char getResult() {
		return result;
	}
	public void setResult(char result) {
		this.result = result;
	}
	public List<Subject> getSubjects() {
		return subjects;
	}
	public void setSubjects(List<Subject> subjects) {
		this.subjects = subjects;
	}
}
