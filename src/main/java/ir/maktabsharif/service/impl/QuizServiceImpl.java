package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.domain.StudentQuiz;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.StudentQuizRepository;
import ir.maktabsharif.service.QuizService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class QuizServiceImpl extends BaseServiceImpl<Quiz, Long, QuizRepository> implements QuizService {

    private final StudentQuizRepository studentQuizRepository;

    public QuizServiceImpl(QuizRepository repository, StudentQuizRepository studentQuizRepository) {
        super(repository);
        this.studentQuizRepository = studentQuizRepository;
    }

    // find all the StudentQuiz of a student by student id
    @Override
    public List<StudentQuiz> findAllStudentQuiz(Long id) {
        return studentQuizRepository.findAllByStudent_Id(id);
    }

    // for starting the student quiz and return the quiz started time
    @Override
    public void startStudentQuiz(Long studentId, Long quizId) {
        Optional<StudentQuiz> studentQuizOptional = studentQuizRepository.findByStudentIdAndQuizId(studentId, quizId);
        if(studentQuizOptional.isPresent()) {
            studentQuizOptional.get().setStartDate(new Date().getTime());
            studentQuizRepository.save(studentQuizOptional.get());
        }
    }

    @Override
    public void endStudentQuiz(StudentQuiz studentQuiz) {
        studentQuiz.setIsActive(false);
        studentQuizRepository.save(studentQuiz);
    }
}
