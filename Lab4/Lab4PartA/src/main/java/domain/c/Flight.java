package domain.c;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Flight {
	@Id
	@GeneratedValue
	private int id;
	private String flightNumber;

	@Column(name ="origin")
	private String from;
	@Column(name ="destination")
	private String to;
	private LocalDate date;

	public Flight() {
	}

	public Flight(String flightNumber, String from, String to, LocalDate date) {
		this.flightNumber = flightNumber;
		this.from = from;
		this.to = to;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}


	@Override
	public String toString() {
		return "Flight{" +
				"id=" + id +
				", flightNumber='" + flightNumber + '\'' +
				", from='" + from + '\'' +
				", to='" + to + '\'' +
				", date=" + date +
				'}';
	}
}


