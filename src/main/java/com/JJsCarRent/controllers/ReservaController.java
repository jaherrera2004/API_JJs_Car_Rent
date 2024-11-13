package com.JJsCarRent.controllers;

import com.JJsCarRent.models.request.reserva.ReservaRequest;
import com.JJsCarRent.models.response.GenericResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/reservas")
public class ReservaController {

    @PostMapping
    public ResponseEntity<GenericResponse> agregarFactura(ReservaRequest request){
        return null;
    }


}
