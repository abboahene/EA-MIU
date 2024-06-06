package bank.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
public class Person {

	@Id
	@GeneratedValue
	private int id;
	private String firstName;
	private String lastName;
	@OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	private Collection<Pet> pets = new ArrayList<>();

	public Person(){}
	public Person(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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

	public Collection<Pet> getPets() {
		return pets;
	}

	public void setPets(Collection<Pet> pets) {
		this.pets = pets;
	}

	public void addPet(Pet pet) {
		this.pets.add(pet);
	}
}
