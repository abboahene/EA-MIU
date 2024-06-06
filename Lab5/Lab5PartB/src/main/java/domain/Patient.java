package domain;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Patient {

	@Id
	@GeneratedValue
	private int id;
	private String name;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@PrimaryKeyJoinColumn(name = "patientId")
	private Address address;

	@OneToMany(mappedBy = "patient", cascade = CascadeType.PERSIST)
	Collection<Appointment> appointments = new ArrayList<>();

	public Patient() {
	}

	public Patient(String name) {

		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
		address.setPatient(this);
	}

	public Collection<Appointment> getAppointments() {
		return appointments;
	}

	public void setAppointments(Collection<Appointment> appointments) {
		this.appointments = appointments;
	}
	public void addAppointment(Appointment appointment) {
		this.appointments.add(appointment);
	}

	@Override
	public String toString() {
		return "Patient{" +
				"id=" + id +
				", name='" + name + '\'' +
				", address=" + (address != null ? address.toString() : "null") +
				'}';
	}
}
