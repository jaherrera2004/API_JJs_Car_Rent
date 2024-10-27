package com.ReservaVehiculos.repository.modelos;

import com.ReservaVehiculos.models.dto.ModeloDto;
import com.ReservaVehiculos.models.entity.ModeloEntity;

public interface ModeloIRepository {

    void save(ModeloEntity modeloEntity);
    boolean existsByModelo(String modelo);
}
