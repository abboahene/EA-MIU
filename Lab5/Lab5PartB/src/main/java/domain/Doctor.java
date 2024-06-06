package domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;


@Entity
public class Doctor {

	@Id
	@GeneratedValue
	private int id;
	private String doctorType;

	private String firstName;

	private String lastName;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	Collection<Appointment> appointments = new ArrayList<>();
	public Doctor() {
	}

	public Doctor(String doctorType, String firstName, String lastName) {
		this.doctorType = doctorType;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Collection<Appointment> getAppointments() {
		return appointments;
	}

	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}

	@Override
	public String toString() {
		return "Doctor{" +
				"id=" + id +
				", doctorType='" + doctorType + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}
