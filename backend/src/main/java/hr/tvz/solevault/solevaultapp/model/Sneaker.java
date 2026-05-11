package hr.tvz.solevault.solevaultapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sneaker")
@NoArgsConstructor
public class Sneaker {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String model;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    @Column(precision = 4, scale = 2)
    private BigDecimal size;

    @Column
    private String colorway;

    @Column(name = "buy_price", nullable = false)
    private BigDecimal buyPrice;

    @Column(name = "current_value")
    private BigDecimal currentValue;

    @Column(nullable = false)
    private String condition;

    @Column(name = "purchased_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate purchasedAt;

    /*@OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL)
    private List<PriceHistory> priceHistories = new ArrayList<>();*/

    /*@OneToMany(mappedBy = "sneaker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SneakerImage> images = new ArrayList<>();*/
}
