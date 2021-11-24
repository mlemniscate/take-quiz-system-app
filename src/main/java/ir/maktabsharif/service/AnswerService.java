package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Answer;

import java.util.List;

public interface AnswerService extends BaseService<Answer, Long> {
    Answer saveQuestionAnswer(Answer answer, Long questionId, Long studentId, Long quizId);

    List<Answer> findAllQuizAnswersByStudent(Long studentId, Long quizId);

    void modifyStudentQuiz(Long studentId, Long quizId);
}
