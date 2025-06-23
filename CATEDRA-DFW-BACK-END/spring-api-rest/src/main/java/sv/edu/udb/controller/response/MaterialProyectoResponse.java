package sv.edu.udb.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialProyectoResponse {
    private String nombreMaterial;
    private int idUnidadMedida;
    private double precioUnitario;
    private String nombreProyecto;
    private int idProyecto;
    private double subtotal;
    private double cantidadUtilizada;
}