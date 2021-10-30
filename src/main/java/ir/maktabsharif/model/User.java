package ir.maktabsharif.model;

import ir.maktabsharif.model.base.BaseEntity;
import ir.maktabsharif.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = User.TABLE_NAME)
public class User extends BaseEntity<Long> {
    public static final String TABLE_NAME = "users";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String ROLE = "role";

    @Column(name = USERNAME)
    private String username;
    @Column(name = PASSWORD)
    private String password;
    @Column(name = FIRST_NAME)
    private String firstName;
    @Column(name = LAST_NAME)
    private String lastName;
    @Column(name = EMAIL)
    private String email;
    @Column(name = GENDER)
    private String gender;
    @Column(name = ROLE)
    private Role role;


}
