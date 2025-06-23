package sv.edu.udb.repository;

import sv.edu.udb.modelo.MaterialesProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialesProyectoRepository extends JpaRepository<MaterialesProyecto, Integer> {
    List<MaterialesProyecto> findByProyecto_IdProyecto(Long proyectoId);
}

