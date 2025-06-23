package sv.edu.udb.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sv.edu.udb.modelo.*;
import sv.edu.udb.service.CatalogoService;

import java.util.List;

@RestController
@RequestMapping("/api/catalogo")
public class CatalogoController {

    private final CatalogoService catalogoService;

    @Autowired
    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping("/tipos-proyecto")
    public List<TipoProyecto> obtenerTiposProyecto() {
        return catalogoService.obtenerTodosTiposProyecto();
    }

    @GetMapping("/estados")
    public List<EstadoProyecto> obtenerEstados() {
        return catalogoService.obtenerTodosEstados();
    }

    @GetMapping("/empleados")
    public List<Empleado> obtenerEmpleados() {
        return catalogoService.obtenerTodosEmpleados();
    }

    @GetMapping("/unidadesmedida")
    public List<UnidadMedida> obtenerUnidadesMedidas() {
        return catalogoService.obtenerTodasUnidadesMedidas();
    }

    @GetMapping("/cargos")
    public List<Cargo> obtenerCargos() {
        return catalogoService.obtenerTodosCargos();
    }
}