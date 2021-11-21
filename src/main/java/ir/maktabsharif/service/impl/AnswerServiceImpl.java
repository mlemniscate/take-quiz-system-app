package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.domain.DescriptiveQuestion;
import ir.maktabsharif.domain.MultiChoiceQuestion;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.repository.AnswerRepository;
import ir.maktabsharif.repository.BaseQuestionRepository;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Long, AnswerRepository> implements AnswerService {

    private final BaseQuestionRepository baseQuestionRepository;
    private final StudentRepository studentRepository;

    public AnswerServiceImpl(AnswerRepository repository, BaseQuestionRepository baseQuestionRepository, StudentRepository studentRepository) {
        super(repository);
        this.baseQuestionRepository = baseQuestionRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Answer saveQuestionAnswer(Answer answer, Long questionId, Long studentId) {
        Optional baseQuestionOptional = baseQuestionRepository.findById(questionId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(baseQuestionOptional.isPresent() && studentOptional.isPresent()) {
            if(baseQuestionOptional.get() instanceof MultiChoiceQuestion)
                answer.setQuestion((MultiChoiceQuestion) baseQuestionOptional.get());
            else answer.setQuestion((DescriptiveQuestion) baseQuestionOptional.get());
            answer.setStudent(studentOptional.get());
            return repository.save(answer);
        } else return null;
    }
}
