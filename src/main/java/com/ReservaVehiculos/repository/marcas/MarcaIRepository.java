package com.ReservaVehiculos.repository.marcas;


import com.ReservaVehiculos.models.entity.MarcaEntity;

import java.util.List;

public interface MarcaIRepository {

    boolean existsByMarca(String marca);
    void save(MarcaEntity marca);
    List<MarcaEntity> findAll();
    void desactivarMarca(Integer id);
    boolean existsById(Integer id);
    void activarMarca(Integer id);
    MarcaEntity findById(Integer id);
    void actualizarFoto(String foto, String marca);
}
