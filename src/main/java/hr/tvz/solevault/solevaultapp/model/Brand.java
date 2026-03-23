package hr.tvz.solevault.solevaultapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class Brand {
    private Long id;
    private String name;
    private String country;
    private Date founded;
    private String logoUrl;
}
