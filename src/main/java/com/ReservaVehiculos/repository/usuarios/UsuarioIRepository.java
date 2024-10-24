package com.ReservaVehiculos.repository.usuarios;

import com.ReservaVehiculos.models.dto.MarcaDto;
import com.ReservaVehiculos.models.dto.UsuarioDto;
import com.ReservaVehiculos.models.entity.UsuarioEntity;

import java.util.List;

public interface UsuarioIRepository {

    void save(UsuarioEntity usuarioEntity);

    void desactivarUsuario(Integer id);

    void activarUsuario(Integer id);

    boolean existsByEmail(String email);

    boolean existsByCedula(String cedula);

    boolean existsById(Integer id);

    List<UsuarioDto> findAll();

    UsuarioDto findById(Integer id);


}
