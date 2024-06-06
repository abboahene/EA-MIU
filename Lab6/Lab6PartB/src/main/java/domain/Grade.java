package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Grade {

	@Id
	@GeneratedValue
	private int id;
	private String grade;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Course course;

	@ManyToMany(mappedBy = "grades")
	private Collection<Student> students = new ArrayList<>();

	public Grade(){}
	public Grade(String grade, Course course) {
		this.grade = grade;
		this.course = course;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}
	public void addStudent(Student student) {
		this.students.add(student);
	}


	@Override
	public String toString() {
		return "Grade{" +
				"id=" + id +
				", grade='" + grade + '\'' +
				'}';
	}
}
