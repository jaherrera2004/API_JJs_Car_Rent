package com.JJsCarRent.repository.reservas;

import com.JJsCarRent.models.entity.ReservaEntity;

import java.util.List;

public interface ReservaIRepository {

    void save(ReservaEntity entity);

    List<ReservaEntity> findAll();

    List<ReservaEntity> findAllByIdUsuario(Integer idUsuario);

    String findEstadoById(Integer id);

    boolean existsEstadoById(Integer id);

    boolean existsById(Integer id);

    void updateEstado(Integer idReserva, Integer idEstado);

    Integer obtenerTotalReservas();
}
