package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.ReservaDto;
import com.JJsCarRent.models.entity.ReservaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReservaMapper {

    ReservaEntity toEntity(ReservaDto dto);

    ReservaDto toDto(ReservaEntity entity);
}
