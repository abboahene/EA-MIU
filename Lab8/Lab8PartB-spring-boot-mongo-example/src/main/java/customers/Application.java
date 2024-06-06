package customers;

import customers.domain.Address;
import customers.domain.Grade;
import customers.domain.Student;
import customers.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;


@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

        // create students
		Grade gradeCS1A = new Grade("CS1", "A+");
		Grade gradeCS2A = new Grade("CS2", "A");
		Grade gradeCS3A = new Grade("CS3", "A");

		Address address1 = new Address("1001 N 4th", "Iowa 1", "52551");
		Address address2 = new Address("1002 N 4th", "Iowa 2", "52552");
		Address address3 = new Address("1003 N 4th", "Iowa 3", "52553");

		Student student1 = new Student(1, "Alfred 1", "898439", address1);
		student1.addGrade(gradeCS1A);
		student1.addGrade(gradeCS2A);
		Student student2 = new Student(2, "Alfred 2", "798439", address2);
		student2.addGrade(gradeCS2A);
		Student student3 = new Student(3, "Alfred 3", "698439", address3);
		student3.addGrade(gradeCS3A);

		studentRepository.save(student1);
		studentRepository.save(student2);
		studentRepository.save(student3);

		System.out.println(studentRepository.findAll());
		System.out.println(studentRepository.findByPhone("798439"));
		System.out.println(studentRepository.findWithCity("Iowa 1"));
		System.out.println(studentRepository.findWithCourseName("CS2"));
		System.out.println(studentRepository.findWithAPlusAndCourseName("CS1"));
	}

}
