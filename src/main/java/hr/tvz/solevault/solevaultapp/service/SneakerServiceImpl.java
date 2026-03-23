package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.Sneaker;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;
import hr.tvz.solevault.solevaultapp.repository.SneakerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SneakerServiceImpl implements SneakerService {
    private SneakerRepository sneakerRepository;

    /*public SneakerServiceImpl(SneakerRepository sneakerRepository) {
        this.sneakerRepository = sneakerRepository;
    }*/

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
    public SneakerDTO addSneaker(SneakerDTO sneaker) {
        return toDTO(sneakerRepository.addSneaker(sneaker));
    }

    @Override
    public void deleteSneaker(Long id) {
        sneakerRepository.deleteSneaker(id);
    }

    private SneakerDTO toDTO(Sneaker s) {
        return new SneakerDTO(s.getModel(), null, s.getSize(), s.getColorway(), s.getBuyPrice(), s.getCurrentValue(), s.getCurrentValue().subtract(s.getBuyPrice()));
    }
}
