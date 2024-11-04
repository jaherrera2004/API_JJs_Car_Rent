package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.ModeloDto;
import com.JJsCarRent.models.request.modelo.ModeloRequest;
import com.JJsCarRent.services.modelos.ModeloIService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public void agregarModelo(@RequestBody @Valid  ModeloRequest modeloRequest){
        modeloIService.agregarModelo(modeloRequest);
    }

    @Operation(summary = "Obtener datos de un modelo por su id")
    @PreAuthorize("hasAuthority('modelo:obtener-modelo')")
    @GetMapping("/{id}")
    public ModeloDto obtenerModeloPorId(@PathVariable Integer id){
        return modeloIService.obtenerPorId(id);
    }

    @Operation(summary = "Traer lista de modelos")
    @GetMapping
    public List<ModeloDto> obtenerListaModelos(){
        return null;
    }
    //hsda
}
