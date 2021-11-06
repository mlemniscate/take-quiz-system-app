package ir.maktabsharif.domain;

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
@Builder
@DiscriminatorValue("ADMIN")
@Table(name = Admin.TABLE_NAME)
public class Admin extends User{
    public static final String TABLE_NAME = "admins";
}
