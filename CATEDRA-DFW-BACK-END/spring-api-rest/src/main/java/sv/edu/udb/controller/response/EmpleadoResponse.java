package sv.edu.udb.controller.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpleadoResponse {
    private Long idEmpleado;
    private String nombreEmpleado;
    private String apellidoEmpleado;
    private String dui;
    private int telefono;
    private String correo;
    private CargoResponse cargo;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class CargoResponse {
        private Long idCargo;
        private String nombreCargo;
    }
}