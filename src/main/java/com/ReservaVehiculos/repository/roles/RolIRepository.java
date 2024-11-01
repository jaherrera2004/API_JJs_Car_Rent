package com.ReservaVehiculos.repository.roles;

import java.util.List;

public interface RolIRepository {
    List<String> findPermisosByIdRol(Integer idRol);
    String findRolById(Integer id);
}
