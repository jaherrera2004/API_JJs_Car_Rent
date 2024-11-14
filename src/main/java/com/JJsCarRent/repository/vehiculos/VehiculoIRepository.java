package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;

import java.util.List;

public interface VehiculoIRepository {
    void save(VehiculoEntity vehiculoEntity);

    VehiculoEntity findById(Integer id);

    boolean existsByPlaca(String placa);

    boolean existsById(Integer id);

    List<VehiculoEntity> findAll();

    void desactivar(Integer id);

    void activar(Integer id);

    void agregarFoto(String foto, String placa);

    String fotoByIdVehiculo(Integer id);
}
