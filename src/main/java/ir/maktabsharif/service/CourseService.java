package ir.maktabsharif.service;

import ir.maktabsharif.controller.dto.SaveCourseDTO;
import ir.maktabsharif.model.Course;

import java.util.List;

public interface CourseService {
    List<Course> getAllCourses();

    Course saveCourse(SaveCourseDTO course);
}
