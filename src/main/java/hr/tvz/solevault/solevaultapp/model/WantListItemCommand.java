package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class WantListItemCommand {
    @NotBlank(message = "Model je obavezan")
    private String model;

    @NotBlank(message = "Brand je obavezan")
    private String brand;

    @NotNull(message = "Ciljana cijena je obavezna")
    @PositiveOrZero(message = "Ciljana cijena mora biti veća ili jednaka od 0")
    @Digits(integer = 10, fraction = 2, message = "Ciljana cijena može imati najviše {integer} cijelih i {fraction} decimalnih znamenki")
    private BigDecimal targetPrice;

    private String notes;
}
