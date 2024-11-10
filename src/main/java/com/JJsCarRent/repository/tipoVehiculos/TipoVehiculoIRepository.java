package com.JJsCarRent.repository.tipoVehiculos;

import com.JJsCarRent.models.entity.TipoVehiculoEntity;

import java.util.List;

public interface TipoVehiculoIRepository {
    boolean existsById(Integer id);

    void save(TipoVehiculoEntity tipoVehiculoEntity);

    boolean existsByTipo(String tipo);

    List<TipoVehiculoEntity> findAll();

    String findTipoById(Integer id);

    void desactivar(Integer id);

    void activar(Integer id);
}
