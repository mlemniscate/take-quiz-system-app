package ir.maktabsharif.service.impl;

import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.LoginStatus;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // when user login we send different login statuses based on situation
    @Override
    public LoginStatus login(LoginUserDTO loginUserDTO) {
        Optional<User> user = userRepository.findByUsername(loginUserDTO.getUsername());
        if(user.isEmpty()) return LoginStatus.WRONG_USERNAME;
        if(!user.get().getPassword().equals(loginUserDTO.getPassword())) return LoginStatus.WRONG_PASSWORD;
        if(!user.get().getIsActive()) return LoginStatus.IS_NOT_ACTIVE;
        if(user.get().getRole().equals(Role.ADMIN)) return LoginStatus.ADMIN;
        if(user.get().getRole().equals(Role.STUDENT)) return LoginStatus.STUDENT;
        if(user.get().getRole().equals(Role.TEACHER)) return LoginStatus.TEACHER;
        else return null;
    }
}
