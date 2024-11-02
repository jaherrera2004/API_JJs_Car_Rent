package com.ReservaVehiculos.repository.modelos;

import com.ReservaVehiculos.models.entity.ModeloEntity;

import java.util.List;

public interface ModeloIRepository {

    void save(ModeloEntity modeloEntity);

    boolean existsByModelo(String modelo);

    boolean existsById(Integer id);

    ModeloEntity findById(Integer id);

    List<ModeloEntity> findAll();
}
