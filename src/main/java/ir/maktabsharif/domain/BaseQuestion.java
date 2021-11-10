package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseQuestion extends BaseEntity<Long> {

    public static final String QUESTION = "question";

    @Column(name = QUESTION)
    private String question;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;



    public BaseQuestion(Long id, String question) {
        super(id);
        this.question = question;
    }
}
