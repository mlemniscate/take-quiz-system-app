package ir.maktabsharif.controller;

import ir.maktabsharif.base.web.rest.BaseRestFul;
import ir.maktabsharif.controller.mapper.BaseQuestionMapper;
import ir.maktabsharif.domain.BaseQuestion;
import ir.maktabsharif.service.BaseQuestionService;
import ir.maktabsharif.service.dto.BaseQuestionDTO;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public class BaseQuestionRestFull<E extends BaseQuestion, D extends BaseQuestionDTO, S extends BaseQuestionService<E>,
        M extends BaseQuestionMapper<E, D>> extends BaseRestFul<E, D, Long, S, M> {

    public BaseQuestionRestFull(S service, M mapper) {
        super(service, mapper);
    }
}
