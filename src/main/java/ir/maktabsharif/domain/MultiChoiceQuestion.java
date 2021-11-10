package ir.maktabsharif.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("MULTI")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = MultiChoiceQuestion.TABLE_NAME)
public class MultiChoiceQuestion extends BaseQuestion{
    public static final String TABLE_NAME = "multi_choice_questions";
    public static final String TRUE_OPTION = "true_option";

    @Column(name = TRUE_OPTION)
    private Integer trueOption;

    @ElementCollection
    private List<String> options;

    @Builder
    public MultiChoiceQuestion(Long id, String question, Integer trueOption, List<String> options) {
        super(id, question);
        this.trueOption = trueOption;
        this.options = options;
    }
}
