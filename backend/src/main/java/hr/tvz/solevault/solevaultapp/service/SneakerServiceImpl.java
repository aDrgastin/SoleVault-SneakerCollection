package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.*;
import hr.tvz.solevault.solevaultapp.repository.BrandRepository;
import hr.tvz.solevault.solevaultapp.repository.SneakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SneakerServiceImpl implements SneakerService {
    private final SneakerRepository sneakerRepository;
    private final BrandRepository brandRepository;

    @Override
    public List<SneakerDTO> findAll() {
        return sneakerRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
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
        Sneaker newSneaker = new Sneaker(sneakerRepository.findAll().size() + 1L, sneakerCommand.getModel(), sneakerCommand.getBrandId(), sneakerCommand.getSize(), sneakerCommand.getColorway(), sneakerCommand.getBuyPrice(), sneakerCommand.getBuyPrice(), sneakerCommand.getCondition(), LocalDate.now());
        Optional<Sneaker> created = sneakerRepository.addSneaker(newSneaker);
        return created.map(this::toDTO);
    }

    @Override
    public Optional<SneakerDTO> updateSneaker(Long id, SneakerCommand sneakerCommand) {
        Optional<Sneaker> oldSneaker = sneakerRepository.findById(id);
        if (oldSneaker.isEmpty()) {
            return Optional.empty();
        }
        Sneaker updated = new Sneaker(id, sneakerCommand.getModel(), sneakerCommand.getBrandId(), sneakerCommand.getSize(), sneakerCommand.getColorway(), sneakerCommand.getBuyPrice(), oldSneaker.get().getCurrentValue(), sneakerCommand.getCondition(), oldSneaker.get().getPurchasedAt());
        return sneakerRepository.updateSneaker(updated).map(this::toDTO);
    }

    @Override
    public boolean deleteSneaker(String model) {
        return sneakerRepository.deleteSneaker(model);
    }

    @Override
    public boolean deleteSneaker(Long id) {
        return sneakerRepository.deleteSneaker(id);
    }

    private SneakerDTO toDTO(Sneaker s) {
        BrandDTO brandDTO = brandRepository.findById(s.getBrand_id())
                .map(b -> new BrandDTO(b.getId(), b.getName(), b.getCountry(), b.getFounded(), b.getLogoUrl()))
                .orElse(null);
        return new SneakerDTO(s.getId(), s.getModel(), brandDTO, s.getSize(), s.getColorway(), s.getBuyPrice(), s.getCurrentValue(), s.getCurrentValue().subtract(s.getBuyPrice()), s.getCondition());
    }
}
