package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.CourseMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.SaveCourseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseRestFul<Course, CourseDTO, Long, CourseService, CourseMapper> {

    public CourseController(CourseService service, CourseMapper mapper) {
        super(service, mapper);
    }

    // get all courses
    @GetMapping("/get-all")
    @CrossOrigin
    List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    // create a new course
    @PostMapping("/save")
    @CrossOrigin
    Course saveCourse(@RequestBody SaveCourseDTO saveCourseDTO) {
        return service.saveCourse(saveCourseDTO);
    }

}
