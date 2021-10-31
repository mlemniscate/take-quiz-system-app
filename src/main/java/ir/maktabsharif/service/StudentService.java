package ir.maktabsharif.service;

import ir.maktabsharif.model.Student;
import ir.maktabsharif.model.enums.Status;

public interface StudentService {
    Status save(Student newStudent);
}
