package ir.maktabsharif.service;


import ir.maktabsharif.controller.dto.FilterUserDTO;
import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.controller.dto.UserWithoutPasswordDTO;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.LoginStatus;

import java.util.List;

public interface UserService {
    LoginStatus login(LoginUserDTO loginUserDTO);

    List<User> findAllUsersByFilter(FilterUserDTO filterUserDTO);

    void editUser(UserWithoutPasswordDTO userWithoutPasswordDTO);
}
