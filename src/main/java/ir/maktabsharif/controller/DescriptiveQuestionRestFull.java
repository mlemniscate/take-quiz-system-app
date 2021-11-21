package ir.maktabsharif.controller;

import ir.maktabsharif.controller.mapper.DescriptiveQuestionMapper;
import ir.maktabsharif.domain.DescriptiveQuestion;
import ir.maktabsharif.service.DescriptiveQuestionService;
import ir.maktabsharif.service.dto.DescriptiveQuestionDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/descriptive-question")
@CrossOrigin
public class DescriptiveQuestionRestFull extends BaseQuestionRestFull<DescriptiveQuestion,
        DescriptiveQuestionDTO, DescriptiveQuestionService, DescriptiveQuestionMapper> {

    public DescriptiveQuestionRestFull(DescriptiveQuestionService service, DescriptiveQuestionMapper mapper) {
        super(service, mapper);
    }
}
