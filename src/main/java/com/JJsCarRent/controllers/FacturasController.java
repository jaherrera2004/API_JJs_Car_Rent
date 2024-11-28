package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.FacturaDto;
import com.JJsCarRent.services.facturas.FacturaIService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/facturas")
public class FacturasController {

    private final FacturaIService facturaIService;

    @GetMapping
    @PreAuthorize("hasAuthority('facturas:obtener-lista') ")
    public List<FacturaDto> obtenerFacturas() {
        return facturaIService.obtenerFacturas();
    }

}
