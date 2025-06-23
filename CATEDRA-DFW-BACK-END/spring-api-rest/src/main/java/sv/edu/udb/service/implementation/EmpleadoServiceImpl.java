package sv.edu.udb.service.implementation;

import sv.edu.udb.controller.request.EmpleadoRequest;
import sv.edu.udb.controller.response.EmpleadoResponse;
import sv.edu.udb.controller.response.ProyectoResponse;
import sv.edu.udb.modelo.Empleado;
import sv.edu.udb.repository.CargoRepository;
import sv.edu.udb.repository.EmpleadoRepository;
import sv.edu.udb.repository.ProyectoRepository;
import sv.edu.udb.service.EmpleadoService;
import sv.edu.udb.service.mapper.EmpleadoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

    private final EmpleadoRepository empleadoRepository;
    private final CargoRepository cargoRepository;
    private final ProyectoRepository proyectoRepository;
    private final EmpleadoMapper empleadoMapper;

    @Override
    @Transactional
    public EmpleadoResponse createEmpleado(EmpleadoRequest request) {
        Empleado empleado = empleadoMapper.toEntity(request);
        empleado.setCargo(cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado")));
        return empleadoMapper.toResponse(empleadoRepository.save(empleado));
    }

    @Override
    public List<EmpleadoResponse> getAllEmpleados() {
        return empleadoRepository.findAll().stream()
                .map(empleadoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoResponse getEmpleadoById(Long id) {
        return empleadoMapper.toResponse(empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado")));
    }

    @Override
    @Transactional
    public EmpleadoResponse updateEmpleado(Long id, EmpleadoRequest request) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));

        empleadoMapper.updateEntity(empleado, request);
        empleado.setCargo(cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado")));

        return empleadoMapper.toResponse(empleadoRepository.save(empleado));
    }

    @Override
    @Transactional
    public void deleteEmpleado(Long id) {
        Empleado empleado = empleadoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado"));
        empleadoRepository.delete(empleado);
    }

    @Override
    public List<ProyectoResponse> getProyectosByEmpleado(Long id) {
        return proyectoRepository.findByArquitectoEncargadoIdEmpleado(id).stream()
                .map(proyecto -> ProyectoResponse.builder()
                        .idProyecto(Math.toIntExact(proyecto.getIdProyecto()))
                        .nombreProyecto(proyecto.getNombreProyecto())
                        .descripcionProyecto(proyecto.getDescripcionProyecto())
                        .fechaInicio(proyecto.getFechaInicio())
                        .fechaFin(proyecto.getFechaFin())
                        .build())
                .collect(Collectors.toList());
    }
}