package yte.intern.spring_data.controller;

import org.springframework.web.bind.annotation.*;
import yte.intern.spring_data.model.Student;
import yte.intern.spring_data.services.StudentService;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public Student addStudent(@RequestBody final Student student){
        return studentService.addStudent(student);
    }

    @GetMapping("/student")
    public Student getStudent(@RequestParam final Long student_number){
        return studentService.getStudent(student_number);
    }

    @GetMapping("/students")
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    @PutMapping("/student")
    public Student updateStudent(@RequestParam final Long student_number, @RequestBody final Student student){
        return studentService.updateStudent(student_number,student);
    }

    @DeleteMapping("/student")
    public String deleteStudent(@RequestParam final Long student_number){
        return studentService.deleteStudent(student_number);
    }

    @PostMapping("/enroll")
    public Boolean enrollStudent(@RequestParam Long student_number,@RequestParam Long course_code){
        return studentService.enrollStudent(student_number,course_code);
    }

}
