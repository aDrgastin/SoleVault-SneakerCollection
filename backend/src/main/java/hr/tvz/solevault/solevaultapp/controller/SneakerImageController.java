package hr.tvz.solevault.solevaultapp.controller;

import hr.tvz.solevault.solevaultapp.model.SneakerImageCommand;
import hr.tvz.solevault.solevaultapp.model.SneakerImageDTO;
import hr.tvz.solevault.solevaultapp.service.SneakerImageService;
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
@RequestMapping("/api/sneaker-image")
@AllArgsConstructor
public class SneakerImageController {
    private SneakerImageService service;

    @GetMapping
    public ResponseEntity<List<SneakerImageDTO>> getSneakerImages() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SneakerImageDTO> getById(@PathVariable Long id) {
        return service.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/sneaker/{id}")
    public ResponseEntity<List<SneakerImageDTO>> getBySneakerId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findBySneakerId(id));
    }

    @PostMapping
    public ResponseEntity<?> addSneakerImage(@Valid @RequestBody SneakerImageCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            return ResponseEntity.status(201).body(service.add(command));
        } catch (RuntimeException e) {
            return ResponseEntity.status(409).build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateSneakerImage(@PathVariable Long id, @Valid @RequestBody SneakerImageCommand command, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResult.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            FieldError::getField, FieldError::getDefaultMessage, (existing, duplicate) -> existing + "\n" + duplicate
                    ));
            return ResponseEntity.badRequest().body(errors);
        }
        return service.update(id, command)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            e.printStackTrace();
            return ResponseEntity.notFound().build();
        }
    }
}
