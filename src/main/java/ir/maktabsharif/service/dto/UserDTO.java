package ir.maktabsharif.service.dto;

import ir.maktabsharif.base.BaseDTO;
import ir.maktabsharif.domain.enums.Gender;
import ir.maktabsharif.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO<Long> {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Role role;
    private Boolean isActive;

}
