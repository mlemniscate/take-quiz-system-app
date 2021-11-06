package ir.maktabsharif.service;

import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.enums.SignUpStatus;

public interface StudentService {
    SignUpStatus save(Student newStudent);
}
