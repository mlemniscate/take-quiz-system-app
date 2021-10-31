package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.User;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    // When we save students in student table we have to save it in users table too.
    @Override
    public Student save(Student newStudent) {
        userRepository.save((User) newStudent);
        return studentRepository.save(newStudent);
    }
}
