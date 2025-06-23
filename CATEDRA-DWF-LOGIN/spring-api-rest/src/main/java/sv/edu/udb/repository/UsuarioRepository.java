package sv.edu.udb.repository;

import sv.edu.udb.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUsuario(String usuario);

    boolean existsByUsuario(String usuario);
}