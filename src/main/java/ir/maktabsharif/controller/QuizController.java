package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.QuizMapper;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.service.QuizService;
import ir.maktabsharif.service.dto.QuizDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController extends BaseRestFul<Quiz, QuizDTO, Long, QuizService, QuizMapper> {

    public QuizController(QuizService service, QuizMapper mapper) {
        super(service, mapper);
    }

    @GetMapping("/all-student-active-quizzes/{id}")
    public ResponseEntity<List<QuizDTO>> getAllStudentActiveQuizzes(@PathVariable Long id) {
        return ResponseEntity.ok(
                mapper.convertListEntityToDTO(
                        service.findAllStudentActiveQuizzes(id)
                )
        );
    }

}
