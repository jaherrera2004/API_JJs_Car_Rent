package com.JJsCarRent.controllers;

import com.JJsCarRent.models.dto.MarcaDto;
import com.JJsCarRent.models.request.marcas.MarcaLogoRequest;
import com.JJsCarRent.models.request.marcas.MarcaRequest;
import com.JJsCarRent.models.response.GenericResponse;
import com.JJsCarRent.models.response.marcas.MarcaConLogoResponse;
import com.JJsCarRent.services.marcas.MarcaIService;
import com.JJsCarRent.utils.ArchivoUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/marcas")
public class MarcaController {

    private final MarcaIService marcaIService;
    private final ArchivoUtil archivoUtil;

    @Operation(summary = "Agregar nueva marca")
    @PreAuthorize("hasAuthority('marca:agregar')")
    @PostMapping
    public ResponseEntity<GenericResponse> agregarMarca(@RequestPart("data") @Valid MarcaRequest request,
                                                        @RequestPart("logo") MultipartFile logo) throws IOException {
        marcaIService.agregarMarca(request, logo);
        return ResponseEntity.ok(GenericResponse.ok(true,"Marca agregada exitosamente"));
    }

    @Operation(summary = "Traer lista de marcas")
    @PreAuthorize("hasAuthority('marca:obtener-lista')")
    @GetMapping
    public List<MarcaDto> obtenerListaMarcas() {
        return marcaIService.obtenerListaMarcas();
    }

    @Operation(summary = "Desactivar marca")
    @PreAuthorize("hasAuthority('marca:desactivar')")
    @DeleteMapping("/{id}")
    public void desactivarMarca(@PathVariable Integer id) {
        marcaIService.desactivarMarca(id);
    }

    @Operation(summary = "Activar marca")
    @PreAuthorize("hasAuthority('marca:activar')")
    @PutMapping("/{id}")
    public void activarMarca(@PathVariable Integer id) {
        marcaIService.activarMarca(id);
    }

    @Operation(summary = "Desactivar marca")
    @PreAuthorize("hasAuthority('marca:obtener')")
    @GetMapping("/{id}")
    public MarcaDto obtenerMarcaPorId(@PathVariable Integer id) {
        return marcaIService.obtenerPorId(id);
    }

//    @Operation(summary = "Agregar logo a una marca")
//    @PreAuthorize("hasAuthority('marca:agregar-logo')")
//    @PostMapping("/logo")
//    public void agregarLogo(@ModelAttribute MarcaLogoRequest request) throws IOException {
//        marcaIService.agregarLogo(request);
//    }

    @Operation(summary = "Obtener el logo de una marca")
    @PreAuthorize("hasAuthority('marca:obtener-logo')")
    @GetMapping("/logo/{id}")
    public ResponseEntity<ByteArrayResource> obtenerLogo(@PathVariable Integer id) throws IOException {
        Pair<ByteArrayResource, String> data = marcaIService.obtenerLogo(id);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(data.b))
                .body(data.a);
    }

    @Operation(summary = "Eliminar logo de una marca")
    @PreAuthorize("hasAuthority('marca:eliminar-logo')")
    @DeleteMapping("/logo/{id}")
    public void eliminarLogo(@PathVariable Integer id) throws IOException {
        marcaIService.eliminarLogo(id);
    }

    @Operation(summary = "Obtener datos de una marca con su logo")
    @PreAuthorize("hasAuthority('marca:obtener-marca-logo')")
    @GetMapping("/logos")
    public List<MarcaConLogoResponse> obtenerMarcasConLogo() {
        return marcaIService.obtenerMarcasConLogo();
    }
}
