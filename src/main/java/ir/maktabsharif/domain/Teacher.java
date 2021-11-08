package ir.maktabsharif.domain;

import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue("TEACHER")
@Table(name = Teacher.TABLE_NAME)
public class Teacher extends User{
    public static final String TABLE_NAME = "teachers";

    @Builder
    public Teacher(Long id, String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive, List<Course> courses) {
        super(id, username, password, firstName, lastName, email, gender, role, isActive);
        this.courses = courses;
    }

    @OneToMany(mappedBy = "teacher",cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Course> courses = new ArrayList<>();
}
