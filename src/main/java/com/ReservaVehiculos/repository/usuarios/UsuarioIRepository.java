package com.ReservaVehiculos.repository.usuarios;

import com.ReservaVehiculos.models.entity.UsuarioEntity;

public interface UsuarioIRepository {

    public void save(UsuarioEntity usuarioEntity);

    public void deleteById(Integer id);

    public boolean existsByEmail(String email);

    public boolean existsByCedula(String cedula);

    public boolean existsById(Integer id);
}
