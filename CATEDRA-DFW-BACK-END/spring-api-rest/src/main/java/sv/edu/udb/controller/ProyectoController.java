package sv.edu.udb.controller;


import sv.edu.udb.controller.request.ProyectoRequest;
import sv.edu.udb.controller.response.ProyectoResponse;
import sv.edu.udb.repository.ProyectoRepository;
import sv.edu.udb.service.ProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proyectos")
@RequiredArgsConstructor
public class ProyectoController {

    private final ProyectoService proyectoService;
    private final ProyectoRepository proyectoRepository;

    @PostMapping
    public ResponseEntity<ProyectoResponse> createProyecto(@RequestBody ProyectoRequest request) {
        return new ResponseEntity<>(proyectoService.createProyecto(request), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProyectoResponse>> getAllProyectos() {
        return ResponseEntity.ok(proyectoService.getAllProyectos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProyectoResponse> getProyectoById(@PathVariable Long id) {
        return ResponseEntity.ok(proyectoService.getProyectoById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProyectoResponse> updateProyecto(
            @PathVariable Long id, @RequestBody ProyectoRequest request) {
        return ResponseEntity.ok(proyectoService.updateProyecto(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProyecto(@PathVariable Long id) {
        proyectoService.deleteProyecto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/empleado/{empleadoId}")
    public ResponseEntity<List<ProyectoResponse>> getProyectosByArquitecto(@PathVariable Long empleadoId) {
        return ResponseEntity.ok(proyectoService.getProyectosByArquitecto(empleadoId));
    }
}