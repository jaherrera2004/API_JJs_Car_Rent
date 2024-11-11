package com.JJsCarRent.controllers;

import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.GenericResponse;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import com.JJsCarRent.services.vehiculos.VehiculoIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/vehiculos")
public class VehiculoController {

    private final VehiculoIService vehiculoIService;

    @Operation(summary = "Agregar vehiculo nuevo")
    @PreAuthorize("hasAuthority('vehiculo:agregar-vehiculo')")
    @PostMapping
    public void agregarVehiculo(@RequestPart("data") @Valid VehiculoRequest request,
                                @RequestPart("fotos") List<MultipartFile> fotos) {

        vehiculoIService.agregarVehiculo(request,fotos);
    }

    @Operation(summary = "Obtener lista de vehiculos")
    @PreAuthorize("hasAuthority('vehiculo:obtener-lista')")
    @GetMapping
    public List<VehiculosDatosResponse> obtenerListaVehiculos (){
        return vehiculoIService.obtenerListaVehiculos();
    }

    @Operation(summary = "Desactivar vehiculo")
    @PreAuthorize("hasAuthority('vehiculo:desactivar')")
    @DeleteMapping("/{id}")
    public void desactivarVehiculo(@PathVariable Integer id){
        vehiculoIService.desactivarVehiculo(id);
    }

    @Operation(summary = "Activar vehiculo")
    @PreAuthorize("hasAuthority('vehiculo:activar')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> activarVehiculo(@PathVariable Integer id){
        vehiculoIService.activarVehiculo(id);
        return ResponseEntity.ok(GenericResponse.ok(true,"Vehiculo activado exitosamente"));
    }

}
