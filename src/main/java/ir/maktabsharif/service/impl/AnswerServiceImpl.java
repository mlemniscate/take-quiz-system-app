package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.*;
import ir.maktabsharif.repository.AnswerRepository;
import ir.maktabsharif.repository.BaseQuestionRepository;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.StudentRepository;
import ir.maktabsharif.service.AnswerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerServiceImpl extends BaseServiceImpl<Answer, Long, AnswerRepository> implements AnswerService {

    private final BaseQuestionRepository baseQuestionRepository;
    private final StudentRepository studentRepository;
    private final QuizRepository quizRepository;

    public AnswerServiceImpl(AnswerRepository repository, BaseQuestionRepository baseQuestionRepository, StudentRepository studentRepository, QuizRepository quizRepository) {
        super(repository);
        this.baseQuestionRepository = baseQuestionRepository;
        this.studentRepository = studentRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public Answer saveQuestionAnswer(Answer answer, Long questionId, Long studentId, Long quizId) {
        Optional baseQuestionOptional = baseQuestionRepository.findById(questionId);
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Quiz> quizOptional = quizRepository.findById(quizId);
        if(baseQuestionOptional.isPresent() && studentOptional.isPresent() && quizOptional.isPresent()) {
            if(baseQuestionOptional.get() instanceof MultiChoiceQuestion)
                answer.setQuestion((MultiChoiceQuestion) baseQuestionOptional.get());
            else answer.setQuestion((DescriptiveQuestion) baseQuestionOptional.get());
            answer.setStudent(studentOptional.get());
            answer.setQuiz(quizOptional.get());
            return repository.save(answer);
        } else return null;
    }

    @Override
    public List<Answer> findAllQuizAnswersByStudent(Long studentId, Long quizId) {
       return repository.findAllByStudentIdAndQuizId(studentId, quizId);
    }
}
