package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.enums.SignUpStatus;

public interface StudentService extends BaseService<Student, Long> {
    SignUpStatus save(Student newStudent);
}
