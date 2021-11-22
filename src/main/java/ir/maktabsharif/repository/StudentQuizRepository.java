package ir.maktabsharif.repository;

import ir.maktabsharif.domain.StudentQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentQuizRepository extends JpaRepository<StudentQuiz, Long> {

    List<StudentQuiz> findAllByStudent_Id(Long id);

    Optional<StudentQuiz> findByStudentIdAndQuizId(Long studentId, Long quizId);

}
