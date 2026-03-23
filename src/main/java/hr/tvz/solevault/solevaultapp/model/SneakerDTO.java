package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
public class SneakerDTO {
    private String model;
    private Brand brand;
    private Integer size;
    private String colorway;
    private BigDecimal buyPrice;
    private BigDecimal currentValue;
    private BigDecimal profitLoss;
}
