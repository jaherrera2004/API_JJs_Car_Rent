package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.MarcaDto;
import com.JJsCarRent.models.entity.MarcaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MarcaMapper {
    MarcaDto toDto(MarcaEntity marcaEntity);
    MarcaEntity toEntity(MarcaDto marcaDto);
}
