package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.repository.AdminRepository;
import ir.maktabsharif.repository.CourseRepository;
import ir.maktabsharif.service.CourseService;
import ir.maktabsharif.service.dto.extra.SaveCourseDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseServiceImpl extends BaseServiceImpl<Course, Long, CourseRepository> implements CourseService {

    private final AdminRepository adminRepository;

    public CourseServiceImpl(CourseRepository repository, AdminRepository adminRepository) {
        super(repository);
        this.adminRepository = adminRepository;
    }

    @Override
    public List<Course> getAllCourses() {
        return repository.findAll();
    }

    @Override
    public Course saveCourse(SaveCourseDTO course) {
        return repository.save(
                Course.builder()
                .title(course.getTitle())
                .startDate(course.getStartDate())
                .endDate(course.getEndDate())
                .build()
        );
    }
}
