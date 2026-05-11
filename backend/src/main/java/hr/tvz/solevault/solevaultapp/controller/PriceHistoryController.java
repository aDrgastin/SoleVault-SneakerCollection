package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.model.PriceHistoryCommand;
import hr.tvz.solevault.solevaultapp.model.PriceHistoryDTO;
import hr.tvz.solevault.solevaultapp.service.PriceHistoryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/price-history")
@AllArgsConstructor
public class PriceHistoryController {
    private PriceHistoryService priceHistoryService;

    @GetMapping
    public ResponseEntity<List<PriceHistoryDTO>> findAll() {
        return ResponseEntity.ok(priceHistoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PriceHistoryDTO> findById(@PathVariable Long id) {
        return priceHistoryService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sneaker/{id}")
    public ResponseEntity<List<PriceHistoryDTO>> findBySneakerId(@PathVariable Long id) {
        return ResponseEntity.ok(priceHistoryService.findBySneakerId(id));
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody PriceHistoryCommand command, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            PriceHistoryDTO created = priceHistoryService.addPriceHistory(command);
            return ResponseEntity.status(201).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody PriceHistoryCommand command, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return priceHistoryService.updatePriceHistory(id, command).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        return priceHistoryService.deletePriceHistory(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
