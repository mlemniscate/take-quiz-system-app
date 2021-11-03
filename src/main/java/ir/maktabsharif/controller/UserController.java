package ir.maktabsharif.controller;

import ir.maktabsharif.controller.dto.FilterUserDTO;
import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.controller.dto.UserWithoutPasswordDTO;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.LoginStatus;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user/login")
    @CrossOrigin
    LoginStatus loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        return service.login(loginUserDTO);
    }

    @PostMapping("/user/edit")
    @CrossOrigin
    void editUser(@RequestBody UserWithoutPasswordDTO userWithoutPasswordDTO) {
        service.editUser(userWithoutPasswordDTO);
    }

    @GetMapping("/user/filter-users")
    @CrossOrigin
    List<UserWithoutPasswordDTO> getAllUsersFilter(@RequestParam String firstName, String lastName, String gender, String role, Boolean isActive) {
        Role backRole = role.equals("ADMIN") ? Role.ADMIN :
                role.equals("TEACHER") ? Role.TEACHER :
                role.equals("STUDENT") ? Role.STUDENT : null;
        List<User> users = service.findAllUsersByFilter(new FilterUserDTO(firstName, lastName, gender, backRole, isActive));
        List<UserWithoutPasswordDTO> userDTOList = new ArrayList<>();
        users.forEach(item -> {
            userDTOList.add(
                    new UserWithoutPasswordDTO(
                            item.getUsername(),
                            item.getFirstName(),
                            item.getLastName(),
                            item.getEmail(),
                            item.getGender(),
                            item.getRole(),
                            item.getIsActive()
                    )
            );
        });
        return userDTOList;
    }

}
