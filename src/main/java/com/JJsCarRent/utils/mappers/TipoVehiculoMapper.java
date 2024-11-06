package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.TipoVehiculoDto;
import com.JJsCarRent.models.entity.TipoVehiculoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoVehiculoMapper {
    TipoVehiculoDto toDto(TipoVehiculoEntity tipoVehiculoEntity);

    TipoVehiculoEntity toEntity(TipoVehiculoDto tipoVehiculoDto);
}
