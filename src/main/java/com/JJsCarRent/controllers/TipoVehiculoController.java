package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.TipoVehiculoDto;
import com.JJsCarRent.models.request.tipoVehiculo.TipoVehiculoRequest;
import com.JJsCarRent.services.TipoVehiculo.TipoVehiculoIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/tipo-vehiculos")
public class TipoVehiculoController {

    private final TipoVehiculoIService tipoVehiculoIService;

    @Operation(summary = "Agregar tipo de vehiculo")
    @PreAuthorize("hasAuthority('tipo-vehiculo:agregar')")
    @PostMapping
    public ResponseEntity<Void> agregarTipoVehiculo(@RequestBody @Valid TipoVehiculoRequest request){
        tipoVehiculoIService.agregarTipoVehiculo(request);
        return ResponseEntity.ok()
                .build();
    }

    @Operation(summary = "Obtener lista de tipos de vehiculo")
    @PreAuthorize("hasAuthority('tipo-vehiculo:obtener-lista')")
    @GetMapping
    public List<TipoVehiculoDto> obtenerListaTipoVehiculos(){
        return tipoVehiculoIService.obtenerListaTipoVehiculos();
    }
}
