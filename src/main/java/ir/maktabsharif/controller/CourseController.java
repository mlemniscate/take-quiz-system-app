package ir.maktabsharif.controller;

import io.swagger.annotations.ApiOperation;
import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.CourseMapper;
import ir.maktabsharif.controller.mapper.QuizMapper;
import ir.maktabsharif.controller.mapper.StudentMapper;
import ir.maktabsharif.controller.mapper.TeacherMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.QuizDTO;
import ir.maktabsharif.service.dto.StudentDTO;
import ir.maktabsharif.service.dto.TeacherDTO;
import ir.maktabsharif.service.dto.extra.CourseWithoutStudentsDTO;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
import javassist.NotFoundException;
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
    private final QuizMapper quizMapper;

    public CourseController(CourseService service, CourseMapper mapper, StudentMapper studentMapper, TeacherMapper teacherMapper, QuizMapper quizMapper) {
        super(service, mapper);
        this.studentMapper = studentMapper;
        this.teacherMapper = teacherMapper;
        this.quizMapper = quizMapper;
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

    // set teacher to course
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

    // Add a students to a course
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

    // Delete a student from a course
    @DeleteMapping("/delete-student/{studentId}/{courseId}")
    ResponseEntity<Void> deleteStudentFromCourse(@PathVariable Long studentId,@PathVariable Long courseId) {
        service.deleteStudentFromCourse(studentId, courseId);
        return ResponseEntity.ok().build();
    }
    
    // get all students of a course
    @GetMapping("/get-all-students/{id}")
    ResponseEntity<List<StudentDTO>> getAllStudentsByCourseId(@PathVariable("id") Long id) {
        Optional<Course> course = service.findByIdNotSecure(id);
        return course.map(value -> ResponseEntity.ok(
                studentMapper.convertListEntityToDTO(value.getStudents())
        )).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get teacher courses
    @GetMapping("/get-teacher-courses/{teacherId}")
    ResponseEntity<List<CourseDTO>> findAllTeacherCourses(@PathVariable Long teacherId) {
        return ResponseEntity.ok(
                mapper.convertListEntityToDTO(
                        service.findTeacherCourses(teacherId)
                )
        );
    }

    // Add quiz to course
    @PostMapping("/add-quiz/{courseId}")
    @ApiOperation(value = "add a quiz to course")
    public ResponseEntity<CourseDTO> save(@RequestBody QuizDTO quizDTO, @PathVariable Long courseId) throws NotFoundException {
        return ResponseEntity.ok(
                mapper.convertEntityToDTO(
                        service.addQuiz(quizMapper.convertDTOToEntity(quizDTO), courseId)
                )
        );
    }

    @GetMapping("/all-student-courses/{id}")
    public ResponseEntity<List<CourseWithoutStudentsDTO>> findAllStudentCourses(@PathVariable Long id){
        return ResponseEntity.ok(
                mapper.convertListEntityToCourseWithoutStudentDTO(
                        service.findStudentCourses(id)
                )
        );
    }

}
