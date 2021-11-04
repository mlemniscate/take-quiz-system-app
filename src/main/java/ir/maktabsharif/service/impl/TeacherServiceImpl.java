package ir.maktabsharif.service.impl;

import ir.maktabsharif.controller.dto.UserWithoutPasswordDTO;
import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.SignUpStatus;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    // When we save teacher in teachers table we have to save it in users table too.
    // Return USER_ALREADY_EXISTS status if user exist with same username or email
    @Override
    public SignUpStatus save(Teacher newTeacher) {
        Optional<User> byUsername = userRepository.findByUsername(newTeacher.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(newTeacher.getEmail());
        if(byUsername.isPresent() || byEmail.isPresent()) return SignUpStatus.USER_ALREADY_EXISTS;
        teacherRepository.save(newTeacher);
        return SignUpStatus.SUCCESS;
    }

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }
}
