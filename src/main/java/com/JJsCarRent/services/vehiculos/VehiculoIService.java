package com.JJsCarRent.services.vehiculos;

import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;

import java.util.List;

public interface VehiculoIService {
    void agregarVehiculo(VehiculoRequest request);
    List<VehiculosDatosResponse> obtenerListaVehiculos();
}
