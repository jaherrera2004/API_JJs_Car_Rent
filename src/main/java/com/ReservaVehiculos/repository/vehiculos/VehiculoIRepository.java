package com.ReservaVehiculos.repository.vehiculos;

import com.ReservaVehiculos.models.entity.VehiculoEntity;

public interface VehiculoIRepository {
    void save(VehiculoEntity vehiculoEntity);
    boolean existsByPlaca(String placa);
}
