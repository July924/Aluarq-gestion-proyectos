package sv.edu.udb.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tipoproyecto")
@Builder
public class TipoProyecto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoProyecto")
    private Long idTipoProyecto;
    private String nombreTipoProyecto;
}
