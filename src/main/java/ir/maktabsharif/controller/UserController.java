package ir.maktabsharif.controller;

import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.model.enums.LoginStatus;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user/login")
    @CrossOrigin
    LoginStatus newEmployee(@RequestBody LoginUserDTO loginUserDTO) {
        return service.login(loginUserDTO);
    }

}
