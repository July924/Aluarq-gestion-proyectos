package sv.edu.udb.repository;

import sv.edu.udb.modelo.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, Long> {
    List<UnidadMedida> findAllByOrderByNombreUnidadAsc();

}