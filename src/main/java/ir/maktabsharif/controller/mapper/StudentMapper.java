package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Student;
import ir.maktabsharif.service.dto.StudentDTO;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper extends BaseMapper<Student, StudentDTO, Long> {
}
