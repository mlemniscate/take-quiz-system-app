package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

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

}
