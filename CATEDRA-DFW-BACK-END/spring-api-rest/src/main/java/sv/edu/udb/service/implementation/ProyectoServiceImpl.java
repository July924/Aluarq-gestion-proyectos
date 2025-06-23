package sv.edu.udb.service.implementation;

import sv.edu.udb.controller.request.ProyectoRequest;
import sv.edu.udb.controller.response.ProyectoResponse;
import sv.edu.udb.repository.EmpleadoRepository;
import sv.edu.udb.repository.ProyectoRepository;
import sv.edu.udb.repository.TipoProyectoRepository;
import sv.edu.udb.modelo.Proyecto;
import sv.edu.udb.service.ProyectoService;
import sv.edu.udb.service.mapper.ProyectoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProyectoServiceImpl implements ProyectoService {

    private final ProyectoRepository proyectoRepository;
    private final EmpleadoRepository empleadoRepository;
    private final TipoProyectoRepository tipoProyectoRepository;
    private final ProyectoMapper proyectoMapper;

    @Override
    public ProyectoResponse createProyecto(ProyectoRequest request) {
        Proyecto proyecto = proyectoMapper.toEntity(request);
        proyecto.setArquitectoEncargado(empleadoRepository.findById((long) request.getIdEmpleado())
                .orElseThrow(() -> new RuntimeException("Empleado no encontrado")));
        proyecto.setTipoProyecto(tipoProyectoRepository.findById((long) request.getIdTipoProyecto())
                .orElseThrow(() -> new RuntimeException("Tipo de proyecto no encontrado")));

        return proyectoMapper.toResponse(proyectoRepository.save(proyecto));
    }

    @Override
    public List<ProyectoResponse> getAllProyectos() {
        return proyectoRepository.findAll().stream()
                .map(proyectoMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProyectoResponse getProyectoById(Long id) {
        return proyectoMapper.toResponse(proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado")));
    }

    @Override
    public ProyectoResponse updateProyecto(Long id, ProyectoRequest request) {
        Proyecto proyecto = proyectoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyectoMapper.updateEntity(proyecto, request);
        return proyectoMapper.toResponse(proyectoRepository.save(proyecto));
    }

    @Override
    public void deleteProyecto(Long id) {
        proyectoRepository.deleteById(id);
    }

    @Override
    public List<ProyectoResponse> getProyectosByArquitecto(Long empleadoId) {
        return proyectoRepository.findByArquitectoEncargadoIdEmpleado(empleadoId).stream()
                .map(proyectoMapper::toResponse)
                .collect(Collectors.toList());
    }
}
