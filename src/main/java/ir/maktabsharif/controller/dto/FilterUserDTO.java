package ir.maktabsharif.controller.dto;

import ir.maktabsharif.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class FilterUserDTO {
    private String firstName;
    private String lastName;
    private String gender;
    private Role role;
}
