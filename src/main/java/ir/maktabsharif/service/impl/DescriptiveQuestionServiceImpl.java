package ir.maktabsharif.service.impl;

import ir.maktabsharif.domain.DescriptiveQuestion;
import ir.maktabsharif.repository.DescriptiveQuestionRepository;
import ir.maktabsharif.service.DescriptiveQuestionService;
import org.springframework.stereotype.Service;

@Service
public class DescriptiveQuestionServiceImpl extends BaseQuestionServiceImpl<DescriptiveQuestion, DescriptiveQuestionRepository> implements DescriptiveQuestionService {

    public DescriptiveQuestionServiceImpl(DescriptiveQuestionRepository repository) {
        super(repository);
    }
}
