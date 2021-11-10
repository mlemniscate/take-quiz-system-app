package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.BaseQuestion;
import ir.maktabsharif.service.dto.BaseQuestionDTO;

public interface BaseQuestionMapper<E extends BaseQuestion, D extends BaseQuestionDTO> extends BaseMapper<E, D, Long> {

}
