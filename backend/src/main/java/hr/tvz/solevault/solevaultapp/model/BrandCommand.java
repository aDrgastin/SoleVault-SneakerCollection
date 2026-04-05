package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class BrandCommand {
    @NotBlank(message = "Naziv ne smije biti prazan")
    @Size(max = 50, message = "Naziv može imati najviše {max} znakova")
    private String name;

    @NotBlank(message = "Država ne smije biti prazna")
    @Size(max = 50, message = "Država može imati najviše {max} znakova")
    private String country;

    @NotNull(message = "Datum osnivanja je obavezan")
    @PastOrPresent(message = "Datum osnivanja ne može biti u budućnosti")
    private LocalDate founded;

    @URL(message = "Neispravan format adrese logotipa")
    private String logoUrl;
}
