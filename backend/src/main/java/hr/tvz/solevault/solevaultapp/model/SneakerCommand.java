package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class SneakerCommand {
    @NotBlank(message = "Model ne smije biti prazan")
    @Size(max = 50, message = "Model može imati najviše {max} znakova")
    private String model;

    @NotNull(message = "Brand je obavezan")
    private Long brandId;

    @NotNull(message = "Veličina je obavezna")
    @PositiveOrZero(message = "Veličina mora biti veća ili jednaka od 0")
    @DecimalMin(value = "35.0", message = "Veličina mora biti veća od {value}")
    @DecimalMax(value = "50.0", message = "Veličina ne može biti veća od {value}")
    private BigDecimal size;

    @NotEmpty(message = "Kombinacija boja ne smije biti prazna")
    @Size(max = 50, message = "Kombinacija boja može imati najviše {max} znakova")
    private String colorway;

    @NotNull(message = "Cijena je obavezna")
    @Positive(message = "Cijena mora biti pozitivna")
    @Digits(integer = 10, fraction = 2, message = "Neispravan format cijene (max {integer} + {fraction} dec.)")
    private BigDecimal buyPrice;

    @NotBlank(message = "Stanje ne smije biti prazno")
    @Pattern(regexp = "NEW|USED", message = "Stanje mora biti 'NEW' ili 'USED'")
    private String condition;
}
