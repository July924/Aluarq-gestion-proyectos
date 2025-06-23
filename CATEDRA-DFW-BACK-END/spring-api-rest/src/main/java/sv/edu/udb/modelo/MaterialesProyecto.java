package sv.edu.udb.modelo;

import jakarta.persistence.*;
import lombok.*;
import org.mapstruct.Mapping;

@Entity
@Table(name = "materialesproyectos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialesProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaterialProyecto")
    private Long idMaterialProyecto;
    private double cantidadUtilizada;
    private double subtotal;
    @ManyToOne
    @JoinColumn(name = "idProyecto")
    private Proyecto proyecto;
    private String nombreMaterial;
    private double precioUnitario;
    @ManyToOne
    @JoinColumn(name = "idUnidadMedida")
    private UnidadMedida unidadMedida;

}