package ir.maktabsharif.model;

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
@Table(name = Admin.TABLE_NAME)
public class Admin extends User{
    public static final String TABLE_NAME = "admins";

    @OneToMany
    @JoinColumn(name = "admin_id")
    private List<Course> courses;
}
