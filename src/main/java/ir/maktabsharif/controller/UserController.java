package ir.maktabsharif.controller;

import io.swagger.annotations.ApiOperation;
import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.UserMapper;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.LoginStatus;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.dto.FilterUserDTO;
import ir.maktabsharif.service.dto.LoginUserDTO;
import ir.maktabsharif.service.dto.UserDTO;
import ir.maktabsharif.service.dto.extra.UserWithoutPasswordDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseRestFul<User, UserDTO, Long, UserService, UserMapper> {

    public UserController(UserService service, UserMapper mapper) {
        super(service, mapper);
    }

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
        List<User> users = service.findAllUsersByFilter(new FilterUserDTO(firstName, lastName, gender, role, isActive));
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

//    Get all the users without password from this api
    @GetMapping("/safe-get-all")
    @ApiOperation(value = "get all entities without password")
    public ResponseEntity<List<UserWithoutPasswordDTO>> getAllSafe() {
        return ResponseEntity.ok(mapper.convertListEntityToUserWithoutPassword(
                service.findAllNotSecure()
        ));
    }
}
