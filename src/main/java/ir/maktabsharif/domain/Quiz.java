package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = Quiz.TABLE_NAME)
public class Quiz extends BaseEntity<Long> {

    public static final String TABLE_NAME = "quizzes";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String TIME = "time";

    @Column(name = TITLE)
    private String title;

    @Column(name = DESCRIPTION)
    private String description;

    @Column(name = TIME)
    private Integer time;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL)
    private List<QuizQuestion> quizQuestions = new ArrayList<>();

    @Transient
    private Integer totalScore;

    @Builder
    public Quiz(Long id, String title, String description, Integer time, Course course, List<QuizQuestion> quizQuestions, Integer totalScore) {
        super(id);
        this.title = title;
        this.description = description;
        this.time = time;
        this.course = course;
        this.quizQuestions = quizQuestions;
        this.totalScore = totalScore;
    }

    public void addQuizQuestion(QuizQuestion quizQuestion) {
        quizQuestions.add(quizQuestion);
        quizQuestion.setQuiz(this);
    }
}
