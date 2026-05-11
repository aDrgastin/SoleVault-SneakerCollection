package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.model.SneakerCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;
import hr.tvz.solevault.solevaultapp.service.SneakerService;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/sneakers")
@AllArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class SneakerController {
    private final SneakerService sneakerService;

    @GetMapping
    public ResponseEntity<List<SneakerDTO>> getSneakers() {
        return ResponseEntity.ok(sneakerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SneakerDTO> getSneakerById(@PathVariable Long id) {
        return sneakerService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/model/{model}")
    public ResponseEntity<SneakerDTO> getSneakerByModel(@PathVariable String model) {
        return sneakerService.findByModel(model).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/filter")
    public ResponseEntity<List<SneakerDTO>> getSneakerByColorway(@RequestParam String colorway) {
        return ResponseEntity.ok(sneakerService.findByColorway(colorway));
    }

    @PostMapping
    public ResponseEntity<?> addSneaker(@Valid @RequestBody SneakerCommand sneakerCommand, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return sneakerService.addSneaker(sneakerCommand)
                .map(dto -> ResponseEntity.status(201).body(dto))
                .orElse(ResponseEntity.status(409).build());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSneaker(@PathVariable Long id, @Valid @RequestBody SneakerCommand sneakerCommand, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return sneakerService.updateSneaker(id, sneakerCommand)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/model/{model}")
    public ResponseEntity<Void> deleteSneakerByModel(@PathVariable String model) {
        if (sneakerService.deleteSneaker(model)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSneaker(@PathVariable Long id) {
        if (sneakerService.deleteSneaker(id)) {
            return ResponseEntity.noContent().build();
        } else return ResponseEntity.notFound().build();
    }
}
