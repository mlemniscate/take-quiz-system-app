package ir.maktabsharif.service.dto.extra;

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
public class UserWithoutPasswordDTO extends BaseDTO<Long> {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Gender gender;
    private Role role;
    private Boolean isActive;
}
