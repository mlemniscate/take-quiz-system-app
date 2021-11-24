package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.domain.StudentQuiz;

import java.util.List;

public interface QuizService extends BaseService<Quiz, Long> {
    List<StudentQuiz> findAllStudentQuiz(Long id);

    Long startStudentQuiz(Long studentId, Long quizId);

    void endStudentQuiz(StudentQuiz convertDTOToEntity);
}
