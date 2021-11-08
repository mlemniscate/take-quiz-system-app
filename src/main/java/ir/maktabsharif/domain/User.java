package ir.maktabsharif.domain;

import ir.maktabsharif.base.BaseEntity;
import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type",
        discriminatorType = DiscriminatorType.STRING)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
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
    public static final String IS_ACTIVE = "is_active";

    @Column(name = USERNAME, nullable = false, unique = true)
    private String username;

    @Column(name = PASSWORD)
    private String password;

    @Column(name = FIRST_NAME)
    private String firstName;

    @Column(name = LAST_NAME)
    private String lastName;

    @Column(name = EMAIL, nullable = false, unique = true)
    private String email;

    @Column(name = GENDER)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = ROLE)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = IS_ACTIVE)
    private Boolean isActive = false;

    public User(Long id, String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive) {
        super(id);
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.role = role;
        this.isActive = isActive;
    }
}
