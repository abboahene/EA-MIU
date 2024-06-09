package client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/books";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// add 748282
		restTemplate.postForLocation(serverUrl, new Book("748282", "Frank Brown", "Keep On", 100.99F));

		// get 748282
		Book book= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "748282");
		System.out.println(book);

		// add 248423
		restTemplate.postForLocation(serverUrl, new Book("248423", "Fanin Smith", "Thinker", 120.99F));

		// get 248423
		Book book1= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "248423");
		System.out.println(book1);

		// delete 248423
		restTemplate.delete(serverUrl+"/{isbn}", "748282");

		// update 248423
		book1.setAuthor("Fanin Smith");
		restTemplate.put(serverUrl+"/{isbn}" , book, "248423");

		// get 748282
		book1= restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "248423");
		System.out.println(book1);

        // get all contacts
		Books books = restTemplate.getForObject(serverUrl, Books.class);
		System.out.println(books);
	}

}
