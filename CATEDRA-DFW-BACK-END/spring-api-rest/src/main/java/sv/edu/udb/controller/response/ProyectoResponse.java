package sv.edu.udb.controller.response;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoResponse {
    private int idProyecto;
    private String nombreProyecto;
    private String descripcionProyecto;
    private Date fechaInicio;
    private Date fechaFin;
    private String estadoProyecto;
    private String ubicacionProyecto;
    private int numeroTrabajadores;
    private Double viaticosAsignados;
    private Double presupuestoMateriales;
    private String tipoProyecto;
    private String empleado;
}
