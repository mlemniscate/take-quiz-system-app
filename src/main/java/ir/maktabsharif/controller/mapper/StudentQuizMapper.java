package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.StudentQuiz;
import ir.maktabsharif.service.dto.StudentQuizDTO;
import org.mapstruct.Mapper;

@Mapper
public interface StudentQuizMapper extends BaseMapper<StudentQuiz, StudentQuizDTO, Long> {

}
