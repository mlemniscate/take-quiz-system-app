package ir.maktabsharif.service;

import ir.maktabsharif.service.dto.SaveCourseDTO;
import ir.maktabsharif.domain.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course saveCourse(SaveCourseDTO course);
}
