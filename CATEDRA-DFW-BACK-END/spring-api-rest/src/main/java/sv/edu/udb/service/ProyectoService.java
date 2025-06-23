package sv.edu.udb.service;
import sv.edu.udb.controller.request.ProyectoRequest;
import sv.edu.udb.controller.response.ProyectoResponse;

import java.util.List;

public interface ProyectoService {
    ProyectoResponse createProyecto(ProyectoRequest request);
    List<ProyectoResponse> getAllProyectos();
    ProyectoResponse getProyectoById(Long id);
    ProyectoResponse updateProyecto(Long id, ProyectoRequest request);
    void deleteProyecto(Long id);
    List<ProyectoResponse> getProyectosByArquitecto(Long empleadoId);
}