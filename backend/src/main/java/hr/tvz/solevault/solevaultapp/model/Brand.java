package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
public class Brand {
    private Long id;
    private String name;
    private String country;
    private LocalDate founded;
    private String logoUrl;
}
