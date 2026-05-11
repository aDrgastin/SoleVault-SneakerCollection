package hr.tvz.solevault.solevaultapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PriceHistoryDTO(
        Long id,
        Long sneakerId,
        BigDecimal price,
        LocalDateTime recordedAt,
        String source
) { }
