package com.JJsCarRent.repository.facturas;

import com.JJsCarRent.models.entity.FacturaEntity;

import java.util.List;

public interface FacturaIRepository {
    List<FacturaEntity> obtenerFacturas();


    float obtenerGanancias();
}
