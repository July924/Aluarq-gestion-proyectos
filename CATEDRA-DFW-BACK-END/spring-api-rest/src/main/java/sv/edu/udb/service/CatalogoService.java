package sv.edu.udb.service;


import sv.edu.udb.modelo.*;

import java.util.List;

public interface CatalogoService {
    List<TipoProyecto> obtenerTodosTiposProyecto();
    List<EstadoProyecto> obtenerTodosEstados();
    List<Empleado> obtenerTodosEmpleados();
    List<UnidadMedida> obtenerTodasUnidadesMedidas();
    List<Cargo> obtenerTodosCargos();
}