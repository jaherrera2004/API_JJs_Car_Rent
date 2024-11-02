package com.ReservaVehiculos.repository.usuarios;
import com.ReservaVehiculos.models.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioIRepository {

    void save(UsuarioEntity usuarioEntity);

    void desactivarUsuario(Integer id);

    void activarUsuario(Integer id);

    boolean existsByEmail(String email);

    boolean existsByCedula(String cedula);

    boolean existsById(Integer id);

    List<UsuarioEntity> findAll();

    UsuarioEntity findById(Integer id);

    UsuarioEntity findByUsername(String username);

    boolean existsByUsername(String username);
}
