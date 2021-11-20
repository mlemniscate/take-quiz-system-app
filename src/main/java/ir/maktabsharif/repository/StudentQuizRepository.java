package ir.maktabsharif.repository;

import ir.maktabsharif.domain.StudentQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentQuizRepository extends JpaRepository<StudentQuiz, Long> {
}
