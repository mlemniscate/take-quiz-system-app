package ir.maktabsharif.service.impl;

import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.User;
import ir.maktabsharif.repository.TeacherRepository;
import ir.maktabsharif.repository.UserRepository;
import ir.maktabsharif.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final UserRepository userRepository;

    @Override
    public Teacher save(Teacher newTeacher) {
        userRepository.save((User) newTeacher);
        return teacherRepository.save(newTeacher);
    }
}
