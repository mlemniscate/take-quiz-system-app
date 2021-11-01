package ir.maktabsharif.repository;

import ir.maktabsharif.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByFirstNameContaining(String firstName);
}
