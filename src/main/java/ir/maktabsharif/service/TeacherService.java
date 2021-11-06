package ir.maktabsharif.service;

import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.enums.SignUpStatus;

import java.util.List;

public interface TeacherService {
    SignUpStatus save(Teacher newTeacher);

    List<Teacher> getAll();
}
