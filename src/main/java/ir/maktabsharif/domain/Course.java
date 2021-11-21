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
@Table(name = Course.TABLE_NAME)
public class Course extends BaseEntity<Long> {
    public static final String TABLE_NAME = "courses";
    public static final String TITLE = "title";
    public static final String DESCRIPTION = "description";
    public static final String HOURS = "hours";
    public static final String START_DATE = "start_date";
    public static final String END_DATE = "end_date";

    @Column(name = TITLE)
    private String title;

    @Column(name = START_DATE)
    private String startDate;

    @Column(name = END_DATE)
    private String endDate;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> students = new ArrayList<>();

    @OneToMany(mappedBy = "course")
    private List<Quiz> quizzes;

    @Builder
    public Course(Long id, String title, String startDate, String endDate, Teacher teacher, List<Student> students) {
        super(id);
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.teacher = teacher;
        this.students = students;
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
        quiz.setCourse(this);
    }
}
