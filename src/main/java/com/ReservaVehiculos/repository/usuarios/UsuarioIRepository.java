package com.ReservaVehiculos.repository.usuarios;

import com.ReservaVehiculos.models.entity.UsuarioEntity;

public interface UsuarioIRepository {

    public void save(UsuarioEntity usuarioEntity);

    public boolean existsByEmail(String email);

    public boolean existsByCedula(String cedula);

}
