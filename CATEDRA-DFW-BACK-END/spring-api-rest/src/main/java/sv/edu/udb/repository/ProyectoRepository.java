package sv.edu.udb.repository;
import sv.edu.udb.modelo.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    List<Proyecto> findByArquitectoEncargadoIdEmpleado(Long idEmpleado);
}