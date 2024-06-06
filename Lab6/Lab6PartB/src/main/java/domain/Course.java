package domain;

import jakarta.persistence.*;

@Entity
public class Course {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	public Course(){}

	public Course(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
