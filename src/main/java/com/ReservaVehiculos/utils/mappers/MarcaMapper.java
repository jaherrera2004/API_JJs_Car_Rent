package com.ReservaVehiculos.utils.mappers;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.entity.MarcaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    MarcaDto toDto(MarcaEntity marcaEntity);
    MarcaEntity toEntity(MarcaDto marcaDto);
}
