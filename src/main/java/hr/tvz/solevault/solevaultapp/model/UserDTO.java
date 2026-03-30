package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserDTO {
    private String username;
    private String email;
    private String role;
}
