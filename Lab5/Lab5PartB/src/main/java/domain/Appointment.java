package domain;


import jakarta.persistence.*;

@Entity
public class Appointment {

	@Id
	@GeneratedValue
	private int id;
	private String appDate;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "patient_id")
	private Patient patient;

	@Embedded
	private Payment payment;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Doctor doctor;

	public Appointment() {
	}

	public Appointment(String appDate, Patient patient, Payment payment,
					   Doctor doctor) {
		this.appDate = appDate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
		patient.addAppointment(this);
		doctor.addAppointment(this);
	}

	public String getAppDate() {
		return appDate;
	}

	public void setAppDate(String appDate) {
		this.appDate = appDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", appDate='" + appDate + '\'' +
				", patient=" + (patient != null ? patient.toString() : "null") +
				", payment=" + (payment != null ? payment.toString() : "null") +
				", doctor=" + (doctor != null ? doctor.toString() : "null") +
				'}';
	}
}
