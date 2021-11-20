package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Quiz;

import java.util.List;

public interface QuizService extends BaseService<Quiz, Long> {
    List<Quiz> findAllStudentActiveQuizzes(Long id);
}
