package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private int id;
	private String firstName;

	private String lastName;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Address address;

	@OneToMany(mappedBy = "customer", cascade =  CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Order> theOrders = new ArrayList<>();

	public Customer() {
	}

	public Customer(String firstName, String lastName, String street,
					String city, String zip) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = new Address(street, city, zip);
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

	public Collection<Order> getTheOrders() {
		return Collections.unmodifiableCollection(theOrders);
	}

	public boolean addOrder(Order order) {
		boolean added = theOrders.add(order); 
		if (added) {
			order.setCustomer(this);
		}
		return added;
	}

	public boolean removeOrder(Order order) {
		boolean removed = theOrders.remove(order);
		if (removed) {
			theOrders.remove(order);
		}
		return removed;
	}

	@Override
	public String toString() {
		return "Customer{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", address=" + (address != null ? address.toString() : "null") +
				'}';
	}
}
