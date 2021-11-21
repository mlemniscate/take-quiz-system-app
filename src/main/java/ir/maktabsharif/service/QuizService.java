package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Quiz;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

public interface QuizService extends BaseService<Quiz, Long> {
    List<Quiz> findAllStudentActiveQuizzes(Long id);

    LocalDateTime startStudentQuiz(Long studentId, Long quizId, HttpServletRequest request);
}
