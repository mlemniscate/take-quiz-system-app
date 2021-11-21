package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnswerDTO extends BaseDTO<Long> {

    private String answer;
    private Integer score;

}
