package yte.intern.spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring_data.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentNumber(Long studentNumber);
}
