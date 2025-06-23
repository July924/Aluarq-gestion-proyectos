package sv.edu.udb.service;

import sv.edu.udb.controller.request.EmpleadoRequest;
import sv.edu.udb.controller.response.EmpleadoResponse;
import sv.edu.udb.controller.response.ProyectoResponse;

import java.util.List;

public interface EmpleadoService {
    EmpleadoResponse createEmpleado(EmpleadoRequest request);
    List<EmpleadoResponse> getAllEmpleados();
    EmpleadoResponse getEmpleadoById(Long id);
    EmpleadoResponse updateEmpleado(Long id, EmpleadoRequest request);
    void deleteEmpleado(Long id);
    List<ProyectoResponse> getProyectosByEmpleado(Long id);
}