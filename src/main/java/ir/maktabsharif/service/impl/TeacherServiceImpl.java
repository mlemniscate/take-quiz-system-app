package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.User;
import ir.maktabsharif.domain.enums.SignUpStatus;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherServiceImpl extends BaseServiceImpl<Teacher, Long, TeacherRepository> implements TeacherService {

    private final UserRepository userRepository;

    public TeacherServiceImpl(TeacherRepository repository, UserRepository userRepository) {
        super(repository);
        this.userRepository = userRepository;
    }

    // When we save teacher in teachers table we have to save it in users table too.
    // Return USER_ALREADY_EXISTS status if user exist with same username or email
    @Override
    public SignUpStatus save(Teacher newTeacher) {
        Optional<User> byUsername = userRepository.findByUsername(newTeacher.getUsername());
        Optional<User> byEmail = userRepository.findByEmail(newTeacher.getEmail());
        if(byUsername.isPresent() || byEmail.isPresent()) return SignUpStatus.USER_ALREADY_EXISTS;
        repository.save(newTeacher);
        return SignUpStatus.SUCCESS;
    }

    @Override
    public List<Teacher> getAll() {
        return repository.findAll();
    }
}
