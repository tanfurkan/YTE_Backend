package yte.intern.spring_data.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    @Column(unique=true)
    private Long studentNumber;
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private Set<Book> books;
    @ManyToMany
    @JoinTable(
            name = "enrollment",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private Set<Course> enrolledCourses;
}
