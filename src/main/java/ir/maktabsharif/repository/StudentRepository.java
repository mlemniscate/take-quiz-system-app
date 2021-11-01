package ir.maktabsharif.repository;

import ir.maktabsharif.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
