package domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Department {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToMany(mappedBy = "department", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Student> students = new ArrayList<>();

	public Department(){}

	public Department(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		return "Department{" +
				"id=" + id +
				", name='" + name + '\'' +
				", students=" + students +
				'}';
	}
}
