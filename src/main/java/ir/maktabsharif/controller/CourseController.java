package ir.maktabsharif.controller;

import ir.maktabsharif.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CourseController {

    private final CourseService service;

}
