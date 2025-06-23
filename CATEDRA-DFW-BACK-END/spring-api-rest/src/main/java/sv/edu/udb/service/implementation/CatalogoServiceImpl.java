package sv.edu.udb.service.implementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sv.edu.udb.modelo.*;
import sv.edu.udb.repository.*;
import sv.edu.udb.service.CatalogoService;

import java.util.List;

@Service
public class CatalogoServiceImpl implements CatalogoService {

    private final TipoProyectoRepository tipoProyectoRepository;
    private final EstadoProyectoRepository estadoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final CargoRepository cargoRepository;

    @Autowired
    public CatalogoServiceImpl(TipoProyectoRepository tipoProyectoRepository,
                               EstadoProyectoRepository estadoRepository,
                               EmpleadoRepository empleadoRepository, UnidadMedidaRepository unidadMedidaRepository, CargoRepository cargoRepository) {
        this.tipoProyectoRepository = tipoProyectoRepository;
        this.estadoRepository = estadoRepository;
        this.empleadoRepository = empleadoRepository;
        this.unidadMedidaRepository = unidadMedidaRepository;
        this.cargoRepository = cargoRepository;
    }

    @Override
    public List<TipoProyecto> obtenerTodosTiposProyecto() {
        return tipoProyectoRepository.findAllByOrderByNombreTipoProyectoAsc();
    }

    @Override
    public List<EstadoProyecto> obtenerTodosEstados() {
        return estadoRepository.findAllByOrderByNombreEstadoAsc();
    }

    @Override
    public List<Empleado> obtenerTodosEmpleados() {
        return empleadoRepository.findAllByOrderByNombreEmpleadoAsc();
    }

    @Override
    public List<UnidadMedida> obtenerTodasUnidadesMedidas() {
        return unidadMedidaRepository.findAllByOrderByNombreUnidadAsc();
    }

    @Override
    public List<Cargo> obtenerTodosCargos() {
        return cargoRepository.findAllByOrderByNombreCargoAsc();
    }
}
