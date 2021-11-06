package ir.maktabsharif.controller;

import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.domain.enums.SignUpStatus;
import ir.maktabsharif.service.TeacherService;
import ir.maktabsharif.service.dto.extra.UserWithoutPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService service;

    @PostMapping("/teacher/sign-up")
    @CrossOrigin
    SignUpStatus newTeacher(@RequestBody Teacher newTeacher) {
        newTeacher.setRole(Role.TEACHER);
        return service.save(newTeacher);
    }

    @GetMapping("/teacher/get-all")
    List<UserWithoutPasswordDTO> getAllTeachers() {
        List<Teacher> teachers = service.getAll();
        List<UserWithoutPasswordDTO> userWithoutPasswordDTOS = new ArrayList<>();
        teachers.forEach(teacher -> {
            userWithoutPasswordDTOS.add(
                    new UserWithoutPasswordDTO(
                            teacher.getUsername(),
                            teacher.getFirstName(),
                            teacher.getLastName(),
                            teacher.getEmail(),
                            teacher.getGender(),
                            teacher.getRole(),
                            teacher.getIsActive()
                    )
            );
        });
        return userWithoutPasswordDTOS;
    }

}
