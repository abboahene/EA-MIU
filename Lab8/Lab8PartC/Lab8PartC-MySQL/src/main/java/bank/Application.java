package bank;

import bank.domain.Person;
import bank.domain.Pet;
import bank.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.util.StopWatch;

import java.util.Random;

@SpringBootApplication
@EnableJpaRepositories("bank.repository")
@EntityScan("bank.domain")
public class Application implements CommandLineRunner {

	@Autowired
	PersonRepository personRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		addAllPersons();
		fetchAllPersons();
	}

	public void addAllPersons(){
		StopWatch sw = new StopWatch();
		sw.start();
		for (int x=0; x< 10000; x++){
			Person person =	new Person("John" + x, "Doe"+ x);
			for (int y=0; y< 10; y++){
				person.addPet(new Pet("Pet " + x + " " + y, y));
			}
			personRepository.save(person);
		}
		sw.stop();
		System.out.println("Adding all persons took "+sw.getTotalTimeMillis()+" ms");
	}

	public void fetchAllPersons(){
		StopWatch sw = new StopWatch();
		sw.start();
		personRepository.findAll();
		sw.stop();
		System.out.println("Fetching all persons took "+sw.getTotalTimeMillis()+" ms");
	}
}


