package ir.maktabsharif.repository;

import ir.maktabsharif.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    List<Answer> findAllByStudentIdAndQuizId(Long studentId, Long quizId);
}
