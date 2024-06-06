package app;

import domain.a.Department;
import domain.b.Book;
import domain.b.Publisher;
import domain.c.Flight;
import domain.c.Passenger;
import domain.d.School;
import domain.d.Student;
import repository.BookRepository;
import repository.DepartmentRepository;
import domain.a.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.PassengerRepository;
import repository.SchoolRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	BookRepository bookRepository;

	@Autowired
	PassengerRepository passengerRepository;

	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Employee employee1 = new Employee("382983", "Kwame");
		Employee employee2 = new Employee("438943", "Kojo");
		Department department1 = new Department("IT");
		department1.addEmployee(employee1);
		department1.addEmployee(employee2);

//		departmentRepository.save(department1);

//		System.out.println(departmentRepository.findAll());


		Publisher publisher1 = new Publisher("Ama");
		Book book1 = new Book("329892", "Eat that frog", "Author 1", publisher1);
		Book book2 = new Book("849323", "Think 2", "Author 2", publisher1);
		Book book3 = new Book("784722", "Jumper 2", "Author 3", publisher1);
//		bookRepository.saveAll(List.of(book1, book2, book3));


		Flight flight1 = new Flight("8392", "Texas", "Iowa", LocalDate.now());
		Flight flight2 = new Flight("4343", "Origeon", "Iowa", LocalDate.now());
		Flight flight3 = new Flight("2123", "Dallas", "Iowa", LocalDate.now());
		Passenger passenger = new Passenger("Kojo");
		passenger.addFlight(flight1);
		passenger.addFlight(flight2);
		passenger.addFlight(flight3);
//		passengerRepository.save(passenger);

		Student student1 = new Student("8493", "Kojo1", "Kwaw1");
		Student student2 = new Student("4734", "Kojo2", "Kwaw2");
		Student student3 = new Student("2843", "Kojo3", "Kwaw3");
		School school = new School("Fosco");

		Map<String, Student> studentsMap = new HashMap<>();
		studentsMap.put(student1.getStudentId(), student1);
		studentsMap.put(student2.getStudentId(), student2);
		studentsMap.put(student3.getStudentId(), student3);
		school.setStudents(studentsMap);

//		schoolRepository.save(school);

	}

}
