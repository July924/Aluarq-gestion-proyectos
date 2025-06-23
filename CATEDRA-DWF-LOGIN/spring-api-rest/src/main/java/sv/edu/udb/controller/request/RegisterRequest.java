package sv.edu.udb.controller.request;

import lombok.Data;
import java.util.List;

@Data
public class RegisterRequest {
    private String usuario;
    private String contrasenia;
    private Long idEmpleado;
    private List<String> roles;
}