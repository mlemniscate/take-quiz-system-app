package ir.maktabsharif.repository;

import ir.maktabsharif.domain.BaseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BaseQuestionRepository<E extends BaseQuestion> extends JpaRepository<E, Long> {
    List<E> findByCourseIdAndTeacherId(Long courseId, Long teacherId);
}
