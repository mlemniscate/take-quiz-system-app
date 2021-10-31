package ir.maktabsharif.controller.dto;

import ir.maktabsharif.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ReturnUserDTO {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private Role role;
}
