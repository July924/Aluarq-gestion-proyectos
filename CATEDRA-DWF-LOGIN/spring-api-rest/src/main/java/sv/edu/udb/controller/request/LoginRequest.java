package sv.edu.udb.controller.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String usuario;
    private String contrasenia;
}