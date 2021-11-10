package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.BaseService;
import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.BaseQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

public class BaseQuestionServiceImpl<E extends BaseQuestion, R extends JpaRepository<E, Long>>
        extends BaseServiceImpl<E, Long, R> implements BaseService<E, Long> {

    public BaseQuestionServiceImpl(R repository) {
        super(repository);
    }
}
