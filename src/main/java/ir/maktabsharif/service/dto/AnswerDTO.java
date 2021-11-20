package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.domain.BaseQuestion;
import ir.maktabsharif.domain.Student;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO extends BaseDTO<Long> {

    private String answer;
    private Integer score;
    private Student student;
    private BaseQuestion question;

}
