package com.ReservaVehiculos.utils.mappers;

import com.ReservaVehiculos.models.dto.VehiculoDto;
import com.ReservaVehiculos.models.entity.VehiculoEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VehiculoMapper {
    VehiculoEntity toEntity(VehiculoDto vehiculoDto);
    VehiculoDto toDto(VehiculoEntity vehiculoEntity);
}
