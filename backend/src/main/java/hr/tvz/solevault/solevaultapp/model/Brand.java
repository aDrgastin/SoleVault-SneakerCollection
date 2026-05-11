package hr.tvz.solevault.solevaultapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "brand")
@NoArgsConstructor
public class Brand {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private LocalDate founded;

    @Column(name = "logo_url")
    private String logoUrl;
}
