package domain.d;

import domain.c.Flight;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class School {

    @Id
    @GeneratedValue
    private int id;
    private String name;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @MapKey(name = "studentId")
    private Map<String, Student> students = new HashMap<>();

    public School() {
    }
    public School(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }
}
