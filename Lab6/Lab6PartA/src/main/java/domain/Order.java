package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue
	private int id;
	private String orderNumber;

	private String date;

	private String status;

	@ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Customer customer;

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<OrderLine> orderLines = new ArrayList<>();

	public Order() {
	}

	public Order(String orderNumber, String date, String status) {
		this.orderNumber = orderNumber;
		this.date = date;
		this.status = status;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<OrderLine> getOrderLines() {
		return Collections.unmodifiableCollection(orderLines);
	}

	public boolean addOrderLine(OrderLine ol) {
		return orderLines.add(ol);
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", orderNumber='" + orderNumber + '\'' +
				", date='" + date + '\'' +
				", status='" + status + '\'' +
				", customer=" + (customer != null ? customer.toString() : "null") +
				", orderLines=" + (orderLines != null ? orderLines.toString() : "null") +
				'}';
	}
}
