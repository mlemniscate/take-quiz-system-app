package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.CourseMapper;
import ir.maktabsharif.controller.mapper.StudentMapper;
import ir.maktabsharif.controller.mapper.TeacherMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
import ir.maktabsharif.service.dto.StudentDTO;
import ir.maktabsharif.service.dto.TeacherDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/course")
@CrossOrigin
public class CourseController extends BaseRestFul<Course, CourseDTO, Long, CourseService, CourseMapper> {

    private final StudentMapper studentMapper;
    private final TeacherMapper teacherMapper;

    public CourseController(CourseService service, CourseMapper mapper, StudentMapper studentMapper, TeacherMapper teacherMapper) {
        super(service, mapper);
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
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

    @PutMapping("/set-teacher")
    ResponseEntity<CourseDTO> updateCourseTeacher(@RequestBody TeacherDTO teacherDTO, @RequestParam Long courseId) {
        Teacher teacher = teacherMapper.convertDTOToEntity(teacherDTO);
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.saveTeacherToCourse(
                                teacher,
                                courseId
                        )
                )
        );
    }

    @PutMapping("/set-student")
    ResponseEntity<CourseDTO> updateCourseTeacher(@RequestBody StudentDTO studentDTO, @RequestParam Long courseId) {
        Student student = studentMapper.convertDTOToEntity(studentDTO);
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.saveStudentsToCourse(
                                student,
                                courseId
                        )
                )
        );
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
