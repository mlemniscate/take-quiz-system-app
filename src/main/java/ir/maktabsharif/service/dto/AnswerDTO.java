package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.service.dto.extra.BaseQuestionIdDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO extends BaseDTO<Long> {

    private String answer;
    private Integer score;
    private BaseQuestionIdDTO question;

}
