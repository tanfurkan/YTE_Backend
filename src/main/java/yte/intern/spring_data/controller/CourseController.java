package yte.intern.spring_data.controller;

import org.springframework.web.bind.annotation.*;
import yte.intern.spring_data.model.Course;
import yte.intern.spring_data.services.CourseService;

import java.util.List;

@RestController
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/course")
    public Course addCourse(@RequestBody final Course course){
        return courseService.addCourse(course);
    }

    @GetMapping("/course")
    public Course getCourse(@RequestParam final Long course_code){
        return courseService.getCourse(course_code);
    }

    @GetMapping("/courses")
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @PutMapping("/course")
    public Course updateCourse(@RequestParam final Long course_code, @RequestBody final Course course){
        return courseService.updateCourse(course_code,course);
    }

    @DeleteMapping("/course")
    public String deleteCourse(@RequestParam final Long course_code){
        return courseService.deleteCourse(course_code);
    }
}
