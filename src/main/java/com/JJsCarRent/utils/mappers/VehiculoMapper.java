package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.VehiculoDto;
import com.JJsCarRent.models.entity.VehiculoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    VehiculoEntity toEntity(VehiculoDto vehiculoDto);
    VehiculoDto toDto(VehiculoEntity vehiculoEntity);
}
