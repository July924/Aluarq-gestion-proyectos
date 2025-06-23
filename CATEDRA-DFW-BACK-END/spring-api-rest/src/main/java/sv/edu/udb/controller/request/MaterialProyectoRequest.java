package sv.edu.udb.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MaterialProyectoRequest {
    @NotNull(message = "El ID del proyecto no puede ser nulo")
    private Long idProyecto;

    @NotNull(message = "El ID del material no puede ser nulo")
    private Long idMaterial;

    @Positive(message = "La cantidad debe ser mayor que cero")
    @Digits(integer = 10, fraction = 2, message = "La cantidad debe tener máximo 2 decimales")
    private double cantidadUtilizada;

    @PositiveOrZero(message = "El subtotal no puede ser negativo")
    @Digits(integer = 10, fraction = 2, message = "El subtotal debe tener máximo 2 decimales")
    private BigDecimal subtotal;

    @NotBlank(message = "El nombre del material es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombreMaterial;

    @NotNull(message = "El precio unitario es obligatorio")
    @Positive(message = "El precio debe ser mayor que cero")
    @Digits(integer = 10, fraction = 2, message = "El precio debe tener máximo 2 decimales")
    private BigDecimal precioUnitario;

    @NotNull(message = "La unidad de medida es obligatoria")
    private Long idUnidadMedida;
}