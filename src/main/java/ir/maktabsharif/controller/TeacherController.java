package ir.maktabsharif.controller;

import ir.maktabsharif.controller.dto.ReturnUserDTO;
import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/teacher")
    ReturnUserDTO newEmployee(@RequestBody Teacher newTeacher) {
        newTeacher.setRole(Role.TEACHER);
        Teacher teacher = service.save(newTeacher);
        return new ReturnUserDTO(teacher.getUsername(),
                teacher.getFirstName(),
                teacher.getLastName(),
                teacher.getEmail(),
                teacher.getGender(),
                teacher.getRole());
    }

}
