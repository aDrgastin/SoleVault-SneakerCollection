package hr.tvz.solevault.solevaultapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "sneaker_image")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class SneakerImage {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column
    private String tag;

    @Column
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sneaker_id", nullable = false)
    private Sneaker sneaker;
}
