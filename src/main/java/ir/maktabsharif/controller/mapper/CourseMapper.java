package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.service.dto.CourseDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper extends BaseMapper<Course, CourseDTO, Long> {

}
