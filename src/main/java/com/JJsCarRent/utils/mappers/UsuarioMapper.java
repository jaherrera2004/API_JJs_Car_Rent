package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.UsuarioDto;
import com.JJsCarRent.models.entity.UsuarioEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDto toDto(UsuarioEntity usuarioEntity);
    UsuarioEntity toEntity(UsuarioDto usuarioDto);

}
