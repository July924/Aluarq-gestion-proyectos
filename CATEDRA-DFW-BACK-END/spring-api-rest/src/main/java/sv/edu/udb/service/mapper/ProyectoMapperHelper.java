package sv.edu.udb.service.mapper;
import org.springframework.stereotype.Component;
import sv.edu.udb.modelo.Empleado;
import sv.edu.udb.modelo.EstadoProyecto;
import sv.edu.udb.modelo.TipoProyecto;
import sv.edu.udb.repository.EstadoProyectoRepository;

@Component
public class ProyectoMapperHelper {
    private final EstadoProyectoRepository estadoProyectoRepository;

    public ProyectoMapperHelper(EstadoProyectoRepository estadoProyectoRepository) {
        this.estadoProyectoRepository = estadoProyectoRepository;
    }

    public TipoProyecto mapIdToTipoProyecto(Long idTipoProyecto) {
        if (idTipoProyecto == 0) return null;
        TipoProyecto tipo = new TipoProyecto();
        tipo.setIdTipoProyecto(idTipoProyecto);
        return tipo;
    }

    public Empleado mapIdToEmpleado(int idEmpleado) {
        if (idEmpleado == 0) return null;
        Empleado emp = new Empleado();
        emp.setIdEmpleado((long) idEmpleado);
        return emp;
    }

    // Métodos inversos (para ProyectoResponse)
    public String map(TipoProyecto tipoProyecto) {
        return tipoProyecto != null ? tipoProyecto.getNombreTipoProyecto() : null;
    }

    public String map(Empleado empleado) {
        return empleado != null ? empleado.getNombreEmpleado() : null;
    }

    public EstadoProyecto mapIdToEstado(int idEstado) {
        return estadoProyectoRepository.findById((long) idEstado)
                .orElseThrow(() -> new RuntimeException("Estado de proyecto no encontrado"));
    }

    public String map(EstadoProyecto estado) {
        return estado != null ? estado.getNombreEstado() : null;
    }

}
