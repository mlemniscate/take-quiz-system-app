package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.CourseMapper;
import ir.maktabsharif.controller.mapper.StudentMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.SaveCourseDTO;
import ir.maktabsharif.service.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController extends BaseRestFul<Course, CourseDTO, Long, CourseService, CourseMapper> {

    private final StudentMapper studentMapper;

    public CourseController(CourseService service, CourseMapper mapper, StudentMapper studentMapper) {
        super(service, mapper);
        this.studentMapper = studentMapper;
    }

    // get all courses
    @GetMapping("/get-all")
    List<Course> getAllCourses() {
        return service.getAllCourses();
    }

    // create a new course
    @PostMapping("/save")
    Course saveCourse(@RequestBody SaveCourseDTO saveCourseDTO) {
        return service.saveCourse(saveCourseDTO);
    }
    
    // get all students of a course
    @GetMapping("/get-all-students/{id}")
    ResponseEntity<List<StudentDTO>> getAllStudentsByCourseId(@PathVariable("id") Long id) {
        Optional<Course> course = service.findByIdNotSecure(id);
        return course.map(value -> ResponseEntity.ok(
                studentMapper.convertListEntityToDTO(value.getStudents())
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
