package ir.maktabsharif.controller;

import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

}
