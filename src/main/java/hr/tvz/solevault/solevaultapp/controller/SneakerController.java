package hr.tvz.solevault.solevaultapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import hr.tvz.solevault.solevaultapp.model.SneakerDTO;
import hr.tvz.solevault.solevaultapp.service.SneakerService;

import java.util.List;

@RestController
@RequestMapping("/api/sneakers")
@AllArgsConstructor
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

    @GetMapping("/filter")
    public ResponseEntity<SneakerDTO> getSneakerByModel(@RequestParam String model) {
        return sneakerService.findByModel(model).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SneakerDTO> addSneaker(@RequestBody SneakerDTO dto) {
        return ResponseEntity.status(201).body(sneakerService.addSneaker(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSneaker(@PathVariable Long id) {
        sneakerService.deleteSneaker(id);
        return ResponseEntity.noContent().build();
    }
}
