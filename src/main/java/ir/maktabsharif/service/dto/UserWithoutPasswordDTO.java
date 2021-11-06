package ir.maktabsharif.service.dto;

import ir.maktabsharif.domain.enums.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserWithoutPasswordDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Role role;
    private Boolean isActive;
}
