package sv.edu.udb.service.implementation;

import sv.edu.udb.controller.request.LoginRequest;
import sv.edu.udb.controller.request.RegisterRequest;
import sv.edu.udb.controller.response.JwtResponse;
import sv.edu.udb.model.Rol;
import sv.edu.udb.model.Usuario;
import sv.edu.udb.repository.RolRepository;
import sv.edu.udb.repository.UsuarioRepository;
import sv.edu.udb.security.JwtTokenProvider;
import sv.edu.udb.security.UserPrincipal;
import sv.edu.udb.service.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UsuarioRepository usuarioRepository,
                           RolRepository rolRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsuario(),
                        loginRequest.getContrasenia()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getUsername(), userDetails.getIdEmpleado(), roles);
    }

    @Override
    public JwtResponse registerUser(RegisterRequest registerRequest) {
        // Verificar si el usuario ya existe
        if(usuarioRepository.existsByUsuario(registerRequest.getUsuario())) {
            throw new RuntimeException("El nombre de usuario ya está en uso!");
        }

        // Crear nuevo usuario
        Usuario usuario = new Usuario();
        usuario.setUsuario(registerRequest.getUsuario());
        usuario.setContrasenia(passwordEncoder.encode(registerRequest.getContrasenia()));
        usuario.setIdEmpleado(registerRequest.getIdEmpleado());

        // Asignar roles
        List<Rol> roles = registerRequest.getRoles().stream()
                .map(rol -> rolRepository.findByNombreRol(rol)
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + rol)))
                .collect(Collectors.toList());

        usuario.setRoles(roles);
        usuarioRepository.save(usuario);

        // Autenticar al nuevo usuario
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsuario(registerRequest.getUsuario());
        loginRequest.setContrasenia(registerRequest.getContrasenia());

        return authenticateUser(loginRequest);
    }
}