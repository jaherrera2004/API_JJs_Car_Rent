package com.JJsCarRent.repository.modelos;

import com.JJsCarRent.models.entity.ModeloEntity;

import java.util.List;

public interface ModeloIRepository {

    void save(ModeloEntity modeloEntity);

    boolean existsByModelo(String modelo);

    boolean existsById(Integer id);

    ModeloEntity findById(Integer id);

    List<ModeloEntity> findAll();

    String findModeloById(Integer id);
}
