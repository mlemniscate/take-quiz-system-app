package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
import ir.maktabsharif.domain.Course;

import java.util.List;

public interface CourseService extends BaseService<Course, Long> {
    List<Course> getAllCourses();

    Course saveCourse(SaveCourseDTO course);

    Course saveTeacherToCourse(Teacher teacher, Long courseId);

    Course saveStudentsToCourse(Student students, Long courseId);

    void deleteStudentFromCourse(Long studentId, Long courseId);
}
