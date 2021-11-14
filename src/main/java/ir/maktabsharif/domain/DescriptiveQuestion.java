package ir.maktabsharif.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    public DescriptiveQuestion(Long id, String title, String question, Integer score) {
        super(id, title, question, score);
    }
}
