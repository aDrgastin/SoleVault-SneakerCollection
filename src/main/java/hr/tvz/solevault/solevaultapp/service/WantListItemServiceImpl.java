package hr.tvz.solevault.solevaultapp.service;

import hr.tvz.solevault.solevaultapp.model.WantListItem;
import hr.tvz.solevault.solevaultapp.model.WantListItemCommand;
import hr.tvz.solevault.solevaultapp.model.WantListItemDTO;
import hr.tvz.solevault.solevaultapp.repository.WantListItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class WantListItemServiceImpl implements WantListItemService {
    private final WantListItemRepository wantListItemRepository;

    @Override
    public List<WantListItemDTO> findAll() {
        return wantListItemRepository.findAll().stream().map(this::toDTO).toList();
    }

    @Override
    public Optional<WantListItemDTO> findById(Long id) {
        return wantListItemRepository.findById(id).map(this::toDTO);
    }

    @Override
    public Optional<WantListItemDTO> addWantItem(WantListItemCommand wantItemCommand) {
        WantListItem newWantItem = new WantListItem(wantListItemRepository.findAll().size() + 1L, wantItemCommand.getModel(), wantItemCommand.getBrand(), wantItemCommand.getTargetPrice(), LocalDate.now(), wantItemCommand.getNotes());
        Optional<WantListItem> created = wantListItemRepository.addWantItem(newWantItem);
        return created.map(this::toDTO);
    }

    private WantListItemDTO toDTO(WantListItem wantListItem) {
        return new WantListItemDTO(wantListItem.getModel(), wantListItem.getBrand(), wantListItem.getTargetPrice(), wantListItem.getAddedAt(), wantListItem.getNotes());
    }
}
