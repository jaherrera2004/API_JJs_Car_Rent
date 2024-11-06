package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;

import java.util.List;

public interface VehiculoIRepository {
    void save(VehiculoEntity vehiculoEntity);

    boolean existsByPlaca(String placa);

    List<VehiculoEntity> findAll();
}
