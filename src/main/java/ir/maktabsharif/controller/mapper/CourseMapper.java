package ir.maktabsharif.controller.mapper;

import ir.maktabsharif.base.mapper.BaseMapper;
import ir.maktabsharif.domain.Course;
import ir.maktabsharif.service.dto.CourseDTO;
import ir.maktabsharif.service.dto.extra.CourseWithoutStudentsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper extends BaseMapper<Course, CourseDTO, Long> {

    CourseWithoutStudentsDTO convertEntityToCourseWithoutStudentDTO(Course course);
    List<CourseWithoutStudentsDTO> convertListEntityToCourseWithoutStudentDTO(List<Course> courses);

}
