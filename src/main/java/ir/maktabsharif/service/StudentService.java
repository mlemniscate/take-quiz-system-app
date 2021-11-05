package ir.maktabsharif.service;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.enums.SignUpStatus;

public interface StudentService {
    SignUpStatus save(Student newStudent);
}
