package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;

public interface VehiculoIRepository {
    void save(VehiculoEntity vehiculoEntity);
    boolean existsByPlaca(String placa);
}
