package yte.intern.spring_data.services;

import org.springframework.stereotype.Service;
import yte.intern.spring_data.model.Course;
import yte.intern.spring_data.model.Student;
import yte.intern.spring_data.repository.CourseRepository;
import yte.intern.spring_data.repository.StudentRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public StudentService(final StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student addStudent(final Student student){
        Optional<Student> optionalStudent = studentRepository.findByStudentNumber(student.getStudentNumber());
        if(optionalStudent.isEmpty()){
            student.setEnrolledCourses(new HashSet<Course>());
            return studentRepository.save(student);
        }
        else{
            return null;
        }
    }

    public Student getStudent(final Long student_number){
        Optional<Student> student = studentRepository.findByStudentNumber(student_number);
        if(student.isPresent()){
            return student.get();
        }
        else{
            return null;
        }
    }

    public Student updateStudent(final Long student_number,final Student student){
        Optional<Student> optional_student = studentRepository.findByStudentNumber(student_number);
        if(optional_student.isPresent()){
            Student student_local = optional_student.get();
            student_local.setName(student.getName());
            student_local.setSurname(student.getSurname());
            student_local.setEnrolledCourses(student.getEnrolledCourses());
            return studentRepository.save(student_local);
        }
        else{
            return null;
        }
    }

    public String deleteStudent(final Long student_number){
        Optional<Student> student = studentRepository.findByStudentNumber(student_number);
        if(student.isPresent()){
            studentRepository.delete(student.get());
            return ("Student with "+ student_number + " is deleted.");
        }
        else{
            return ("No Student present with "+ student_number);
        }
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public Boolean enrollStudent(Long student_number, Long course_code) {
        Optional<Student> student = studentRepository.findByStudentNumber(student_number);
        if(student.isPresent()){
            Student newStudent = student.get();
            Optional<Course> course = courseRepository.findCourseByCode(course_code);
            if(course.isPresent()){
                newStudent.getEnrolledCourses().add(course.get());
                studentRepository.save(newStudent);
                return true;
            }
        }
        return false;
    }
}
