package ir.maktabsharif.service;

import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.enums.SignUpStatus;

import java.util.List;

public interface TeacherService {
    SignUpStatus save(Teacher newTeacher);

    List<Teacher> getAll();
}
