package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Brand;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class MockBrandRepository implements BrandRepository {
    private Brand brand1 = new Brand(1L, "Nike", "USA", LocalDate.of(1955, 4, 3), "");
    private Brand brand2 = new Brand(2L, "Adidas", "Germany", LocalDate.of(1930, 6, 21), "");
    private Brand brand3 = new Brand(3L, "Puma", "Germany", LocalDate.of(1955, 4, 3), "");
    List<Brand> brands = List.of(brand1, brand2, brand3);

    @Override
    public List<Brand> findAll() {
        return brands;
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return brands.stream().filter(brand -> brand.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<Brand> addBrand(Brand brand) {
        if (brands.stream().anyMatch(b -> b.getName().equals(brand.getName()))) {
            return Optional.empty();
        }
        Brand newBrand = new Brand(brands.size() + 1L, brand.getName(), brand.getCountry(), brand.getFounded(), brand.getLogoUrl());
        brands = Stream.concat(brands.stream(), Stream.of(newBrand)).toList();
        return Optional.of(newBrand);
    }

    @Override
    public Optional<Brand> updateBrand(Brand brand) {
        return Optional.empty();
    }

    @Override
    public int deleteBrand(Long id) {
        return 0;
    }
}
