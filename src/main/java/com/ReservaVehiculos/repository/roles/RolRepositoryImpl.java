package com.ReservaVehiculos.repository.roles;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class RolRepositoryImpl implements RolIRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<String> findPermisosByIdRol(Integer idRol) {
        String sql = "SELECT p.permiso " +
                "FROM tbl_rol_detalles rd " +
                "JOIN tbl_permisos p ON rd.id_permiso = p.id " +
                "WHERE rd.id_rol = ?";

        return jdbcTemplate.query(sql, new Object[]{idRol}, (rs, rowNum) -> rs.getString("permiso"));
    }

    @Override
    public String findRolById(Integer id) {
        String sql = "SELECT rol FROM tbl_roles WHERE id = ?";

        return jdbcTemplate.queryForObject(sql,(rs, rowNum) -> {
            return  rs.getString("rol");
        },id);
    }

}
