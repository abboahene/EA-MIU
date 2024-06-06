package domain.Product;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class CD extends Product{

	private String artist;

	public CD() {
		super();
	}

	public CD(String name, String description, double price, String artist) {
		super(name, description, price);
		this.artist = artist;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return super.toString() + "\b, artist='" + artist + "'}";
	}
}
