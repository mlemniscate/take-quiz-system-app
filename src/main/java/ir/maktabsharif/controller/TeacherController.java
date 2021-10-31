package ir.maktabsharif.controller;

import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;

}
