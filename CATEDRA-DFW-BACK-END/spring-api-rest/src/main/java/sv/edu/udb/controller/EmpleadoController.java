package sv.edu.udb.controller;

import sv.edu.udb.controller.request.EmpleadoRequest;
import sv.edu.udb.controller.response.EmpleadoResponse;
import sv.edu.udb.controller.response.ProyectoResponse;
import sv.edu.udb.service.EmpleadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @PostMapping
    public ResponseEntity<EmpleadoResponse> createEmpleado(@RequestBody EmpleadoRequest request) {
        return ResponseEntity.ok(empleadoService.createEmpleado(request));
    }

    @GetMapping
    public ResponseEntity<List<EmpleadoResponse>> getAllEmpleados() {
        return ResponseEntity.ok(empleadoService.getAllEmpleados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> getEmpleadoById(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.getEmpleadoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpleadoResponse> updateEmpleado(
            @PathVariable Long id, @RequestBody EmpleadoRequest request) {
        return ResponseEntity.ok(empleadoService.updateEmpleado(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmpleado(@PathVariable Long id) {
        empleadoService.deleteEmpleado(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/proyectos")
    public ResponseEntity<List<ProyectoResponse>> getProyectosByEmpleado(@PathVariable Long id) {
        return ResponseEntity.ok(empleadoService.getProyectosByEmpleado(id));
    }
}