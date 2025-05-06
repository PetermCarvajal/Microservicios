package org.peters3.taxonomia;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "taxonomia")
public class Taxonomia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TipoCategoria tipo;

    @Enumerated(EnumType.STRING)
    private UnidadMedida unidadMedida;

    @Enumerated(EnumType.STRING)
    private Proveedor proveedor;

    @Override
    public String toString() {
        return String.format("""
              Categorias:
              Tipo Categoria: %s
              Unidad Medida: %s
              Proveedor: %s
              """,tipo,unidadMedida,proveedor);
    }

}