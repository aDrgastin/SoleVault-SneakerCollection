package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.Brand;
import hr.tvz.solevault.solevaultapp.model.BrandCommand;
import hr.tvz.solevault.solevaultapp.model.BrandDTO;
import hr.tvz.solevault.solevaultapp.repository.BrandRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public Optional<BrandDTO> findById(Long id) {
        return brandRepository.findById(id).map(this::toDto);
    }

    @Override
    public Optional<BrandDTO> add(BrandCommand brandCommand) {
        Brand newBrand = new Brand(brandRepository.findAll().size() + 1L, brandCommand.getName(), brandCommand.getCountry(), brandCommand.getFounded(), brandCommand.getLogoUrl());
        Optional<Brand> created = brandRepository.addBrand(newBrand);
        return created.map(this::toDto);
    }

    private BrandDTO toDto(Brand brand) {
        return new BrandDTO(brand.getId(), brand.getName(), brand.getCountry(), brand.getFounded(), brand.getLogoUrl());
    }
}
