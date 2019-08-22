package yte.intern.spring_data.services;

import org.springframework.stereotype.Service;
import yte.intern.spring_data.model.Course;
import yte.intern.spring_data.model.Student;
import yte.intern.spring_data.repository.CourseRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(final CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course addCourse(final Course course){
        Optional<Course> optionalCourse = courseRepository.findCourseByCode(course.getCode());
        if(optionalCourse.isEmpty()){
            course.setEnrolledStudents(new HashSet<Student>());
            return courseRepository.save(course);
        }
        else{
            return null;
        }
    }

    public Course getCourse(final Long course_code){
        Optional<Course> course = courseRepository.findCourseByCode(course_code);
        if(course.isPresent()){
            return course.get();
        }
        else{
            return null;
        }
    }

    public Course updateCourse(final Long course_code,final Course course){
        Optional<Course> optional_course = courseRepository.findCourseByCode(course_code);
        if(optional_course.isPresent()){
            Course course_local = optional_course.get();
            course_local.setName(course.getName());
            course_local.setInstructor(course.getInstructor());
            course_local.setEnrolledStudents(course.getEnrolledStudents());
            return courseRepository.save(course_local);
        }
        else{
            return null;
        }
    }

    public String deleteCourse(final Long course_code){
        Optional<Course> course = courseRepository.findCourseByCode(course_code);
        if(course.isPresent()){
            courseRepository.delete(course.get());
            return ("Course code with "+ course_code + " is deleted.");
        }
        else{
            return ("No Course present with code "+ course_code);

        }
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }


}
