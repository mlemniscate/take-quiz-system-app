package ir.maktabsharif.domain;

import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("ADMIN")
@Table(name = Admin.TABLE_NAME)
public class Admin extends User{
    public static final String TABLE_NAME = "admins";

    @Builder
    public Admin(Long id, String username, String password, String firstName, String lastName, String email, Gender gender, Role role, Boolean isActive) {
        super(id, username, password, firstName, lastName, email, gender, role, isActive);
    }
}
