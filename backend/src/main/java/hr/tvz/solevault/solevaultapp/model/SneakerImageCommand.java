package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SneakerImageCommand {
    @NotBlank(message = "URL slike ne smije biti prazan")
    private String url;

    @Size(max = 55, message = "Tag ne može biti dulji od {max} znakova")
    private String tag;

    @Size(max = 255, message = "Opis ne može biti dulji od {max} znakova")
    private String description;

    @NotNull(message = "Pripadna tenisica je obavezna")
    private Long sneakerId;
}
