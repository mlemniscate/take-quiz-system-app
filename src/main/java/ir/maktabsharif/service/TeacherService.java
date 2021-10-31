package ir.maktabsharif.service;

import ir.maktabsharif.model.Teacher;
import ir.maktabsharif.model.enums.Status;

public interface TeacherService {
    Status save(Teacher newTeacher);
}
