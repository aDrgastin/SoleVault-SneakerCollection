package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserCommand {
    @NotBlank(message = "Korisničko ime ne smije biti prazno")
    private String username;

    @NotBlank(message = "Lozinka ne smije biti prazna")
    @Size(min = 6, message = "Lozinka mora sadržavati najmanje {min} znakova")
    private String password;

    @NotEmpty(message = "Email ne smije biti prazan")
    @Email
    private String email;
}
