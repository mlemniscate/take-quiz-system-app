package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Table(name = Answer.TABLE_NAME)
public class Answer extends BaseEntity<Long> {

    public static final String TABLE_NAME = "answers";
    public static final String ANSWER = "answer";
    public static final String SCORE = "score";

    @Column(name = ANSWER)
    private String answer;
    @Column(name = SCORE)
    private Integer score;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "question_id")
    private BaseQuestion question;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
}
