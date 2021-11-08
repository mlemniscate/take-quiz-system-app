package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.QuizMapper;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.service.QuizService;
import ir.maktabsharif.service.dto.QuizDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/quiz")
@CrossOrigin
public class QuizController extends BaseRestFul<Quiz, QuizDTO, Long, QuizService, QuizMapper> {

    public QuizController(QuizService service, QuizMapper mapper) {
        super(service, mapper);
    }
}
