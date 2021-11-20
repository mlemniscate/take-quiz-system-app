package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.domain.StudentQuiz;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.StudentQuizRepository;
import ir.maktabsharif.service.QuizService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz, Long, QuizRepository> implements QuizService {

    private final StudentQuizRepository studentQuizRepository;

    public QuizServiceImpl(QuizRepository repository, StudentQuizRepository studentQuizRepository) {
        super(repository);
        this.studentQuizRepository = studentQuizRepository;
    }

    @Override
    public List<Quiz> findAllStudentActiveQuizzes(Long id) {
        List<StudentQuiz> studentQuizList = studentQuizRepository.findAllByStudent_Id(id);
        List<Quiz> quizzes = new ArrayList<>();
        studentQuizList.forEach(studentQuiz -> {
            if(studentQuiz.getIsActive()) quizzes.add(studentQuiz.getQuiz());
            });
        return quizzes;
    }
}
