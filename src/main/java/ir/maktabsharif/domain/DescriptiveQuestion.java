package ir.maktabsharif.domain;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue("DESCRIPTIVE")
@Getter
@Setter
@NoArgsConstructor
@Table(name = DescriptiveQuestion.TABLE_NAME)
public class DescriptiveQuestion extends BaseQuestion{

    public static final String TABLE_NAME = "descriptive_questions";

    @Builder
    public DescriptiveQuestion(Long id, String question) {
        super(id, question);
    }
}
