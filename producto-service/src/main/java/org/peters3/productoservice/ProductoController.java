package org.peters3.productoservice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/producto")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getOrderById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto crear(@RequestBody Producto producto) {
        return repository.save(producto);
    }

    @DeleteMapping("/{id}")
    public Producto eliminarById(@PathVariable Long id) {
         repository.deleteById(id);
        return null;
    }

    @PutMapping
    public ResponseEntity<Producto> actualizar(@RequestBody Producto producto) {
    if (!repository.existsById(producto.getId())) {
        return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(repository.save(producto));
}
}