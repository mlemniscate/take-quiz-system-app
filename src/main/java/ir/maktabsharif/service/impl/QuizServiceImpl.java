package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.service.QuizService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz, Long, QuizRepository> implements QuizService {

    public QuizServiceImpl(QuizRepository repository) {
        super(repository);
    }
}
