package com.ReservaVehiculos.utils.mappers;

import com.ReservaVehiculos.models.dto.ModeloDto;
import com.ReservaVehiculos.models.entity.ModeloEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    ModeloDto toDto(ModeloEntity modeloEntity);
    ModeloEntity toEntity(ModeloDto modeloDto);
}
