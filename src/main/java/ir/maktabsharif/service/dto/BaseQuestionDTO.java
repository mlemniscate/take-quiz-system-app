package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BaseQuestionDTO extends BaseDTO<Long> {

    private String question;

}
