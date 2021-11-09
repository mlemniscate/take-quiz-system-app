package ir.maktabsharif.repository;

import ir.maktabsharif.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    List<Course> findCourseByTeacherId(Long teacher_id);

}
