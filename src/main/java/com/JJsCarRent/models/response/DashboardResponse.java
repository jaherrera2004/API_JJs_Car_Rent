package com.JJsCarRent.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DashboardResponse {

    private float totalGanancias;
    private Integer totalClientes;
    private Integer totalReservas;
    private Integer totalVehiculos;
}
