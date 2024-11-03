package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.ModeloDto;
import com.JJsCarRent.models.entity.ModeloEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModeloMapper {

    ModeloDto toDto(ModeloEntity modeloEntity);
    ModeloEntity toEntity(ModeloDto modeloDto);
}
