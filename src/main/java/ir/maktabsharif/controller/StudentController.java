package ir.maktabsharif.controller;

import ir.maktabsharif.controller.dto.ReturnUserDTO;
import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    @PostMapping("/student")
    ReturnUserDTO newEmployee(@RequestBody Student newStudent) {
        newStudent.setRole(Role.STUDENT);
        Student student = service.save(newStudent);
        return new ReturnUserDTO(student.getUsername(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender(),
                student.getRole());
    }

}
