package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Teacher;
import ir.maktabsharif.service.dto.TeacherDTO;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper extends BaseMapper<Teacher, TeacherDTO, Long> {
}
