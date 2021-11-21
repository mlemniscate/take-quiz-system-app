package ir.maktabsharif.controller;

import ir.maktabsharif.controller.mapper.MultiChoiceQuestionMapper;
import ir.maktabsharif.domain.MultiChoiceQuestion;
import ir.maktabsharif.service.MultiChoiceQuestionService;
import ir.maktabsharif.service.dto.MultiChoiceQuestionDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multi-choice-question")
@CrossOrigin
public class MultiChoiceQuestionRestFull extends BaseQuestionRestFull<MultiChoiceQuestion,
        MultiChoiceQuestionDTO, MultiChoiceQuestionService, MultiChoiceQuestionMapper> {

    public MultiChoiceQuestionRestFull(MultiChoiceQuestionService service, MultiChoiceQuestionMapper mapper) {
        super(service, mapper);
    }
}
