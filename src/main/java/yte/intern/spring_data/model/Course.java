package yte.intern.spring_data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Setter
@Getter
@Entity
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private Long code;
    private String instructor;
    @ManyToMany(mappedBy = "enrolledCourses")
    private Set<Student> enrolledStudents;
}
