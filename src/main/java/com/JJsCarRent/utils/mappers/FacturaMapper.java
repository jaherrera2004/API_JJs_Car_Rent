package com.JJsCarRent.utils.mappers;

import com.JJsCarRent.models.dto.FacturaDto;
import com.JJsCarRent.models.entity.FacturaEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FacturaMapper {

    FacturaEntity toEntity(FacturaDto facturaDto);
    FacturaDto toDto(FacturaEntity facturaEntity);
}
