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
@DiscriminatorValue("STUDENT")
@Table(name = Student.TABLE_NAME)
public class Student extends User {
    public static final String TABLE_NAME = "students";

    @Builder
    public Student(Long id, String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive, List<Course> courses) {
        super(id, username, password, firstName, lastName, email, gender, role, isActive);
        this.courses = courses;
    }

    @ManyToMany(mappedBy = "students")
    private List<Course> courses = new ArrayList<>();
}
