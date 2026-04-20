package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class BrandDTO {
    private Long id;
    private String name;
    private String country;
    private LocalDate founded;
    private String logoUrl;
}
