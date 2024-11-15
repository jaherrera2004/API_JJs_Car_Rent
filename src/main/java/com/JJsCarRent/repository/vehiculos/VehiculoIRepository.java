package com.JJsCarRent.repository.vehiculos;

import com.JJsCarRent.models.entity.VehiculoEntity;

import java.time.LocalDate;
import java.util.List;

public interface VehiculoIRepository {
    void save(VehiculoEntity vehiculoEntity);

    VehiculoEntity findById(Integer id);

    boolean existsByPlaca(String placa);

    boolean existsById(Integer id);

    boolean isVehiculoDisponible(Integer id, LocalDate fechaInicio, LocalDate fechaEntrega);

    List<VehiculoEntity> findAll();

    List<VehiculoEntity> findAllByTipo(Integer id);

    void desactivar(Integer id);

    void activar(Integer id);

    void agregarFoto(String foto, String placa);

    String fotoByIdVehiculo(Integer id);
}
