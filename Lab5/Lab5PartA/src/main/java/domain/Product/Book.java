package domain.Product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book extends Product{

	private String isbn;


	public Book() {
		super();
	}

	public Book(String name, String description, double price, String isbn) {
		super(name, description, price);
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@Override
	public String toString() {
		return super.toString() + "\b, isbn='" + isbn + "'}";
	}
}
