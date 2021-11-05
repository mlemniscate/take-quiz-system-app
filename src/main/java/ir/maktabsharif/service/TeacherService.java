package ir.maktabsharif.service;

import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.enums.SignUpStatus;

public interface TeacherService {
    SignUpStatus save(Teacher newTeacher);
}
