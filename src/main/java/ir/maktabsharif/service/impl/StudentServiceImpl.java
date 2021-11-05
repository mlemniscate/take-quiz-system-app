package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.User;
import ir.maktabsharif.model.enums.SignUpStatus;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    // When we save student in students table we have to save it in users table too.
    // Return USER_ALREADY_EXISTS status if user exist with same username or email
    @Override
    public SignUpStatus save(Student newStudent) {
        Optional<User> byUsername = userRepository.findByUsername(newStudent.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(newStudent.getEmail());
        if(byUsername.isPresent() || byEmail.isPresent()) return SignUpStatus.USER_ALREADY_EXISTS;
        studentRepository.save(newStudent);
        return SignUpStatus.SUCCESS;
    }
}
