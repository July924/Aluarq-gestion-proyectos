package sv.edu.udb.service.mapper;

import sv.edu.udb.controller.response.MaterialProyectoResponse;
import sv.edu.udb.modelo.MaterialesProyecto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MaterialProyectoMapper {

    @Mapping(target = "nombreMaterial", source = "nombreMaterial")
    @Mapping(target = "idUnidadMedida", source = "unidadMedida.idUnidadMedida")
    @Mapping(target = "precioUnitario", source = "precioUnitario")
    @Mapping(target = "nombreProyecto", source = "proyecto.nombreProyecto")
    @Mapping(target = "idProyecto", source = "proyecto.idProyecto")
    @Mapping(target = "cantidadUtilizada", source = "cantidadUtilizada")
    @Mapping(target = "subtotal", source = "subtotal")
    MaterialProyectoResponse toResponse(MaterialesProyecto entity);
}