package com.ReservaVehiculos.services.marcas.logo;

import com.ReservaVehiculos.models.request.marcas.MarcaLogoRequest;

import java.io.IOException;

public interface MarcaLogoIService {
    void agregarLogo(MarcaLogoRequest request) throws IOException;
}
