package ir.maktabsharif.service.dto.extra;

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
    private String role;
    private Boolean isActive;
}
