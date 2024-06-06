package bank.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Tracerecord {

	@Id
	@GeneratedValue
	private long id;

	private LocalDate date;
	private LocalTime time;

    private String message;

	protected Tracerecord() {
	}

	public Tracerecord(String message) {
		this.message = message;
		this.date = LocalDate.now();
		this.time = LocalTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}
}


