package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class WantListItem {
    private Long id;
    private String model;
    private String brand;
    private BigDecimal targetPrice;
    private LocalDate addedAt;
    private String notes;
}
