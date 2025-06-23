package sv.edu.udb.repository;

import sv.edu.udb.modelo.TipoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface TipoProyectoRepository extends JpaRepository<TipoProyecto, Long> {

    Optional<TipoProyecto> findByNombreTipoProyecto(String nombreTipoProyecto);

    boolean existsByNombreTipoProyecto(String nombreTipoProyecto);

    List<TipoProyecto> findAllByOrderByNombreTipoProyectoAsc();


}
