package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.ModeloDto;
import com.JJsCarRent.models.request.modelo.ModeloRequest;
import com.JJsCarRent.models.response.GenericResponse;
import com.JJsCarRent.models.response.modelos.ModeloDatosResponse;
import com.JJsCarRent.services.modelos.ModeloIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/modelos")
public class ModeloController {

    private final ModeloIService modeloIService;

    @Operation(summary = "Agregar modelo nuevo")
    @PreAuthorize("hasAuthority('modelo:agregar-modelo')")
    @PostMapping
    public ResponseEntity<GenericResponse> agregarModelo(@RequestBody @Valid ModeloRequest modeloRequest) {
        modeloIService.agregarModelo(modeloRequest);
        return ResponseEntity.ok(GenericResponse.ok(true, "Modelo agregado existosamente"));
    }

    @Operation(summary = "Obtener datos de un modelo por su id")
    @PreAuthorize("hasAuthority('modelo:obtener-modelo')")
    @GetMapping("/{id}")
    public ModeloDto obtenerModeloPorId(@PathVariable Integer id) {
        return modeloIService.obtenerPorId(id);
    }

    @Operation(summary = "Traer lista de modelos")
    @PreAuthorize("hasAuthority('modelo:obtener-lista')")
    @GetMapping
    public List<ModeloDatosResponse> obtenerListaModelos() {
        return modeloIService.obtenerListaModelos();
    }

    @Operation(summary = "Desactivar modelo")
    @PreAuthorize("hasAuthority('modelo:desactivar')")
    @DeleteMapping("/{id}")
    public void desactivarModelo(@PathVariable Integer id) {
        modeloIService.desactivarModelo(id);
    }

    @Operation(summary = "Activar modelo")
    @PreAuthorize("hasAuthority('modelo:activar')")
    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse> activarModelo(@PathVariable Integer id){
        return ResponseEntity.ok(GenericResponse.ok(true, "El modelo ha sido activado"));
    }

}
