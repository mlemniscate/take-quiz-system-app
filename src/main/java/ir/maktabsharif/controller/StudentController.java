package ir.maktabsharif.controller;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping("/student/sign-up")
    @CrossOrigin
    Status newEmployee(@RequestBody Student newStudent) {
        newStudent.setRole(Role.STUDENT);
        return service.save(newStudent);
    }

}
