package sv.edu.udb.service.mapper;

import org.mapstruct.*;
import sv.edu.udb.controller.request.EmpleadoRequest;
import sv.edu.udb.controller.response.EmpleadoResponse;
import sv.edu.udb.modelo.Cargo;
import sv.edu.udb.modelo.Empleado;

@Mapper(componentModel = "spring")
public interface EmpleadoMapper {

    @Mapping(target = "cargo", ignore = true)
    Empleado toEntity(EmpleadoRequest request);

    @Mapping(target = "cargo", source = "cargo")
    EmpleadoResponse toResponse(Empleado entity);

    @Mapping(target = "cargo", ignore = true)
    void updateEntity(@MappingTarget Empleado entity, EmpleadoRequest request);

    @AfterMapping
    default void mapCargoToResponse(@MappingTarget EmpleadoResponse.CargoResponse target, Cargo source) {
        if (source != null) {
            target.setIdCargo(source.getIdCargo());
            target.setNombreCargo(source.getNombreCargo());
        }
    }
}