package customers.repository;

import customers.domain.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends MongoRepository<Student, Integer> {

    Student findByName(String name);
    Student findByPhone(String phone);
    @Query("{'address.city' : :#{#city}}")
    Student findWithCity(@Param("city") String city);

    @Query("{'grades.courseName' : :#{#courseName}}")
    List<Student> findWithCourseName(@Param("courseName") String courseName);

    @Query("{'grades.courseName' : :#{#courseName}, 'grades.grade' : 'A+'}")
    List<Student> findWithAPlusAndCourseName(@Param("courseName") String courseName);
}

