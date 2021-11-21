package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.Answer;

public interface AnswerService extends BaseService<Answer, Long> {
    Answer saveQuestionAnswer(Answer answer, Long questionId, Long studentId);
}
