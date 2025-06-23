package sv.edu.udb.controller.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoRequest {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String nombreEmpleado;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(max = 100, message = "El apellido no puede exceder los 100 caracteres")
    private String apellidoEmpleado;

    @NotBlank(message = "El DUI es obligatorio")
    @Pattern(regexp = "^[0-9]{8}-[0-9]$", message = "Formato de DUI inválido")
    private String dui;

    @NotNull(message = "El teléfono es obligatorio")
    @Positive(message = "El teléfono debe ser positivo")
    private int telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Correo electrónico inválido")
    private String correo;

    @NotNull(message = "El cargo es obligatorio")
    private Long idCargo;
}