package domain.product;


import jakarta.persistence.Entity;

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
