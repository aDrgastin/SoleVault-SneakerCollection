package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class WantListItemDTO {
    private String model;
    private String brand;
    private BigDecimal targetPrice;
    private LocalDate addedAt;
    private String notes;
}
