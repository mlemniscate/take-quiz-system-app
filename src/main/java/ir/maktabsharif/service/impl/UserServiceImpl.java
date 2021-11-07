package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.LoginStatus;
import ir.maktabsharif.domain.enums.Role;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.UserService;
import ir.maktabsharif.service.dto.extra.FilterUserDTO;
import ir.maktabsharif.service.dto.extra.LoginUserDTO;
import ir.maktabsharif.service.dto.extra.UserWithoutPasswordDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    // when user login we send different login statuses based on loginUserDTO information
    @Override
    public LoginStatus login(LoginUserDTO loginUserDTO) {
        Optional<User> user = repository.findByUsername(loginUserDTO.getUsername());
        if (user.isEmpty()) return LoginStatus.WRONG_USERNAME;
        if (!user.get().getPassword().equals(loginUserDTO.getPassword())) return LoginStatus.WRONG_PASSWORD;
        if (!user.get().getIsActive()) return LoginStatus.IS_NOT_ACTIVE;
        if (user.get().getRole().equals(Role.ADMIN)) return LoginStatus.ADMIN;
        if (user.get().getRole().equals(Role.STUDENT)) return LoginStatus.STUDENT;
        if (user.get().getRole().equals(Role.TEACHER)) return LoginStatus.TEACHER;
        else return null;
    }

    // return users based on filterUserDTO information and filter users
    @Override
    public List<User> findAllUsersByFilter(FilterUserDTO filterUserDTO) {
        String gender = filterUserDTO.getGender().equals("FEMALE") ? "F" :
                filterUserDTO.getGender().equals("MALE") ? "M" : "";
        Boolean isActiveUser = filterUserDTO.getIsActive();
        List<Boolean> isActive = Objects.isNull(isActiveUser) ? List.of(true, false) :
                isActiveUser ? List.of(true) : List.of(false);
        return repository.findAll(
                filterUserDTO.getFirstName(),
                filterUserDTO.getLastName(),
                gender,
                filterUserDTO.getRole(),
                isActive
                );
    }

    @Override
    public void editUser(UserWithoutPasswordDTO userWithoutPasswordDTO) {
        Optional<User> byUsername = repository.findByUsername(userWithoutPasswordDTO.getUsername());
        if (byUsername.isPresent()) {
            User user = byUsername.get();
            user.setFirstName(userWithoutPasswordDTO.getFirstName());
            user.setLastName(userWithoutPasswordDTO.getLastName());
            user.setEmail(userWithoutPasswordDTO.getEmail());
            user.setGender(userWithoutPasswordDTO.getGender());
            user.setRole(userWithoutPasswordDTO.getRole());
            user.setIsActive(userWithoutPasswordDTO.getIsActive());
            user = repository.save(user);
            String userType = userWithoutPasswordDTO.getRole().toString();
            repository.editUserTypeNative(userType, user.getUsername());
        }
    }
}
