package ir.maktabsharif.domain;


import ir.maktabsharif.base.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuizQuestion extends BaseEntity<Long> {

    public static final String SCORE = "score";

    @ManyToOne(optional = false)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private BaseQuestion question;

    @Column(name = SCORE)
    private Integer score;

    public QuizQuestion(Quiz quiz, BaseQuestion question) {
        this.quiz = quiz;
        this.question = question;
    }

    @Builder
    public QuizQuestion(Long id, Quiz quiz, BaseQuestion question, Integer score) {
        super(id);
        this.quiz = quiz;
        this.question = question;
        this.score = score;
    }
}
