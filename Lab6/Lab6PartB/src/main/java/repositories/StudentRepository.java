package repositories;

import domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("SELECT s FROM Student s JOIN FETCH s.department d WHERE d.name = :departmentName")
    List<Student> findStudentsByDepartmentName(@Param("departmentName") String departmentName);

    @Query("SELECT s FROM Student s JOIN FETCH s.grades g JOIN FETCH g.course c WHERE c.name = :courseName")
    List<Student> findStudentsByCourseName(@Param("courseName") String courseName);
}
