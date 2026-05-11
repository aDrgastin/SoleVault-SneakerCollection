package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.*;
import hr.tvz.solevault.solevaultapp.repository.BrandJpaRepository;
import hr.tvz.solevault.solevaultapp.repository.SneakerJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SneakerServiceImpl implements SneakerService {
    private final SneakerJpaRepository sneakerRepository;
    private final BrandJpaRepository brandRepository;

    @Override
    @Transactional(readOnly = true)
    public List<SneakerDTO> findAll() {
        return sneakerRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SneakerDTO> findById(Long id) {
        return sneakerRepository.findById(id).map(this::toDTO);
    }

    @Override
    public Optional<SneakerDTO> findByModel(String val) {
        return sneakerRepository.findByModel(val).map(this::toDTO);
    }

    @Override
    public List<SneakerDTO> findByColorway(String colorway) {
        return sneakerRepository.findByColorway(colorway).stream().map(this::toDTO).toList();
    }

    @Override
    public Optional<SneakerDTO> addSneaker(SneakerCommand sneakerCommand) {
        Brand brand = brandRepository.findById(sneakerCommand.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found with id: " + sneakerCommand.getBrandId()));
        Sneaker newSneaker = new Sneaker(null, sneakerCommand.getModel(), brand, sneakerCommand.getSize(), sneakerCommand.getColorway(), sneakerCommand.getBuyPrice(), sneakerCommand.getBuyPrice(), sneakerCommand.getCondition(), LocalDate.now());
        return Optional.of(toDTO(sneakerRepository.save(newSneaker)));
    }

    @Override
    @Transactional
    public Optional<SneakerDTO> updateSneaker(Long id, SneakerCommand sneakerCommand) {
        Optional<Sneaker> oldSneaker = sneakerRepository.findById(id);
        if (oldSneaker.isEmpty()) {
            return Optional.empty();
        }
        Brand brand = brandRepository.findById(sneakerCommand.getBrandId()).orElseThrow(() -> new RuntimeException("Brand not found with id: " + sneakerCommand.getBrandId()));
        Sneaker updated = new Sneaker(id, sneakerCommand.getModel(), brand, sneakerCommand.getSize(), sneakerCommand.getColorway(), sneakerCommand.getBuyPrice(), oldSneaker.get().getCurrentValue(), sneakerCommand.getCondition(), oldSneaker.get().getPurchasedAt());
        return Optional.of(toDTO(sneakerRepository.save(updated)));
    }

    @Override
    public boolean deleteSneaker(String model) {
        try {
            sneakerRepository.deleteByModel(model);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    @Override
    public boolean deleteSneaker(Long id) {
        try {
            sneakerRepository.deleteById(id);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    private SneakerDTO toDTO(Sneaker s) {
        Brand b = s.getBrand();
        BrandDTO brandDTO = new BrandDTO(b.getId(), b.getName(), b.getCountry(), b.getFounded(), b.getLogoUrl());
        return new SneakerDTO(s.getId(), s.getModel(), brandDTO, s.getSize(), s.getColorway(), s.getBuyPrice(), s.getCurrentValue(), s.getCurrentValue().subtract(s.getBuyPrice()), s.getCondition());
    }
}
