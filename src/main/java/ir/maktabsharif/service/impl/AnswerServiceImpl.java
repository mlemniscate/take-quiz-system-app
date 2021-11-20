package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.repository.AnswerRepository;
import ir.maktabsharif.service.AnswerService;
import org.springframework.stereotype.Service;

@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Long, AnswerRepository> implements AnswerService {

    public AnswerServiceImpl(AnswerRepository repository) {
        super(repository);
    }
}
