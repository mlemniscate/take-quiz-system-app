package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Answer;
import ir.maktabsharif.service.dto.AnswerDTO;
import org.mapstruct.Mapper;

@Mapper
public interface AnswerMapper extends BaseMapper<Answer, AnswerDTO, Long> {
}
