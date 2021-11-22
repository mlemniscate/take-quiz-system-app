package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentQuizDTO extends BaseDTO<Long> {

    private Boolean isActive;
    private Integer score;
    private Long startDate;
    private QuizDTO quiz;
    private StudentDTO student;

}
