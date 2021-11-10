package ir.maktabsharif.service.impl;

import ir.maktabsharif.domain.MultiChoiceQuestion;
import ir.maktabsharif.repository.MultiChoiceQuestionRepository;
import ir.maktabsharif.service.MultiChoiceQuestionService;
import org.springframework.stereotype.Service;

@Service
public class MultiChoiceQuestionServiceImpl extends BaseQuestionServiceImpl<MultiChoiceQuestion, MultiChoiceQuestionRepository> implements MultiChoiceQuestionService {

    public MultiChoiceQuestionServiceImpl(MultiChoiceQuestionRepository repository) {
        super(repository);
    }
}
