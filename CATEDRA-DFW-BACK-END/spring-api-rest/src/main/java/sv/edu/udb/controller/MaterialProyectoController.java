package sv.edu.udb.controller;


import sv.edu.udb.controller.request.MaterialProyectoRequest;
import sv.edu.udb.controller.response.MaterialProyectoResponse;
import sv.edu.udb.service.MaterialProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materiales-proyecto")
@RequiredArgsConstructor
public class MaterialProyectoController {

    private final MaterialProyectoService materialProyectoService;

    @PostMapping("/proyecto/{proyectoId}")
    public ResponseEntity<MaterialProyectoResponse> addMaterialToProyecto(
            @PathVariable Long proyectoId,
            @RequestBody MaterialProyectoRequest request) {

        // Asigna el ID del proyecto recibido por URL al request
        request.setIdProyecto(proyectoId);

        return new ResponseEntity<>(materialProyectoService.addMaterialToProyecto(request), HttpStatus.CREATED);
    }
    @GetMapping("/proyecto/{proyectoId}")
    public ResponseEntity<List<MaterialProyectoResponse>> getMaterialesByProyecto(@PathVariable Long proyectoId) {
        return ResponseEntity.ok(materialProyectoService.getMaterialesByProyecto(proyectoId));
    }

    // DELETE: /api/materiales-proyecto/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterialProyecto(@PathVariable Long id) {
        materialProyectoService.deleteMaterialProyecto(id);
        return ResponseEntity.noContent().build();
    }

    // PUT: /api/materiales-proyecto/{id}
    @PutMapping("/{id}")
    public ResponseEntity<MaterialProyectoResponse> updateMaterialProyecto(
            @PathVariable Long id,
            @RequestBody MaterialProyectoRequest request) {
        MaterialProyectoResponse updated = materialProyectoService.updateMaterialProyecto(id, request);
        return ResponseEntity.ok(updated);
    }


}