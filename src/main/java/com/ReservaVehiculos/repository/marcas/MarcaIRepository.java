package com.ReservaVehiculos.repository.marcas;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.entity.MarcaEntity;

import java.util.List;

public interface MarcaIRepository {
    boolean existsByMarca(String marca);
    void save(MarcaEntity marca);
    List<MarcaDto> findAll();
    void desactivarMarca(Integer id);
    boolean existsById(Integer id);
    void activarMarca(Integer id);
    MarcaDto findById(Integer id);
}
