package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandRepository {
    List<Brand> findAll();

    Optional<Brand> findById(Long id);

    Optional<Brand> addBrand(Brand brand);

    Optional<Brand> updateBrand(Brand brand);

    int deleteBrand(Long id);
}
