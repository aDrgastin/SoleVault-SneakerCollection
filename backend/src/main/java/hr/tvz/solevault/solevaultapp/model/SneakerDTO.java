package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SneakerDTO {
    private Long id;
    private String model;
    private BrandDTO brand;
    private BigDecimal size;
    private String colorway;
    private BigDecimal buyPrice;
    private BigDecimal currentValue;
    private BigDecimal profitLoss;
    private String condition;
}
