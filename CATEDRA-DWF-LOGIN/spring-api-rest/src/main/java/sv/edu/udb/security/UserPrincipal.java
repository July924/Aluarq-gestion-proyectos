package sv.edu.udb.security;

import sv.edu.udb.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserPrincipal implements UserDetails {
    private String usuario;
    private String contrasenia;
    private Long idEmpleado;
    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String usuario, String contrasenia, Long idEmpleado,
                         Collection<? extends GrantedAuthority> authorities) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.idEmpleado = idEmpleado;
        this.authorities = authorities;
    }

    public static UserPrincipal create(Usuario usuario) {
        List<GrantedAuthority> authorities = usuario.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getNombreRol()))
                .collect(Collectors.toList());

        return new UserPrincipal(
                usuario.getUsuario(),
                usuario.getContrasenia(),
                usuario.getIdEmpleado(),
                authorities
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return contrasenia;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}