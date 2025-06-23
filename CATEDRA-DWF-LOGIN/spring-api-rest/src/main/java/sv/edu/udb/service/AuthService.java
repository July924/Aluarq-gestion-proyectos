package sv.edu.udb.service;

import sv.edu.udb.controller.request.LoginRequest;
import sv.edu.udb.controller.request.RegisterRequest;
import sv.edu.udb.controller.response.JwtResponse;

public interface AuthService {
    JwtResponse authenticateUser(LoginRequest loginRequest);
    JwtResponse registerUser(RegisterRequest registerRequest);
}