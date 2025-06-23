package sv.edu.udb.controller.request;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProyectoRequest {
    private int idProyecto;
    private String nombreProyecto;
    private String descripcionProyecto;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    private Date fechaFin;
    private int idEstado;
    private String ubicacionProyecto;
    private int numeroTrabajadores;
    private Double viaticosAsignados;
    private Double presupuestoMateriales;
    private int idTipoProyecto;
    private int idEmpleado;
}