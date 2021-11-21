package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.SignUpStatus;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student, Long, StudentRepository> implements StudentService {

    private final UserRepository userRepository;

    public StudentServiceImpl(StudentRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    // When we save student in students table we have to save it in users table too.
    // Return USER_ALREADY_EXISTS status if user exist with same username or email
    @Override
    public SignUpStatus save(Student newStudent) {
        Optional<User> byUsername = userRepository.findByUsername(newStudent.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(newStudent.getEmail());
        if(byUsername.isPresent() || byEmail.isPresent()) return SignUpStatus.USER_ALREADY_EXISTS;
        repository.save(newStudent);
        return SignUpStatus.SUCCESS;
    }
}
