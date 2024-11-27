package com.JJsCarRent.controllers;

import com.JJsCarRent.models.response.DashboardResponse;
import com.JJsCarRent.services.dashboard.DashboardIService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/dashboard")
public class DashboardController {

    private final DashboardIService dashboardIService;

    @Operation(summary = "Obtener dashboard")
    @PreAuthorize("hasAuthority('dashboard:obtener-dashboard')")
    @GetMapping
    public DashboardResponse obtenerDashboard() {
        return dashboardIService.obtenerDashboard();
    }
}
