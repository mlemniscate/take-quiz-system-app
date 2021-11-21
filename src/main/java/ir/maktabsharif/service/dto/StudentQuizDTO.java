package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.domain.Quiz;
import ir.maktabsharif.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentQuizDTO extends BaseDTO<Long> {

    private Boolean isActive;
    private Integer score;
    private Quiz quiz;
    private Student student;

}
