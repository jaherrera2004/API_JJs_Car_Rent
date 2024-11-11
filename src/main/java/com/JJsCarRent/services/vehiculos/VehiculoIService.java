package com.JJsCarRent.services.vehiculos;

import com.JJsCarRent.models.request.vehiculo.VehiculoRequest;
import com.JJsCarRent.models.response.vehiculos.VehiculosDatosResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VehiculoIService {
    void agregarVehiculo(VehiculoRequest request, List<MultipartFile> fotos);

    List<VehiculosDatosResponse> obtenerListaVehiculos();

    void desactivarVehiculo(Integer id);

    void activarVehiculo(Integer id);
}
