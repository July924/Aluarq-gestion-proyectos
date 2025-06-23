package sv.edu.udb.modelo;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "empleados")

public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String dui;
    private int telefono;
    private String correo;
    @ManyToOne
    @JoinColumn(name = "idCargo")
    private Cargo cargo;
}
