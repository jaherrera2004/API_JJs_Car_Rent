package com.ReservaVehiculos.controllers;

import com.ReservaVehiculos.models.request.auth.AuthRequest;
import com.ReservaVehiculos.models.response.auth.AuthResponse;
import com.ReservaVehiculos.services.auth.AuthIService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

    private final AuthIService authIService;

    @PostMapping
    public ResponseEntity<AuthResponse> iniciarSesion(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(authIService.iniciarSesion(request));
    }

}
