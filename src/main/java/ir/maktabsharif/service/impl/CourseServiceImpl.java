package ir.maktabsharif.service.impl;

import ir.maktabsharif.controller.dto.SaveCourseDTO;
import ir.maktabsharif.model.Admin;
import ir.maktabsharif.model.Course;
import ir.maktabsharif.repository.AdminRepository;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final AdminRepository adminRepository;

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course saveCourse(SaveCourseDTO course) {
        Course save = courseRepository.save(
                Course.builder()
                .title(course.getTitle())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .build()
        );
        Admin admin = adminRepository.findByUsername(course.getAdminUsername());
        admin.getCourses().add(save);
        adminRepository.save(admin);
        return save;
    }
}
