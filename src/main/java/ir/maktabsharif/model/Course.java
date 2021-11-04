package ir.maktabsharif.model;

import ir.maktabsharif.model.base.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}
