package ir.maktabsharif.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = Teacher.TABLE_NAME)
public class Teacher extends User{
    public static final String TABLE_NAME = "teachers";

    @OneToMany
    @JoinColumn(name = "teacher_id")
    private List<Course> courses;
}
