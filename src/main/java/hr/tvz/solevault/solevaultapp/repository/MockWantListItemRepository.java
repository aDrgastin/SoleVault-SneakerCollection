package hr.tvz.solevault.solevaultapp.repository;

import hr.tvz.solevault.solevaultapp.model.WantListItem;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class MockWantListItemRepository implements WantListItemRepository {
    private WantListItem wli1 = new WantListItem(1L, "AirForce", "Nike", BigDecimal.valueOf(46), LocalDate.now(), "tražim popust");
    private List<WantListItem> wantItems = List.of(wli1);

    @Override
    public List<WantListItem> findAll() {
        return wantItems;
    }

    @Override
    public Optional<WantListItem> findById(Long id) {
        return wantItems.stream().filter(wli -> wli.getId().equals(id)).findFirst();
    }

    @Override
    public Optional<WantListItem> addWantItem(WantListItem wantListItem) {
        if (wantItems.stream().anyMatch(wli -> wli.getModel().equals(wantListItem.getModel()) && wli.getBrand().equals(wantListItem.getBrand()))) {
            return Optional.empty();
        }
        WantListItem newWantItem = new WantListItem(wantItems.size() + 1L, wantListItem.getModel(), wantListItem.getBrand(), wantListItem.getTargetPrice(), wantListItem.getAddedAt(), wantListItem.getNotes());
        wantItems = Stream.concat(wantItems.stream(), Stream.of(newWantItem)).toList();
        return Optional.of(newWantItem);
    }
}
