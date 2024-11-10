package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.TipoVehiculoDto;
import com.JJsCarRent.models.request.tipoVehiculo.TipoVehiculoRequest;
import com.JJsCarRent.models.response.GenericResponse;
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
    public ResponseEntity<GenericResponse> agregarTipoVehiculo(@RequestBody @Valid TipoVehiculoRequest request){
        tipoVehiculoIService.agregarTipoVehiculo(request);
        return ResponseEntity.ok(GenericResponse.ok(true,"Tipo de vehiculo agregado exitosamente"));
    }

    @Operation(summary = "Obtener lista de tipos de vehiculo")
    @PreAuthorize("hasAuthority('tipo-vehiculo:obtener-lista')")
    @GetMapping
    public List<TipoVehiculoDto> obtenerListaTipoVehiculos(){
        return tipoVehiculoIService.obtenerListaTipoVehiculos();
    }

    @Operation(summary = "Desactivar tipo de vehiculo")
    @PreAuthorize("hasAuthority('tipo-vehiculo:desactivar')")
    @DeleteMapping("/{id}")
    public void desactivarTipoVehiculo(@PathVariable Integer id){
        tipoVehiculoIService.desactivarTipoVehiculo(id);
    }

    @Operation(summary = "Activar tipo de vehiculo")
    @PreAuthorize("hasAuthority('tipo-vehiculo:activar')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> activarTipoVehiculo(@PathVariable Integer id){
        return ResponseEntity.ok(GenericResponse.ok(true, "El tipo de vehiculo ha sido activado"));
    }
}
