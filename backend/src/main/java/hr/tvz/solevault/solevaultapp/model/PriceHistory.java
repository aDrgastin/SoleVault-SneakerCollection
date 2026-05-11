package hr.tvz.solevault.solevaultapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "price_history")
@NoArgsConstructor
public class PriceHistory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sneaker_id", nullable = false)
    private Sneaker sneaker;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "recorded_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime recordedAt;

    @Column
    private String source;
}
