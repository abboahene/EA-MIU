package app;

import domain.Course;
import domain.Department;
import domain.Grade;
import domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.StudentRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain") 
public class Application implements CommandLineRunner{

	@Autowired
	StudentRepository studentRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	@Transactional
	public void run(String... args) throws Exception {

		Course course1 = new Course("Algo 1");
		Course course2 = new Course("Algo 2");
		Grade A = new Grade("A", course1);
		Grade B = new Grade("B", course2);

		Department department1 = new Department("CS1");

		Student student1 = new Student("Alfred 1", "38934", department1);
		student1.addGrade(A);

		Department department2 = new Department("CS2");

		Student student2 = new Student("Alfred 2", "38934", department2);
		student2.addGrade(B);

		studentRepository.save(student1);
		studentRepository.save(student2);

		System.out.println(studentRepository.findStudentsByCourseName("Algo 1"));
		System.out.println(studentRepository.findStudentsByDepartmentName("CS1"));


	}

}
