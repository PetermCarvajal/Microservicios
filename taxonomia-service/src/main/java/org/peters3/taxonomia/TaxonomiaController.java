package org.peters3.taxonomia;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/taxonomia")
public class TaxonomiaController {

    private final TaxonomiaRepository repository;

    public TaxonomiaController(TaxonomiaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Taxonomia> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Taxonomia getTaxonomiaById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public Taxonomia crear(@RequestBody Taxonomia taxonomia) {
        return repository.save(taxonomia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarById(@PathVariable Long id) {
    repository.deleteById(id);
    return ResponseEntity.noContent().build();
    }
    @PutMapping
    public ResponseEntity<Taxonomia> actualizar(@RequestBody Taxonomia taxonomia) {
    if (!repository.existsById(taxonomia.getId())) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(repository.save(taxonomia));
    }
}
