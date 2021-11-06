package ir.maktabsharif.repository;

import ir.maktabsharif.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
