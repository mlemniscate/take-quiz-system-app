package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = BaseQuestion.TABLE_NAME)
public class BaseQuestion extends BaseEntity<Long> {
    public static final String TABLE_NAME = "questions";
    public static final String QUESTION = "question";
    public static final String TITLE = "title";

    @Column(name = TITLE, nullable = false)
    private String title;

    @Column(name = QUESTION, nullable = false)
    private String question;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions = new ArrayList<>();

    @Transient
    private Integer score;

    public BaseQuestion(Long id, String title, String question, Integer score) {
        super(id);
        this.title = title;
        this.question = question;
        this.score = score;
    }

    public void addQuizQuestion(QuizQuestion quizQuestion) {
        this.getQuizQuestions().add(quizQuestion);
        quizQuestion.setQuestion(this);
    }
}
