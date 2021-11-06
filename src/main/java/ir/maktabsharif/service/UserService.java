package ir.maktabsharif.service;


import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.service.dto.FilterUserDTO;
import ir.maktabsharif.service.dto.LoginUserDTO;
import ir.maktabsharif.service.dto.UserWithoutPasswordDTO;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.LoginStatus;

import java.util.List;

public interface UserService extends BaseService<User, Long> {
    LoginStatus login(LoginUserDTO loginUserDTO);

    List<User> findAllUsersByFilter(FilterUserDTO filterUserDTO);

    void editUser(UserWithoutPasswordDTO userWithoutPasswordDTO);
}
