package yte.intern.spring_data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import yte.intern.spring_data.model.Course;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Optional<Course> findCourseByCode(long code);
}
