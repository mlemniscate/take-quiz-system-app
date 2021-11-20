package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.AnswerMapper;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.service.AnswerService;
import ir.maktabsharif.service.dto.AnswerDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController extends BaseRestFul<Answer, AnswerDTO, Long, AnswerService, AnswerMapper> {

    public AnswerController(AnswerService service, AnswerMapper mapper) {
        super(service, mapper);
    }
}
