package sv.edu.udb.controller.response;

import lombok.*;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String usuario;
    private Long idEmpleado;
    private List<String> roles;

    public JwtResponse(String jwt, String username, Long idEmpleado, List<String> roles) {
    }
}