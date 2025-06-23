package sv.edu.udb.modelo;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "unidadesmedida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUnidadMedida")
    private Long idUnidadMedida;
    private String nombreUnidad;
}