package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class Sneaker {
    private Long id;
    private String model;
    private Long brand_id;
    private BigDecimal size;
    private String colorway;
    private BigDecimal buyPrice;
    private BigDecimal currentValue;
    private String condition;
    private LocalDate purchasedAt;
}
