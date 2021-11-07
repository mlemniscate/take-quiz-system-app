package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.domain.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO extends BaseDTO<Long> {

    private String title;
    private String startDate;
    private String endDate;
    private Teacher teacher;

}
