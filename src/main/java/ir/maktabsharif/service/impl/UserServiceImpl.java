package ir.maktabsharif.service.impl;

import ir.maktabsharif.controller.dto.FilterUserDTO;
import ir.maktabsharif.controller.dto.LoginUserDTO;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.LoginStatus;
import ir.maktabsharif.model.enums.Role;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // when user login we send different login statuses based on loginUserDTO information
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

    // return users based on filterUserDTO information and filter users
    @Override
    public List<User> findAllUsersByFilter(FilterUserDTO filterUserDTO) {
        if(Objects.isNull(filterUserDTO.getRole())) {
            if(Objects.isNull(filterUserDTO.getIsActive())) {
                return userRepository.
                        findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleNot(
                                filterUserDTO.getFirstName(),
                                filterUserDTO.getLastName(),
                                filterUserDTO.getGender(),
                                Role.ADMIN
                        );
            } else {
                return userRepository.
                        findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleNotAndIsActive(
                                filterUserDTO.getFirstName(),
                                filterUserDTO.getLastName(),
                                filterUserDTO.getGender(),
                                Role.ADMIN,
                                filterUserDTO.getIsActive()
                        );
            }
        } else if(Objects.isNull(filterUserDTO.getIsActive())) {
            return userRepository.
                    findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRole(
                            filterUserDTO.getFirstName(),
                            filterUserDTO.getLastName(),
                            filterUserDTO.getGender(),
                            filterUserDTO.getRole()
                    );
        } else {
            return userRepository.
                    findByFirstNameContainingAndLastNameContainingAndGenderContainingAndRoleAndIsActive(
                            filterUserDTO.getFirstName(),
                            filterUserDTO.getLastName(),
                            filterUserDTO.getGender(),
                            filterUserDTO.getRole(),
                            filterUserDTO.getIsActive()
                    );
        }
    }
}
