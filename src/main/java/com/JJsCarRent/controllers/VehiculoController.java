package com.JJsCarRent.controllers;

import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import com.JJsCarRent.services.vehiculos.VehiculoIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoIService vehiculoIService;

    @Operation(summary = "Agregar vehiculo nuevo")
    @PreAuthorize("hasAuthority('vehiculo:agregar-vehiculo')")
    @PostMapping
    public void agregarVehiculo(@RequestBody @Valid VehiculoRequest request) {
        vehiculoIService.agregarVehiculo(request);
    }

    @Operation(summary = "Obtener lista de vehiculos")
    @PreAuthorize("hasAuthority('vehiculo:obtener-lista')")
    @GetMapping
    public List<VehiculosDatosResponse> obtenerListaVehiculos (){
        return vehiculoIService.obtenerListaVehiculos();
    }


}
