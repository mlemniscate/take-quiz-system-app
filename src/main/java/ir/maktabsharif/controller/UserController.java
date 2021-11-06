package ir.maktabsharif.controller;

import ir.maktabsharif.service.dto.FilterUserDTO;
import ir.maktabsharif.service.dto.LoginUserDTO;
import ir.maktabsharif.service.dto.UserWithoutPasswordDTO;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.LoginStatus;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/login")
    @CrossOrigin
    LoginStatus loginUser(@RequestBody LoginUserDTO loginUserDTO) {
        return service.login(loginUserDTO);
    }

    @PostMapping("/edit")
    @CrossOrigin
    void editUser(@RequestBody UserWithoutPasswordDTO userWithoutPasswordDTO) {
        service.editUser(userWithoutPasswordDTO);
    }

    @GetMapping("/filter-users")
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
