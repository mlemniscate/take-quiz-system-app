package ir.maktabsharif.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MultiChoiceQuestionDTO extends BaseQuestionDTO{

    private Integer trueOption;
    private List<String> options;

}
