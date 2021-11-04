package ir.maktabsharif.controller;

import ir.maktabsharif.controller.dto.SaveCourseDTO;
import ir.maktabsharif.model.Course;
import ir.maktabsharif.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

    // get all courses
    @GetMapping("/course/get-all")
    @CrossOrigin
    List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    // create a new course
    @PostMapping("/course/save")
    @CrossOrigin
    Course saveCourse(@RequestBody SaveCourseDTO saveCourseDTO) {
        return service.saveCourse(saveCourseDTO);
    }

}
