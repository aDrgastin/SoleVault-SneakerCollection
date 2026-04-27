package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.BrandCommand;
import hr.tvz.solevault.solevaultapp.model.BrandDTO;
import hr.tvz.solevault.solevaultapp.model.SneakerCommand;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<BrandDTO> findAll();

    Optional<BrandDTO> findById(Long id);

    Optional<BrandDTO> updateBrand(Long id, BrandCommand brandCommand);

    Optional<BrandDTO> addBrand(BrandCommand brandCommand);

    boolean deleteBrand(Long id);
}
