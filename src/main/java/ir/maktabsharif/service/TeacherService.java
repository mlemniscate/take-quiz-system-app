package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.domain.enums.SignUpStatus;
import javassist.NotFoundException;

import java.util.List;

public interface TeacherService extends BaseService<Teacher, Long> {
    SignUpStatus save(Teacher newTeacher);

    List<Teacher> getAll();

    Teacher findByUsername(String username) throws NotFoundException;
}
