package sv.edu.udb.service.implementation;

import sv.edu.udb.controller.request.MaterialProyectoRequest;
import sv.edu.udb.controller.response.MaterialProyectoResponse;
import sv.edu.udb.modelo.MaterialesProyecto;
import sv.edu.udb.modelo.Proyecto;
import sv.edu.udb.modelo.UnidadMedida;
import sv.edu.udb.repository.MaterialesProyectoRepository;
import sv.edu.udb.repository.ProyectoRepository;
import sv.edu.udb.repository.UnidadMedidaRepository;
import sv.edu.udb.service.MaterialProyectoService;
import sv.edu.udb.service.mapper.MaterialProyectoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MaterialProyectoServiceImpl implements MaterialProyectoService {

    private final MaterialesProyectoRepository materialesProyectoRepository;
    private final ProyectoRepository proyectoRepository;
    private final UnidadMedidaRepository unidadMedidaRepository;
    private final MaterialProyectoMapper mapper;

    @Override
    @Transactional
    public MaterialProyectoResponse addMaterialToProyecto(MaterialProyectoRequest request) {
        Proyecto proyecto = proyectoRepository.findById(request.getIdProyecto())
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.getIdUnidadMedida())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));

        // Calcular subtotal basado en precio unitario y cantidad
        BigDecimal subtotal = request.getPrecioUnitario().multiply(BigDecimal.valueOf(request.getCantidadUtilizada()));

        MaterialesProyecto nuevoMaterial = MaterialesProyecto.builder()
                .proyecto(proyecto)
                .nombreMaterial(request.getNombreMaterial())
                .cantidadUtilizada(request.getCantidadUtilizada())
                .precioUnitario(request.getPrecioUnitario().doubleValue())
                .unidadMedida(unidadMedida)
                .subtotal(subtotal.doubleValue())
                .build();

        MaterialesProyecto saved = materialesProyectoRepository.save(nuevoMaterial);
        return mapper.toResponse(saved);
    }

    @Override
    public List<MaterialProyectoResponse> getMaterialesByProyecto(Long proyectoId) {
        List<MaterialesProyecto> materiales = materialesProyectoRepository.findByProyecto_IdProyecto(proyectoId);
        return materiales.stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MaterialProyectoResponse updateMaterialProyecto(Long idMaterialProyecto, MaterialProyectoRequest request) {
        MaterialesProyecto materialProyecto = materialesProyectoRepository.findById(Math.toIntExact(idMaterialProyecto))
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        UnidadMedida unidadMedida = unidadMedidaRepository.findById(request.getIdUnidadMedida())
                .orElseThrow(() -> new RuntimeException("Unidad de medida no encontrada"));

        // Calcular nuevo subtotal
        BigDecimal subtotal = request.getPrecioUnitario().multiply(BigDecimal.valueOf(request.getCantidadUtilizada()));

        materialProyecto.setNombreMaterial(request.getNombreMaterial());
        materialProyecto.setCantidadUtilizada(request.getCantidadUtilizada());
        materialProyecto.setPrecioUnitario(request.getPrecioUnitario().doubleValue());
        materialProyecto.setUnidadMedida(unidadMedida);
        materialProyecto.setSubtotal(subtotal.doubleValue());

        MaterialesProyecto updated = materialesProyectoRepository.save(materialProyecto);
        return mapper.toResponse(updated);
    }

    @Override
    public void deleteMaterialProyecto(Long idMaterialProyecto) {
        materialesProyectoRepository.deleteById(Math.toIntExact(idMaterialProyecto));
    }

}