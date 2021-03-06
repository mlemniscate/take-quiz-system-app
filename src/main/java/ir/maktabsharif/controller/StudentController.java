package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.StudentMapper;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.domain.enums.SignUpStatus;
import ir.maktabsharif.service.StudentService;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.StudentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController extends BaseRestFul<Student, StudentDTO, Long, StudentService, StudentMapper> {

    public StudentController(StudentService service, StudentMapper mapper) {
        super(service, mapper);
    }

    @PostMapping("/sign-up")
    SignUpStatus newStudent(@RequestBody Student newStudent) {
        newStudent.setRole(Role.STUDENT);
        return service.save(newStudent);
    }



}
