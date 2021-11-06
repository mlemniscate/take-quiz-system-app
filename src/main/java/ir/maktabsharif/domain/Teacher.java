package ir.maktabsharif.domain;

import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@DiscriminatorValue("TEACHER")
@Table(name = Teacher.TABLE_NAME)
public class Teacher extends User{
    public static final String TABLE_NAME = "teachers";

    public Teacher(String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive) {
        super(username, password, firstName, lastName, email, gender, role, isActive);
    }

    @OneToMany
    @JoinColumn(name = "teacher_id")
    private List<Course> courses;
}
