package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.service.dto.QuizDTO;
import org.mapstruct.Mapper;

@Mapper
public interface QuizMapper extends BaseMapper<Quiz, QuizDTO, Long> {

}
