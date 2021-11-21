package ir.maktabsharif.service.impl;

import ir.maktabsharif.base.service.impl.BaseServiceImpl;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.domain.StudentQuiz;
import ir.maktabsharif.repository.QuizRepository;
import ir.maktabsharif.repository.StudentQuizRepository;
import ir.maktabsharif.service.QuizService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Override
    public List<Quiz> findAllStudentActiveQuizzes(Long id) {
        List<StudentQuiz> studentQuizList = studentQuizRepository.findAllByStudent_Id(id);
        List<Quiz> quizzes = new ArrayList<>();
        studentQuizList.forEach(studentQuiz -> {
            if(studentQuiz.getIsActive()) quizzes.add(studentQuiz.getQuiz());
            });
        return quizzes;
    }

    @Override
    public LocalDateTime startStudentQuiz(Long studentId, Long quizId, HttpServletRequest request) {
        HttpSession session = request.getSession();
        LocalDateTime time =  (LocalDateTime) session.getAttribute("startQuizTime" + studentId + quizId);
        Optional<Quiz> quizOptional = repository.findById(quizId);
        if(time == null && quizOptional.isPresent()) {
            session.setAttribute("startQuizTime" + studentId + quizId, LocalDateTime.now());
            return LocalDateTime.now();
        } else return time;
    }
}
