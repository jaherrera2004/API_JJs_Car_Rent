package com.JJsCarRent.services.facturas;

import com.JJsCarRent.models.dto.FacturaDto;
import com.JJsCarRent.repository.facturas.FacturaIRepository;
import com.JJsCarRent.utils.mappers.FacturaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FacturaServiceImpl implements FacturaIService{

    private final FacturaIRepository facturaIRepository;
    private final FacturaMapper facturaMapper;

    @Override
    public List<FacturaDto> obtenerFacturas() {

        return facturaIRepository.obtenerFacturas()
                .stream()
                .map(facturaMapper::toDto)
                .toList();
    }

}
