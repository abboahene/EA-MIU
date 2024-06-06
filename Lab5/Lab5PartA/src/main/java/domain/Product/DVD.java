package domain.Product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class DVD extends Product{


	private String genre;

	public DVD() {
		super();
	}

	public DVD(String name, String description, double price, String genre) {
		super(name, description, price);
		this.genre = genre;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return super.toString() + "\b, genre='" + genre + "'}";
	}
}
