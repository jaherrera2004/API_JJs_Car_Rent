package com.JJsCarRent.repository.marcas;


import com.JJsCarRent.models.entity.MarcaEntity;

import java.util.List;

public interface MarcaIRepository {

    boolean existsByMarca(String marca);

    void save(MarcaEntity marca);

    List<MarcaEntity> findAll();

    void desactivarMarca(Integer id);

    boolean existsById(Integer id);

    void activarMarca(Integer id);

    MarcaEntity findById(Integer id);

    String findMarcaById(Integer id);

    void actualizarFoto(String foto, String marca);

    String obtenerLogoPorId(Integer id);

    boolean tieneLogo(Integer id);

    boolean tieneLogo(String logo);

    void eliminarLogo(Integer id);

    String obtenerLogoPorMarca(String marca);

}
