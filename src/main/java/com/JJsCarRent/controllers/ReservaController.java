package com.JJsCarRent.controllers;

import com.JJsCarRent.models.request.reserva.ReservaRequest;
import com.JJsCarRent.models.response.GenericResponse;
import com.JJsCarRent.models.response.reservas.ReservaResponse;
import com.JJsCarRent.services.reservas.ReservaIService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    private final ReservaIService reservaIService;

    @Operation(summary = "Crear reserva")
    @PreAuthorize("hasAuthority('reserva:crear')")
    @PostMapping
    public ResponseEntity<GenericResponse> crearReserva(@RequestBody ReservaRequest request){
        reservaIService.crearReserva(request);
        return ResponseEntity.ok(GenericResponse.ok(true,"Reserva creada existosamente"));
    }

    @Operation(summary = "Obtener lista de reservas")
    @PreAuthorize("hasAuthority('reserva:obtener-lista')")
    @GetMapping
    public List<ReservaResponse> obtenerListaReservas(){
        return reservaIService.obtenerListaReservas();
    }

    @Operation(summary = "Obtener lista de reservas por el id del usuario")
    @PreAuthorize("hasAuthority('reserva:obtener-lista-por-usuario')")
    @GetMapping("/usuario/{idUsuario}")
    public List<ReservaResponse> obtenerListaReservasPorIdUsuario(@PathVariable Integer idUsuario){
        return reservaIService.obtenerListaReservasPorIdUsuario(idUsuario);
    }

    @Operation(summary = "Actualizar estado de reserva")
    @PreAuthorize("hasAuthority('reserva:actualizar')")
    @PutMapping("/{idReserva}/{idEstado}")
    public GenericResponse actualizarReserva(@PathVariable Integer idReserva, @PathVariable Integer idEstado){
        reservaIService.actualizarReserva(idReserva,idEstado);
        return GenericResponse.ok(true, "Reserva actualizada exitosamente");
    }
}
