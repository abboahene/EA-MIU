package domain.a;

import jakarta.persistence.*;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private int id;
	private String employeeNumber;
	private String name;
	@ManyToOne
	private Department department;

	public Employee() {
	}
	public Employee(String employeeNumber, String name) {
		this.employeeNumber = employeeNumber;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id=" + id +
				", employeeNumber='" + employeeNumber + '\'' +
				", name='" + name + '\'' +
				'}';
	}
}


