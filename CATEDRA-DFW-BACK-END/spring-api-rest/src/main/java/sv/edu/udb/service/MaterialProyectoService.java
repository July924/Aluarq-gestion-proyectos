package sv.edu.udb.service;

import sv.edu.udb.controller.request.MaterialProyectoRequest;
import sv.edu.udb.controller.response.MaterialProyectoResponse;

import java.math.BigDecimal;
import java.util.List;

public interface MaterialProyectoService {
    MaterialProyectoResponse addMaterialToProyecto(MaterialProyectoRequest request);
    List<MaterialProyectoResponse> getMaterialesByProyecto(Long proyectoId);
    MaterialProyectoResponse updateMaterialProyecto(Long id, MaterialProyectoRequest request);
    void deleteMaterialProyecto(Long id);
}