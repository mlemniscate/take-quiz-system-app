package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.domain.MultiChoiceQuestion;
import ir.maktabsharif.service.dto.MultiChoiceQuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface MultiChoiceQuestionMapper extends BaseQuestionMapper<MultiChoiceQuestion, MultiChoiceQuestionDTO> {

}
