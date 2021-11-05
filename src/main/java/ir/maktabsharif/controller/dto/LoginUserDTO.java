package ir.maktabsharif.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginUserDTO {
    private String username;
    private String password;
}
