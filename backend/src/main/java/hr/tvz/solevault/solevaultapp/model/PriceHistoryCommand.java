package hr.tvz.solevault.solevaultapp.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
public class PriceHistoryCommand {
    @NotNull(message = "Tenisica je obavezna")
    private Long sneakerId;

    @NotNull(message = "Cijena je obavezna")
    @Positive(message = "Cijena mora biti pozitivna")
    private BigDecimal price;

    @NotBlank(message = "Izvor ne može biti prazan")
    private String source;
}
