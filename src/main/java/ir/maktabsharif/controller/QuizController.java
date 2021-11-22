package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.QuizMapper;
import ir.maktabsharif.controller.mapper.StudentQuizMapper;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.service.QuizService;
import ir.maktabsharif.service.dto.QuizDTO;
import ir.maktabsharif.service.dto.StudentQuizDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController extends BaseRestFul<Quiz, QuizDTO, Long, QuizService, QuizMapper> {

    private final StudentQuizMapper studentQuizMapper;

    public QuizController(QuizService service, QuizMapper mapper, StudentQuizMapper studentQuizMapper) {
        super(service, mapper);
        this.studentQuizMapper = studentQuizMapper;
    }

    // return all the StudentQuiz of a student by id
    @GetMapping("/all-student-quiz/{id}")
    public ResponseEntity<List<StudentQuizDTO>> getAllStudentActiveQuizzes(@PathVariable Long id) {
        return ResponseEntity.ok(
                studentQuizMapper.convertListEntityToDTO(
                        service.findAllStudentQuiz(id)
                )
        );
    }

    // request to start the quiz and get the started time
    // if quiz started before this method give you the last started time
    @GetMapping("/start-student-quiz/{studentId}/{quizId}")
    public ResponseEntity<Void> start(@PathVariable Long studentId, @PathVariable Long quizId) {
        service.startStudentQuiz(studentId, quizId);
        return ResponseEntity.ok().build();
    }


}
