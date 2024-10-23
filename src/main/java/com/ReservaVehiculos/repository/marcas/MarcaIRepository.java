package com.ReservaVehiculos.repository.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.entity.MarcaEntity;

public interface MarcaIRepository {
    boolean existsByMarca(String marca);
    void save(MarcaEntity marca);
}
