package ir.maktabsharif.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder
@Table(name = Admin.TABLE_NAME)
public class Admin extends User{
    public static final String TABLE_NAME = "admins";
}
