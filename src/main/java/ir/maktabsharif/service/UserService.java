package ir.maktabsharif.service;

import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.model.enums.LoginStatus;

public interface UserService {
    LoginStatus login(LoginUserDTO loginUserDTO);
}
