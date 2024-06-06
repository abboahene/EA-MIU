package customers.domain;

import jakarta.annotation.Generated;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Collection;


@Document
public class Student {

	@Id
	private int studentNumber;
	private String name;
	private String phone;
	private Address address;
	private Collection<Grade> grades = new ArrayList<>();

	public Student(int studentNumber, String name, String phone, Address address) {
		this.studentNumber = studentNumber;
		this.name = name;
		this.phone = phone;
		this.address = address;
	}

	public int getStudentNumber() {
		return studentNumber;
	}

	public void setStudentNumber(int studentNumber) {
		this.studentNumber = studentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	@Override
	public String toString() {
		return "Student{" +
				", studentNumber=" + studentNumber +
				", name='" + name + '\'' +
				", phone='" + phone + '\'' +
				", address=" + (address != null ? address.toString() : "null") +
				", grades=" + (grades != null ? grades.toString() : "null") +
				'}';
	}
}