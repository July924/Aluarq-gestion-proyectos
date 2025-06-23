package sv.edu.udb.repository;

import sv.edu.udb.modelo.Cargo;
import sv.edu.udb.modelo.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {
    // Consultas personalizadas
    boolean existsByDui(String dui);
    boolean existsByCorreo(String correo);

    // Buscar empleado por DUI
    Empleado findByDui(String dui);

    // Buscar empleados por cargo
    List<Empleado> findByCargo(Cargo cargo);
    // O si el campo se llama "nombreEmpleado"
    List<Empleado> findAllByOrderByNombreEmpleadoAsc();

}