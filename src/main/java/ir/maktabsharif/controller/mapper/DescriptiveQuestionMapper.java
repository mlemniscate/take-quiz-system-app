package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.domain.DescriptiveQuestion;
import ir.maktabsharif.service.dto.DescriptiveQuestionDTO;
import org.mapstruct.Mapper;

@Mapper
public interface DescriptiveQuestionMapper extends BaseQuestionMapper<DescriptiveQuestion, DescriptiveQuestionDTO> {

}
