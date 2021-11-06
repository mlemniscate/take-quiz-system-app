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
@DiscriminatorValue("STUDENT")
@Table(name = Student.TABLE_NAME)
public class Student extends User {
    public static final String TABLE_NAME = "students";

    public Student(String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive) {
        super(username, password, firstName, lastName, email, gender, role, isActive);
    }

    @ManyToMany
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;
}
