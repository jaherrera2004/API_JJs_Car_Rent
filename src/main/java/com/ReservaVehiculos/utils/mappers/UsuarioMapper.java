package com.ReservaVehiculos.utils.mappers;

import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(UsuarioEntity usuarioEntity);
    UsuarioEntity toEntity(UsuarioDto usuarioDto);

}
