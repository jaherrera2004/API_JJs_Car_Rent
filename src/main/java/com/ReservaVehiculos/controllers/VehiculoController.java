package com.ReservaVehiculos.controllers;

import com.ReservaVehiculos.models.request.vehiculo.VehiculoRequest;
import com.ReservaVehiculos.services.vehiculos.VehiculoIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoIService vehiculoIService;

    @PreAuthorize("hasAuthority('vehiculo:agregar-vehiculo')")
    @PostMapping
    public void agregarVehiculo(@RequestBody @Valid VehiculoRequest request) {
        vehiculoIService.agregarVehiculo(request);
    }

}
