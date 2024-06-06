package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String name;

	private String studentNumber;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Grade> grades = new ArrayList<>();
	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Department department;

	public Student(){}
	public Student(String name, String studentNumber, Department department) {
		this.name = name;
		this.studentNumber = studentNumber;
		this.department = department;
		department.addStudent(this);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(String studentNumber) {
		this.studentNumber = studentNumber;
	}

	public Collection<Grade> getGrades() {
		return grades;
	}

	public void setGrades(Collection<Grade> grades) {
		this.grades = grades;
	}

	public void addGrade(Grade grade) {
		this.grades.add(grade);
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Student{" +
				"id=" + id +
				", name='" + name + '\'' +
				", studentNumber='" + studentNumber + '\'' +
				'}';
	}
}
