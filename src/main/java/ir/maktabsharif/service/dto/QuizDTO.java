package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO extends BaseDTO<Long> {

    private String title;
    private String description;
    private Integer time;
}
