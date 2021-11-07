package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.service.dto.SaveCourseDTO;
import ir.maktabsharif.domain.Course;

import java.util.List;

public interface CourseService extends BaseService<Course, Long> {
    List<Course> getAllCourses();

    Course saveCourse(SaveCourseDTO course);
}
