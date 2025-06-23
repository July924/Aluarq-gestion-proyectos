package sv.edu.udb.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import sv.edu.udb.controller.request.ProyectoRequest;
import sv.edu.udb.controller.response.ProyectoResponse;
import sv.edu.udb.modelo.Proyecto;

@Mapper(componentModel = "spring", uses = ProyectoMapperHelper.class)
public interface ProyectoMapper {

    @Mapping(target = "tipoProyecto", source = "idTipoProyecto")
    @Mapping(target = "arquitectoEncargado", source = "idEmpleado")
    @Mapping(target = "estadoProyecto", source = "idEstado")
    Proyecto toEntity(ProyectoRequest request);

    ProyectoResponse toResponse(Proyecto proyecto);


    void updateEntity(@MappingTarget Proyecto proyecto, ProyectoRequest request);
}

