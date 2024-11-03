package com.JJsCarRent.controllers;

import com.JJsCarRent.models.request.auth.AuthRequest;
import com.JJsCarRent.models.response.auth.AuthResponse;
import com.JJsCarRent.services.auth.AuthIService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Iniciar sesion")
    @PostMapping
    public ResponseEntity<AuthResponse> iniciarSesion(@RequestBody @Valid AuthRequest request){
        return ResponseEntity.ok(authIService.iniciarSesion(request));
    }

}
