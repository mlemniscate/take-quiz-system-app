package ir.maktabsharif.controller;

import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.model.enums.Status;
import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/teacher/sign-up")
    @CrossOrigin
    Status newEmployee(@RequestBody Teacher newTeacher) {
        newTeacher.setRole(Role.TEACHER);
        return service.save(newTeacher);
    }

}
