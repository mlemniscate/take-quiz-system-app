package ir.maktabsharif.service;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.domain.BaseQuestion;

import java.util.List;

public interface BaseQuestionService<E extends BaseQuestion> extends BaseService<E, Long> {
    List<E> findByCourseIdAndTeacherId(Long courseId, Long teacherId);

    E saveNotSecure(E question, Long courseId, Long teacherId, Long quizId);

    List<E> findAllByQuizId(Long quizId);

    void deleteNotSecure(Long quizId, E question);
}
